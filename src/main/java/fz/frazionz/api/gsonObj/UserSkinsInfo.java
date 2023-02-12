package fz.frazionz.api.gsonObj;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

public class UserSkinsInfo {

	private String username;
	private boolean exist;
	private boolean hasSkin = true;
	private String skinSHA1;
	private SkinType skinType = SkinType.CLASSIC;
	private int capeId = -1;

	public UserSkinsInfo(boolean exist, String name, boolean hasSkin, String skinSHA1, String skinType) {
		this.exist = exist;
		this.username = name;
		this.hasSkin = hasSkin;
		this.skinSHA1 = skinSHA1;
		this.skinType = SkinType.valueOf(StringUtils.upperCase(skinType));
	}

	
	public String getUsername() {
		return username;
	}
	
	public SkinType getSkinType() {
		return this.skinType;
	}
	
	public boolean isSlim() {
		return getSkinType() == SkinType.SLIM;
	}

	public boolean hasSkin() {
		return this.hasSkin;
	}

	public String getSkinSHA1() {
		return this.skinSHA1;
	}

	public boolean isSkinExist() {
		return this.exist;
	}

	public int getCapeId() {
		return this.capeId;
	}

	public void setCapeId(int capeId) {
		this.capeId = capeId;
	}

	public static UserSkinsInfo fromJSON(String json) {
		JsonObject obj = new Gson().fromJson(json, JsonObject.class);
		boolean exist = obj.get("exist").getAsBoolean();
		String username = obj.get("name").getAsString();
		boolean hasSkin = obj.get("skin_exist").getAsBoolean();
		String skinSHA1 = obj.get("skin_sha1").getAsString();
		String skinType = obj.get("skin_type").getAsString();
		return new UserSkinsInfo(exist, username, hasSkin, skinSHA1, skinType);
	}


	public enum SkinType
	{
		CLASSIC("default"),
		SLIM("slim");
		
		String tps;
		
		SkinType(String tps){
			this.tps = tps;
		}
		
		public String getTps() {
			return this.tps;
		}
	}
	
}
