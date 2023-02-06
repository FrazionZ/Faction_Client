package fz.frazionz.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonObject;

import com.google.gson.Gson;

import fz.frazionz.api.data.FactionProfile;
import fz.frazionz.api.gsonObj.UserSkinsInfo;
import fz.frazionz.api.gsonObj.ServerData;
import fz.frazionz.api.gsonObj.SuccessObj;
import fz.frazionz.api.gsonObj.SuccessType;
import net.minecraft.client.Minecraft;

public class HTTPFunctions {

	private static final Gson gson = new Gson();

	public static FactionProfile getFactionProfile()
	{
		FactionProfile factionProfile = null;

		System.out.println(Minecraft.getMinecraft().getSession().getPlayerID());
		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.FACTION_PROFILE + Minecraft.getMinecraft().getSession().getPlayerID());

		if(reply.getStatusCode() == 200)
		{
			factionProfile = gson.fromJson(reply.getBody(), FactionProfile.class);
		}
		return factionProfile;
	}

	public static List<SuccessType> getAllSucessTypes()
	{
		List<SuccessType> lst = new ArrayList<>();

		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.SUCCESS_TYPES_LIST);

		if(reply.getStatusCode() == 200)
		{
			SuccessType[] objs = gson.fromJson(reply.getBody(), SuccessType[].class);
			lst.addAll(Arrays.asList(objs));
		}
		return lst;
	}

	public static String getServerData(String ip, String port)
	{

		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.API_MOJANG_GET_ONLINE_PLAYERS+"/"+ip+"/"+port+"/json");
		ServerData objs = null;
		if(reply.getStatusCode() == 200)
		{
			objs = gson.fromJson(reply.getBody(), ServerData.class);
		}
		assert objs != null;
		return objs.getOnline();
	}

	public static List<SuccessObj> getAllSuccessItemsPlayer(String idType)
	{
		List<SuccessObj> lst = new ArrayList<>();

		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.SUCCESS_OBJ_LIST+idType+"/"+Minecraft.getMinecraft().getSession().getPlayerID());

		if(reply.getStatusCode() == 200)
		{
			System.out.println(reply.getBody());
			SuccessObj[] objs = gson.fromJson(reply.getBody(), SuccessObj[].class);
			lst.addAll(Arrays.asList(objs));
		}
		return lst;
	}
	
	public static UserSkinsInfo getPlayerSkinInfo(UUID uuid)
	{
		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.API_USER_UUID_SKIN_DATA.replace("{UUID}", uuid.toString()));

		if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
		{
			return UserSkinsInfo.fromJSON(reply.getBody());
		}
		
		return null;
	}

	public static int getPlayerCapeId(UUID uuid) {
		String body = get(HTTPEndpoints.API_USER_UUID_CAPE_DATA.replace("{UUID}", uuid.toString()));
		if(body != null)
		{
			JsonObject obj = gson.fromJson(body, JsonObject.class);
			return obj.get("cape_id").getAsInt();
		}
		return -1;
	}

	public static String get(String url)
	{
		HTTPReply reply = HTTPUtils.sendGet(url);
		if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
		{
			return reply.getBody();
		}
		return null;
	}
	
}
