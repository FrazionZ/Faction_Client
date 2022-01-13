package fz.frazionz.api.gsonObj;

import fz.frazionz.api.data.ShopAPIDataStocker;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BoutiqueItem {

	private int id;
	private String boutiqueItemName;
	private String material;
	private String minecraftItemName;
	private int minecraftItemData;
	private String description;
	private double buyPrice;
	private int boutiqueType;
	private boolean isActive;
	private String permissionNeeded;
	private String permission;
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getBuyPrice() {
		return buyPrice;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public int getMinecraftItemData() {
		return minecraftItemData;
	}
	
	public String getMinecraftItemName() {
		return minecraftItemName;
	}
	
	public int getBoutiqueType() {
		return boutiqueType;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public String getBoutiqueItemName() {
		return boutiqueItemName;
	}
	
	public String getPermissionNeeded() {
		return permissionNeeded;
	}
	
	public String getPermission() {
		return permission;
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
}
