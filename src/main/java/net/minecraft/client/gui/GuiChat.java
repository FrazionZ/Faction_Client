package net.minecraft.client.gui;

import java.io.IOException;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fz.frazionz.client.gui.GuiChatTextField;
import fz.frazionz.utils.FzTabCompleter;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class GuiChat extends GuiScreen implements ITabCompleter
{
    private static final Logger LOGGER = LogManager.getLogger();
    private String historyBuffer = "";

    /**
     * keeps position of which chat message you will select when you press up, (does not increase for duplicated
     * messages sent immediately after each other)
     */
    private int sentHistoryCursor = -1;
    private FzTabCompleter tabCompleter;

    /** Chat entry field */
    protected GuiChatTextField inputField;

    /**
     * is the text that appears when you press the chat key and the input box appears pre-filled
     */
    private String defaultInputFieldText = "";
    private GuiButton guiButtonShowPassword;

    private String showPasswordBtn = "AFF. LE MOT DE PASSE";
    private String hidePasswordBtn = "CACHER LE MOT DE PASSE";

    private String pwordLogin = "";
    private boolean showPassword = false;

    
    public GuiChat()
    {
    }

    public GuiChat(String defaultText)
    {
        this.defaultInputFieldText = defaultText;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
        this.inputField = new GuiChatTextField(0, this.fontRenderer, 4, this.height - 12, this.width - 4, 12);
        this.inputField.setMaxStringLength(256);
        this.inputField.setEnableBackgroundDrawing(false);
        this.inputField.setFocused(true);
        this.inputField.setText(this.defaultInputFieldText);
        this.inputField.setCanLoseFocus(false);
        this.tabCompleter = new GuiChat.ChatTabCompleter(this.inputField);
    
        guiButtonShowPassword = new GuiButton(10, 8, this.height - 37, 135, 20, showPasswordBtn);
        guiButtonShowPassword.visible = false;
        this.buttonList.add(guiButtonShowPassword);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
        this.mc.ingameGUI.getChatGUI().resetScroll();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.inputField.updateCursorCounter();
    }

    
    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.tabCompleter.resetRequested();

        if (keyCode == 15)
        {
            this.tabCompleter.complete();
        }
        else
        {
            this.tabCompleter.resetDidComplete();
        }

        if (keyCode == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
        else if (keyCode != 28 && keyCode != 156)
        {
            if (keyCode == 200)
            {
                this.getSentHistory(-1);
            }
            else if (keyCode == 208)
            {
                this.getSentHistory(1);
            }
            else if (keyCode == 201)
            {
                this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
            }
            else if (keyCode == 209)
            {
                this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
            }
            else
            {
                if(this.inputField.getText().startsWith("/login ") || this.inputField.getText().startsWith("/register ")) {
                    guiButtonShowPassword.visible = true;
	                if(this.showPassword)
	                    this.inputField.textboxKeyTyped(typedChar, keyCode, true);
	                else
	                    this.inputField.textboxKeyTyped((keyCode == 57) ? " ".charAt(0) : "*".charAt(0), keyCode, true);
                    
                    if(keyCode == 211 || keyCode == 14) 
                    {
                        if (pwordLogin.length() > 0) {
                            pwordLogin = FzUtils.removeLastChar(pwordLogin);
                        }
                    }
                    else {
                        if(ChatAllowedCharacters.isAllowedCharacter(typedChar))
                            pwordLogin += Character.toString(typedChar);
                    }
                }
                else {
                    this.inputField.textboxKeyTyped(typedChar, keyCode, true);
                }

                if(pwordLogin.length() <= 0)
                    guiButtonShowPassword.visible = false;
            }
        }
        else
        {
            String s = this.inputField.getText().trim();

            if (!s.isEmpty())
            {
                this.sendChatMessage(s);
            }

            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }

    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException
    {
        if(button.id == 10){
            this.inputField.setText(this.inputField.getText().split(" ")[0]+" ");
            this.showPassword = !this.showPassword;
            if(this.showPassword){
                guiButtonShowPassword.displayString = hidePasswordBtn;
                for(char ch : pwordLogin.toCharArray())
                    this.inputField.textboxKeyTyped(ch, 57, true);
            }else{
                guiButtonShowPassword.displayString = showPasswordBtn;
                for(char ch : pwordLogin.toCharArray())
                    this.inputField.textboxKeyTyped((String.valueOf(ch).equalsIgnoreCase(" ")) ? " ".charAt(0) : "*".charAt(0), 57, true);
            }
        }
    }

    
    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();

        if (i != 0)
        {
            if (i > 1)
            {
                i = 1;
            }

            if (i < -1)
            {
                i = -1;
            }

            if (!isShiftKeyDown())
            {
                i *= 7;
            }

            this.mc.ingameGUI.getChatGUI().scroll(i);
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        if (mouseButton == 0)
        {
            ITextComponent itextcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());

            if (itextcomponent != null && this.handleComponentClick(itextcomponent))
            {
                return;
            }
        }

        this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Sets the text of the chat
     */
    protected void setText(String newChatText, boolean shouldOverwrite)
    {
        if (shouldOverwrite)
        {
            this.inputField.setText(newChatText);
        }
        else
        {
            this.inputField.writeText(newChatText, true);
        }
    }

    /**
     * input is relative and is applied directly to the sentHistoryCursor so -1 is the previous message, 1 is the next
     * message from the current cursor position
     */
    public void getSentHistory(int msgPos)
    {
        int i = this.sentHistoryCursor + msgPos;
        int j = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
        i = MathHelper.clamp(i, 0, j);

        if (i != this.sentHistoryCursor)
        {
            if (i == j)
            {
                this.sentHistoryCursor = j;
                this.inputField.setText(this.historyBuffer);
            }
            else
            {
                if (this.sentHistoryCursor == j)
                {
                    this.historyBuffer = this.inputField.getText();
                }

                this.inputField.setText((String)this.mc.ingameGUI.getChatGUI().getSentMessages().get(i));
                this.sentHistoryCursor = i;
            }
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
        this.inputField.drawTextBox();
        ITextComponent itextcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());

        if (itextcomponent != null && itextcomponent.getStyle().getHoverEvent() != null)
        {
            this.handleComponentHover(itextcomponent, mouseX, mouseY);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Sets the list of tab completions, as long as they were previously requested.
     */
    public void setCompletions(String... newCompletions)
    {
        this.tabCompleter.setCompletions(newCompletions);
    }

    public static class ChatTabCompleter extends FzTabCompleter
    {
        private final Minecraft client = Minecraft.getMinecraft();

        public ChatTabCompleter(GuiChatTextField p_i46749_1_)
        {
            super(p_i46749_1_, false);
        }

        public void complete()
        {
            super.complete();

            if (this.completions.size() > 1)
            {
                StringBuilder stringbuilder = new StringBuilder();

                for (String s : this.completions)
                {
                    if (stringbuilder.length() > 0)
                    {
                        stringbuilder.append(", ");
                    }

                    stringbuilder.append(s);
                }

                this.client.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString(stringbuilder.toString()), 1);
            }
        }

        @Nullable
        public BlockPos getTargetBlockPos()
        {
            BlockPos blockpos = null;

            if (this.client.objectMouseOver != null && this.client.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                blockpos = this.client.objectMouseOver.getBlockPos();
            }

            return blockpos;
        }
    }
}
