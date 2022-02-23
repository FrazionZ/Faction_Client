package fz.frazionz.api;

public class HTTPEndpoints {

	public static final String BASE = "https://api.frazionz.net/";
	public static final String WEBSITE = "https://frazionz.net/";
	public static final String WEBSITE_BASE = "https://frazionz.net/fzapi/";
	public static final String WEBSITE_API_BASE = "https://frazionz.net/API/";
	public static final String PLAYER_SKIN_INFO = WEBSITE_BASE + "skins";
	public static final String SHOP_ITEM_LIST = BASE + "shop/shopItem/getAllShopItem";
	public static final String SHOP_TYPE_LIST = BASE + "shop/shopType/getAllShopType";
	public static final String BOUTIQUE_TYPE_LIST = BASE + "boutique/boutiqueType/getAllBoutiqueType";
	public static final String BOUTIQUE_ITEM_LIST = BASE + "boutique/boutiqueItem/getAllBoutiqueItem";

	public static final String SUCCESS_TYPES_LIST = WEBSITE_API_BASE + "success/types";
	public static final String SUCCESS_OBJ_LIST = WEBSITE_API_BASE + "success/player/";

	public static final String API_MOJANG_GET_ONLINE_PLAYERS = "https://minecraft-api.com/api/ping/online/";

}
