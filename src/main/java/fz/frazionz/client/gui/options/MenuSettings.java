package fz.frazionz.client.gui.options;

import fz.frazionz.client.gui.list.slot.*;
import fz.frazionz.client.gui.list.slot.keybinds.CategorySlot;
import fz.frazionz.client.gui.list.slot.keybinds.KeySlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.SoundCategory;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuSettings {

    private static final GameSettings.Options[] CHAT_OPTIONS = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO, GameSettings.Options.NARRATOR};
    private static final GameSettings.Options[] CONTROLS_OPTIONS = new GameSettings.Options[] {GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN, GameSettings.Options.AUTO_JUMP};




    public static List<FzSlot> getSlotForOptions(GameSettings.Options[] options) {
        List<FzSlot> slots = new java.util.ArrayList<>();
        for(GameSettings.Options option : options) {
            if(option.isFloat()) {
                slots.add(new GameSettingSliderInputSlot(0, 0, 200, option));
            }
            else if(option.isBoolean()) {
                slots.add(new GameSettingToggleInputSlot(0, 0, 200, option));
            }
            else {

            }
        }
        return slots;
    }

    public static FzSlot[] getGeneral() {

        List<FzSlot> slots = new java.util.ArrayList<>();
        slots.add(new GameSettingSliderInputSlot(0, 0, 200, GameSettings.Options.FOV));
        slots.add(new CategoryNameSlot(I18n.format("options.chat.title")));
        slots.addAll(getSlotForOptions(CHAT_OPTIONS));
        
        return slots.toArray(new FzSlot[slots.size()]);
    }

    public static FzSlot[] getMusics() {
        FzSlot[] slots = new FzSlot[SoundCategory.values().length];
        int i = 0;
        for (SoundCategory soundcategory : SoundCategory.values())
        {
            slots[i++] = new SoundSliderInputSlot(0, 0, 700, soundcategory);
        }
        return slots;
    }

    public static FzSlot[] getControls() {
        List<FzSlot> slots = new java.util.ArrayList<>();
        slots.addAll(getSlotForOptions(CONTROLS_OPTIONS));

        List<String> categories = new java.util.ArrayList<>();
        HashMap<String, List<FzSlot>> categoryBind = new HashMap<>();

        KeyBinding[] keybinds = (KeyBinding[]) ArrayUtils.clone(Minecraft.getMinecraft().gameSettings.keyBindings);
        for (KeyBinding keybinding : keybinds)
        {
            String category = keybinding.getKeyCategory();

            if (!categories.contains(category)) {
                categories.add(category);
                categoryBind.put(category, new java.util.ArrayList<>());
            }

            categoryBind.get(category).add(new KeySlot(keybinding));
        }

        for (String category : categories)
        {
            slots.add(new CategorySlot(I18n.format(category)));
            slots.addAll(categoryBind.get(category));
        }

        return slots.toArray(new FzSlot[slots.size()]);
    }

}
