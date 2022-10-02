package fz.frazionz.client.gui.shop;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

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

	public static ShopCategory deserialize(String json) {
		return new Gson().fromJson(json, ShopCategory.class);
	}

	public static ShopCategory[] deserializeList(String json) {
		return new Gson().fromJson(json, ShopCategory[].class);
	}
}
