package fz.frazionz.api.gsonObj;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fz.frazionz.utils.ItemUtils;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MarketType {

	private int id;
	private String typeName;
	private String minecraftItemName;
	private int minecraftItemData;

	public int getId() {
		return id;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public String toString() {
		return "{MarketType : " + this.typeName + "}";
	}
	
	public int getMinecraftItemData() {
		return minecraftItemData;
	}
	
	public String getMinecraftItemName() {
		return minecraftItemName;
	}
	
	public ItemStack getItemStack() {
		 Item item = null;
		 try {
			item = ItemUtils.getItemByText(this.getMinecraftItemName());
		} catch (NumberInvalidException e) {
			e.printStackTrace();
		}
	    ItemStack itemstack = new ItemStack(item, 1, this.getMinecraftItemData());
	    return itemstack;
	}

	public JsonElement serialize() {
		return new Gson().toJsonTree(this);
	}

	public JsonElement serializeList(MarketType[] types) {
		return new Gson().toJsonTree(types);
	}

	public static MarketType[] deserializeList(String json) {
		return new Gson().fromJson(json, MarketType[].class);
	}

	public static MarketType deserialize(String json) {
		return new Gson().fromJson(json, MarketType.class);
	}
}
