package fz.frazionz.api.gsonObj;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fz.frazionz.utils.ItemUtils;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShopCategory {

	private int id;
	private String name;
	private String singularName;
	private List<ShopItem> items = new ArrayList<ShopItem>();

	public ShopCategory(int id, String name, String singularName) {
		this.id = id;
		this.name = name;
		this.singularName = singularName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSingularName() {
		return singularName;
	}
	
	public List<ShopItem> getItems() {
		return items;
	}
	
	public void setItems(List<ShopItem> items) {
		this.items = items;
	}
}
