package fz.frazionz.api.gsonObj;

import fz.frazionz.api.data.ShopAPIDataStocker;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MarketItem {

	private int id;
	private String minecraftItemName;
	private int minecraftItemData;
	private int maxStock;
	private int actualStock;
	private int shopType;
	private boolean isActive;
	private double actualBuyPrice;
	private double actualSellPrice;
	
	public int getId() {
		return id;
	}
	
	public int getActualStock() {
		return actualStock;
	}
	
	public int getMaxStock() {
		return maxStock;
	}
	
	public int getMinecraftItemData() {
		return minecraftItemData;
	}
	
	public String getMinecraftItemName() {
		return minecraftItemName;
	}
	
	public int getShopType() {
		return shopType;
	}
	
	public double getActualBuyPrice() {
		return actualBuyPrice;
	}
	
	public double getActualSellPrice() {
		return actualSellPrice;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public ItemStack getItemStack() {
		 Item item = null;
		 try {
			item = ShopAPIDataStocker.getItemByText(this.getMinecraftItemName());
		} catch (NumberInvalidException e) {
			e.printStackTrace();
		}
	    ItemStack itemstack = new ItemStack(item, 1, this.getMinecraftItemData());
	    return itemstack;
	}

	public static ItemStack getItemStack(String itemName) {
		Item item = null;
		try {
			item = ShopAPIDataStocker.getItemByText(itemName);
		} catch (NumberInvalidException e) {
			e.printStackTrace();
		}
		ItemStack itemstack = new ItemStack(item, 1);
		return itemstack;
	}
}
