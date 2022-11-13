package fz.frazionz.utils;

public class StringUtils {

	public static String capitalize(String str) {
		str = str.toLowerCase();
		String[] strs = str.split(" ");
		for(int i = 0; i < strs.length; i++) {
			strs[i] = strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
		}
		return String.join(" ", strs);
	}
	
}
