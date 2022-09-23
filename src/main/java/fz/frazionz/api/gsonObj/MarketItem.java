package fz.frazionz.api.gsonObj;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fz.frazionz.utils.ItemUtils;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MarketItem {

	private int id;
	private String itemName;
	private int itemData;
	private int maxStock;
	private int stock;
	private double buyPrice;
	private double sellPrice;

	public int getId() {
		return id;
	}

	public int getMaxStock() {
		return maxStock;
	}

	public int getStock() {
		return stock;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public ItemStack getItemStack() {
		 Item item = null;
		 try {
			item = ItemUtils.getItemByText(this.itemName);
		} catch (NumberInvalidException e) {
			e.printStackTrace();
		}
	    ItemStack itemstack = new ItemStack(item, 1, this.itemData);
	    return itemstack;
	}

	public JsonElement serialize() {
		return new Gson().toJsonTree(this);
	}

	public JsonElement serializeList(MarketItem[] types) {
		return new Gson().toJsonTree(types);
	}

	public static MarketItem[] deserializeList(String json) {
		return new Gson().fromJson(json, MarketItem[].class);
	}

	public static MarketItem deserialize(String json) {
		return new Gson().fromJson(json, MarketItem.class);
	}
}
