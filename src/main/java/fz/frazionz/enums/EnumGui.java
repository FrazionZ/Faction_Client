package fz.frazionz.enums;

import java.util.Arrays;

public enum EnumGui {

    MARKET_LIST("FZ|Market_List"),
    MARKET_ITEM_LIST("FZ|Market_Item_List"),
    MARKET_ITEM("FZ|Market_Item"),
    SHOP_MENU("FZ|Shop_Menu"),
    SHOP_ITEM("FZ|Shop_Item"),
    SKILL_MENU("FZ|Skill_Menu"),
    SERVER_SWITCHER("FZ|Server_Switcher"),
    CUSTOM_GUI("FZ|Custom_Gui"),
    NULL("FZ|Null"),
    ;

    public String key;
    EnumGui(String key) {
        this.key = key;
    }

    public static EnumGui fromKey(String key) {
        return Arrays.stream(EnumGui.values()).filter(enumGui -> enumGui.key.equals(key)).findFirst().orElse(NULL);
    }
}
