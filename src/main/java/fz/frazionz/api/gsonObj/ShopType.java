package fz.frazionz.api.gsonObj;

import java.util.ArrayList;
import java.util.List;

import fz.frazionz.api.data.ShopAPIDataStocker;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShopType {

	private int id;
	private String typeName;
	private boolean isActive;
	private String minecraftItemName;
	private int minecraftItemData;
	private List<ShopItem> items = new ArrayList<ShopItem>();
	
	public int getId() {
		return id;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public String toString() {
		return "ShopType : " + this.typeName + " - isActive : " + this.isActive;
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
			item = ShopAPIDataStocker.getItemByText(this.getMinecraftItemName());
		} catch (NumberInvalidException e) {
			e.printStackTrace();
		}
	    ItemStack itemstack = new ItemStack(item, 1, this.getMinecraftItemData());
	    return itemstack;
	}
	
	public List<ShopItem> getItems() {
		return items;
	}
	
	public void setItems(List<ShopItem> items) {
		this.items = items;
	}
	
}
