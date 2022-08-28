package fz.frazionz.utils.data;

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
	
}
