package net.minecraft.client.gui;

import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fz.frazionz.Client;
import fz.frazionz.TTFFontRenderer;
import fz.frazionz.client.gui.buttons.GuiFzButton;
import fz.frazionz.client.gui.buttons.GuiFzOptionButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;

public class GuiLanguage extends GuiScreen
{
    
    /** The List GuiSlot object reference. */
    private GuiLanguage.List list;

    /** Reference to the GameSettings object. */
    private final GameSettings game_settings_3;

    /** Reference to the LanguageManager object. */
    private final LanguageManager languageManager;

    /**
     * A button which allows the user to determine if the Unicode font should be forced.
     */
    private GuiFzOptionButton forceUnicodeFontBtn;

    /** The button to confirm the current settings. */
    private GuiFzButton confirmSettingsBtn;
    
    private GuiScreen lastScreen;
    private String title;


    public GuiLanguage(GuiScreen screen, GameSettings gameSettingsObj, LanguageManager manager)
    {
    	this.lastScreen = screen;
        this.game_settings_3 = gameSettingsObj;
        this.languageManager = manager;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
    	super.initGui();
    	this.title = I18n.format("options.language");
    	
        this.forceUnicodeFontBtn = (GuiFzOptionButton)this.addButton(new GuiFzOptionButton(100, this.width / 2 - 155, this.height - this.height/16 - 10, I18n.format(GameSettings.Options.FORCE_UNICODE_FONT.getTranslation()), this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT)));
        this.confirmSettingsBtn = (GuiFzButton)this.addButton(new GuiFzButton(6, this.width / 2 - 155 + 160, this.height - this.height/16 - 10, 150, 20, I18n.format("gui.done")));
        this.list = new GuiLanguage.List(this.mc, 0, width, height/8, height-height/8, 20);
        this.list.registerScrollButtons(7, 8);
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException
    {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button, int keyCode) throws IOException
    {
        if (button.enabled)
        {
            switch (button.id)
            {
                case 5:
                    break;

                case 6:
                    this.mc.displayGuiScreen(this.lastScreen);
                    break;

                case 100:
                    this.game_settings_3.setOptionValue(((GuiFzOptionButton)button).getOption(), 1);
                    ((GuiFzOptionButton)button).setInfo(this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT));
                    ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                    int i = scaledresolution.getScaledWidth();
                    int j = scaledresolution.getScaledHeight();
                    this.setWorldAndResolution(this.mc, i, j);
                    break;

                default:
                    this.list.actionPerformed(button, keyCode);
            }
        }
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {        
    	TTFFontRenderer titleRenderer = Client.getInstance().getTTFFontRenderers().get(24);
    	int titleSize = titleRenderer.getWidth(this.title);
    	
    	this.drawRect(0, 0, width, height, BLACK_3);
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawRect(0, 0, this.width, this.height/8, this.BLACK_4);
	    this.drawRect(0, this.height - this.height/8, this.width, this.height, this.BLACK_4);
        titleRenderer.drawCenteredString(this.title, this.width / 2, this.height/16, 0xFFFFFFFF);
	
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    class List extends GuiSlot
    {
        private final java.util.List<String> langCodeList = Lists.<String>newArrayList();
        private final Map<String, Language> languageMap = Maps.<String, Language>newHashMap();

        public List(Minecraft mcIn, int width, int height, int top, int bottom, int slotHeight)
        {
            super(mcIn, width, height, top, bottom, slotHeight);

            for (Language language : GuiLanguage.this.languageManager.getLanguages())
            {
                this.languageMap.put(language.getLanguageCode(), language);
                this.langCodeList.add(language.getLanguageCode());
            }
        }

        protected int getSize()
        {
            return this.langCodeList.size();
        }

        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY)
        {
            Language language = this.languageMap.get(this.langCodeList.get(slotIndex));
            GuiLanguage.this.languageManager.setCurrentLanguage(language);
            GuiLanguage.this.game_settings_3.language = language.getLanguageCode();
            this.mc.refreshResources();
            GuiLanguage.this.fontRenderer.setUnicodeFlag(GuiLanguage.this.languageManager.isCurrentLocaleUnicode() || GuiLanguage.this.game_settings_3.forceUnicodeFont);
            GuiLanguage.this.fontRenderer.setBidiFlag(GuiLanguage.this.languageManager.isCurrentLanguageBidirectional());

            GuiLanguage.this.confirmSettingsBtn.displayString = I18n.format("gui.done");
            GuiLanguage.this.forceUnicodeFontBtn.displayString = GuiLanguage.this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
            
            GuiLanguage.this.game_settings_3.saveOptions();
        }

        protected boolean isSelected(int slotIndex)
        {
            return ((String)this.langCodeList.get(slotIndex)).equals(GuiLanguage.this.languageManager.getCurrentLanguage().getLanguageCode());
        }

        protected int getContentHeight()
        {
            return this.getSize() * 18;
        }

        protected void drawBackground()
        {
        }

        protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks)
        {
            GuiLanguage.this.fontRenderer.setBidiFlag(true);
            GuiLanguage.this.drawCenteredString(GuiLanguage.this.fontRenderer, ((Language)this.languageMap.get(this.langCodeList.get(slotIndex))).toString(), this.width / 2, yPos + 1, 16777215);
            GuiLanguage.this.fontRenderer.setBidiFlag(GuiLanguage.this.languageManager.getCurrentLanguage().isBidirectional());
        }
    }
}
