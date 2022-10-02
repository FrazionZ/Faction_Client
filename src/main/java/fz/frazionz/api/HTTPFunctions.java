package fz.frazionz.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import fz.frazionz.api.data.FactionProfile;
import fz.frazionz.api.gsonObj.ObjPlayerSkinsInfo;
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
	
	public static ObjPlayerSkinsInfo getPlayerSkinInfo(String username)
	{
		Minecraft mc = Minecraft.getMinecraft();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		
		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.PLAYER_SKIN_INFO, params);
		
		if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
		{
			ObjPlayerSkinsInfo obj = gson.fromJson(reply.getBody(), ObjPlayerSkinsInfo.class);
			return obj;
		}
		
		return null;
	}
	
}
