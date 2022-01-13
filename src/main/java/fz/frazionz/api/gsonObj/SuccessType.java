package fz.frazionz.api.gsonObj;

import fz.frazionz.api.data.ShopAPIDataStocker;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SuccessType {

	private int id;
	private String action;
	private String display;
	private List<SuccessObj> items = new ArrayList<SuccessObj>();
	
	public SuccessType(int id, String action, String display) {
		this.id = id;
		this.action = action;
		this.display = display;
	}

	public int getId() {
		return id;
	}
	
	public String getAction() {
		return action;
	}

	public String getDisplay() {
		return display;
	}

	public List<SuccessObj> getItems() {
		return items;
	}

	public void setItems(List<SuccessObj> items) {
		this.items = items;
	}
}
