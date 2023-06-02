package fz.frazionz.client.gui.options;

import fz.frazionz.client.gui.list.slot.CategoryNameSlot;
import fz.frazionz.client.gui.list.slot.FzSlot;
import fz.frazionz.client.gui.list.slot.GameSettingSliderInputSlot;
import fz.frazionz.client.gui.list.slot.SoundSliderInputSlot;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.SoundCategory;

import java.util.List;

public class MenuSettings {

    private static final GameSettings.Options[] CHAT_OPTIONS = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH, GameSettings.Options.REDUCED_DEBUG_INFO, GameSettings.Options.NARRATOR};

    public static List<FzSlot> getSlotForOptions(GameSettings.Options[] options) {
        List<FzSlot> slots = new java.util.ArrayList<>();
        for(GameSettings.Options option : options) {
            if(option.isFloat()) {
                slots.add(new GameSettingSliderInputSlot(0, 0, 200, option));
            }
            else if(option.isBoolean()) {
                //slots.add(new GamesettingSliderInputSlot(0, 0, 200, option));
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

}
