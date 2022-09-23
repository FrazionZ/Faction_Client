package fz.frazionz.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import fz.frazionz.api.data.FactionProfile;
import fz.frazionz.api.gsonObj.ShopItem;
import fz.frazionz.api.gsonObj.ShopType;
import fz.frazionz.api.gsonObj.ObjPlayerSkinsInfo;
import fz.frazionz.api.gsonObj.ServerData;
import fz.frazionz.api.gsonObj.MarketItem;
import fz.frazionz.api.gsonObj.MarketType;
import fz.frazionz.api.gsonObj.SuccessObj;
import fz.frazionz.api.gsonObj.SuccessType;
import net.minecraft.client.Minecraft;

public class HTTPFunctions {

	private static final Gson gson = new Gson();
	
	public static boolean isAPIUp()
	{
		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.BASE);
		return reply.getStatusCode() == 200;
	}

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
	
	
	/*public static double getPlayerMoney() {
		Minecraft mc = Minecraft.getMinecraft();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", mc.getSession().getUsername()));
		
		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.PLAYER_MONEY, params);
		
		if(reply.getStatusCode() == 200)
		{
			ObjPlayerMoney obj = gson.fromJson(reply.getBody(), ObjPlayerMoney.class);
			return obj.getMoney();
		}
		return 0;
	}
	
	public static List getPlayerStats()
	{
		List lst = null;
		
		Minecraft mc = Minecraft.getMinecraft();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", mc.getSession().getUsername()));
		
		HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.PLAYER_STATS, params);
		
		if(reply.getStatusCode() == 200 || reply.getStatusCode() == 304)
		{
			ObjPlayerStats[] obj = gson.fromJson(reply.getBody(), ObjPlayerStats[].class);
			lst = Arrays.asList(obj[0].getPlayerName(), obj[0].getPoints(), obj[0].getKills(), obj[0].getDeaths(), obj[0].getFactionId(), obj[0].getMoney());
			return lst;
		}
		
		return lst;
	}*/
	
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
