package fz.frazionz.api.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import fz.frazionz.api.gsonObj.ProfilItem;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.api.gsonObj.SuccessType;
import fz.frazionz.utils.FzUtils;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDataStocker {

	private static final Gson gson = new Gson();

	private static List<String> permissionsList;

	public static List<ProfilItem> profilItems;

	public static List<String> getPermissionsList() {
		return permissionsList;
	}
	
	public static void setPermissionsList(List<String> list) {
		permissionsList = list;
	}
	
	public static boolean hasPermission(String permission) {
		return true;
	}

	public static void loadProfilItems(){
		if(profilItems != null)
			profilItems.clear();
		File profiles = new File(FzUtils.getLauncherDir(), "profiles.json");
		if(profiles.exists()){
			try {
				JsonReader reader = new JsonReader(new FileReader(profiles));
				ProfilItem[] objs = gson.fromJson(reader, ProfilItem[].class);
				profilItems = new ArrayList<>();
				if(objs.length > 0){
					for(ProfilItem profilItem : objs)
						if(profilItem.getUuid().equalsIgnoreCase(Minecraft.getMinecraft().getSession().getPlayerID()))
							profilItems.add(profilItem);

					//LOAD OTHER PROFILE
					for(ProfilItem profilItem : objs)
						if(!profilItem.getUuid().equalsIgnoreCase(Minecraft.getMinecraft().getSession().getPlayerID()))
							profilItems.add(profilItem);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static ProfilItem getPlayer(String uuid){
		ProfilItem rprofilItem = null;
		for(ProfilItem profilItem : profilItems)
			if(profilItem.getUuid().equalsIgnoreCase(uuid))
				rprofilItem = profilItem;

		return rprofilItem;
	}

	public static ProfilItem getPlayer(int position){
		return profilItems.get(position);
	}
}
