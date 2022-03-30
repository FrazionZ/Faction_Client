package fz.frazionz.api;

public class HTTPEndpoints {

	public static final String BASE = "https://api.frazionz.net/";
	public static final String FACTION = "faction/";
	public static final String WEBSITE = "https://frazionz.net/";
	public static final String WEBSITE_BASE = "https://frazionz.net/fzapi/";
	public static final String WEBSITE_API_BASE = "https://frazionz.net/API/";
	public static final String PLAYER_SKIN_INFO = BASE + "skins";
	public static final String SHOP_ITEM_LIST = BASE + FACTION + "shop/items";
	public static final String SHOP_TYPE_LIST = BASE + FACTION + "shop/types";
	public static final String BOUTIQUE_TYPE_LIST = BASE + FACTION + "boutique/types";
	public static final String BOUTIQUE_ITEM_LIST = BASE + FACTION + "boutique/items";
	public static final String FZ_PROFILE_DATA = BASE + "users?username=";

	public static final String FACTION_PROFILE = BASE + FACTION + "profile/";

	public static final String SUCCESS_TYPES_LIST = WEBSITE_API_BASE + "success/types";
	public static final String SUCCESS_OBJ_LIST = WEBSITE_API_BASE + "success/player/";

	public static final String API_MOJANG_GET_ONLINE_PLAYERS = "https://minecraft-api.com/api/ping/online/";

}
