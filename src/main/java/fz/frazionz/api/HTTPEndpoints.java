package fz.frazionz.api;

public class HTTPEndpoints {

	// Base
	public static final String BASE = "https://api.frazionz.net/";
	public static final String BASE_FACTION = BASE + "faction/";
	public static final String BASE_SKILL = BASE_FACTION + "skill/";
	
	public static final String WEBSITE = "https://frazionz.net/";
	public static final String WEBSITE_BASE = "https://frazionz.net/fzapi/";
	public static final String WEBSITE_API_BASE = "https://frazionz.net/API/";
	public static final String PLAYER_SKIN_INFO = BASE + "skins";
	public static final String FZ_PROFILE_DATA = BASE + "users?username=";
	
	// Faction
	public static final String FACTION_PROFILE = BASE_FACTION + "profile/";

	// Skill
	public static final String SKILL_LIST = BASE_SKILL + "all/";
	public static final String SKILL_EXP_NEEDED = BASE_SKILL + "needed/";
	public static final String SKILL_ACTIONS = BASE_SKILL + "actions/";
	
	
	public static final String SUCCESS_TYPES_LIST = WEBSITE_API_BASE + "success/types";
	public static final String SUCCESS_OBJ_LIST = WEBSITE_API_BASE + "success/player/";

	public static final String API_MOJANG_GET_ONLINE_PLAYERS = "https://minecraft-api.com/api/ping/online/";

}
