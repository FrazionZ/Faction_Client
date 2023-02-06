package fz.frazionz.api;

public class HTTPEndpoints {

	// Base
	public static final String BASE = "https://api.frazionz.net/";
	public static final String BASE_FACTION = "https://api.frazionz.net/faction/";
	public static final String BASE_SKILL = "https://api.frazionz.net/faction/skill/";
	
	public static final String WEBSITE = "https://frazionz.net/";
	public static final String WEBSITE_API_BASE = "https://frazionz.net/API/";
	public static final String FZ_PROFILE_DATA = "https://api.frazionz.net/users?username=";
	
	// Faction
	public static final String FACTION_PROFILE = "https://api.frazionz.net/faction/profile/";

	// Skill
	public static final String SKILL_LIST = BASE_SKILL + "all/";
	public static final String SKILL_EXP_NEEDED = BASE_SKILL + "needed/";
	public static final String SKILL_ACTIONS = BASE_SKILL + "actions/";
	
	
	public static final String SUCCESS_TYPES_LIST = WEBSITE_API_BASE + "success/types";
	public static final String SUCCESS_OBJ_LIST = WEBSITE_API_BASE + "success/player/";

	public static final String API_MOJANG_GET_ONLINE_PLAYERS = "https://minecraft-api.com/api/ping/online/";

	// API USER
	public static final String API_USER_UUID_SKIN_DATA = "https://api.frazionz.net/user/{UUID}/skin/data/";
	public static final String API_USER_UUID_SKIN_DISPLAY = "https://api.frazionz.net/user/{UUID}/skin/display/";
	public static final String API_USER_UUID_CAPE_DATA = "https://api.frazionz.net/user/{UUID}/cape/data/";
	public static final String API_CAPES_DISPLAY_BRUT_ID = "https://api.frazionz.net/capes/display/brut/{ID}";
	public static final String API_CAPES_LIBRARY = "https://api.frazionz.net/capes/library/";

}
