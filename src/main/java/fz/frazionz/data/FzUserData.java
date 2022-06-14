package fz.frazionz.data;

import java.util.Arrays;
import java.util.List;

public class FzUserData {

	private static FzUserData instance;
	
	private List<String> macros = Arrays.asList("", "", "", "");
	
	public FzUserData() {
		instance = this;
	}
	
	public static FzUserData getInstance() {
		return instance;
	}
	
	public void setMacros(List<String> macros) {
		this.macros = macros;
	}
	
	public List<String> getMacros() {
		return macros;
	}
	
	public void setMacro(int macroNumber, String macro) {
		this.macros.set(macroNumber, macro);
	}
	
	public String getMacro(int macroNumber) {
		return this.macros.get(macroNumber);
	}
	
	public enum EnumUserData {
		
		CURRENT_CHUNK("wilderness", DataType.SERVER),
		FACTION("wilderness", DataType.USER),
		MONEY(0.0D, DataType.USER);
		
		private Object value;
		private DataType type;
		
		private EnumUserData(Object value, DataType type) {
			this.value = value;
			this.type = type;
		}
		
		public Object getValue() {
			return value;
		}
		
		public DataType getType() {
			return type;
		}
		
		public void setValue(Object value) {
			this.value = value;
		}
		
		public enum DataType {
			
			SERVER,
			USER;
			
		}
		
	}
	
}
