package fz.frazionz.api.gsonObj;

import org.apache.commons.lang3.StringUtils;

public class ObjPlayerSkinsInfo {

	private boolean exist;
	private String name;
	private boolean skinexist = true;
	private boolean capexist = true;
	private String typeskin = "steve";
	
	public String getName() {
		return name;
	}
	
	public SkinType getSkinType() {
		return SkinType.valueOf(StringUtils.upperCase(this.typeskin));
	}
	
	public boolean isSlim() {
		return (getSkinType() == SkinType.ALEX) ? true : false;
	}
	
	public boolean isSkinExist() {
		return skinexist;
	}
	
	public boolean isCapeExist() {
		return capexist;
	}

	@Override
	public String toString() {
		return "ObjPlayerSkinsInfo{" +
				"exist=" + exist +
				", name='" + name + '\'' +
				", skinexist=" + skinexist +
				", capexist=" + capexist +
				", typeskin='" + typeskin + '\'' +
				'}';
	}

	public enum SkinType
	{
		STEVE("default"),
		ALEX("slim");
		
		String tps;
		
		SkinType(String tps){
			this.tps = tps;
		}
		
		public String getTps() {
			return this.tps;
		}
	}
	
}
