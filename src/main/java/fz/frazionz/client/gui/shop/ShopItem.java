package fz.frazionz.client.gui.shop;

import com.google.gson.Gson;

public class ShopItem {

	private int id;
	private String name;
	private double pricePbs;
	private double priceCoins;
	private boolean multipleBuy;
	private String description;
	private String customCategoryName;
	private int color1;
	private int color2;
	private int position;
	private ShopCategory category;

	public ShopItem(int id, String name, double pricePbs, double priceCoins, boolean multipleBuy, String description, String customCategoryName, int color1, int color2, int position, ShopCategory category) {
		this.id = id;
		this.name = name;
		this.pricePbs = pricePbs;
		this.priceCoins = priceCoins;
		this.multipleBuy = multipleBuy;
		this.description = description;
		this.customCategoryName = customCategoryName;
		this.color1 = color1;
		this.color2 = color2;
		this.position = position;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPricePbs() {
		return pricePbs;
	}

	public void setPricePbs(double pricePbs) {
		this.pricePbs = pricePbs;
	}

	public double getPriceCoins() {
		return priceCoins;
	}

	public void setPriceCoins(double priceCoins) {
		this.priceCoins = priceCoins;
	}

	public boolean isMultipleBuy() {
		return multipleBuy;
	}

	public void setMultipleBuy(boolean multipleBuy) {
		this.multipleBuy = multipleBuy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomCategoryName() {
		return customCategoryName;
	}

	public void setCustomCategoryName(String customCategoryName) {
		this.customCategoryName = customCategoryName;
	}

	public int getColor1() {
		return color1;
	}

	public void setColor1(int color1) {
		this.color1 = color1;
	}

	public int getColor2() {
		return color2;
	}

	public void setColor2(int color2) {
		this.color2 = color2;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ShopCategory getCategory() {
		return category;
	}

	public void setCategory(ShopCategory category) {
		this.category = category;
	}

	public static ShopItem deserialize(String json) {
		return new Gson().fromJson(json, ShopItem.class);
	}

	public static ShopItem[] deserializeList(String json) {
		return new Gson().fromJson(json, ShopItem[].class);
	}
}
