package fz.frazionz.api.data;

import java.util.List;

public class PlayerDataStocker {
	
	private static List<String> permissionsList;

	public static List<String> getPermissionsList() {
		return permissionsList;
	}
	
	public static void setPermissionsList(List<String> list) {
		permissionsList = list;
	}
	
	public static boolean hasPermission(String permission) {
		return true;
	}
}
