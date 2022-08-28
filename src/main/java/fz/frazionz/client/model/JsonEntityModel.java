package fz.frazionz.client.model;

import org.json.JSONObject;

import fz.frazionz.utils.JsonHelper;
import net.minecraft.resources.ResourceLocation;

public class JsonEntityModel extends JsonModel {

	private final String name;
	
	public JsonEntityModel(String name) {
		this(name, false);
	}
	
	public JsonEntityModel(String name, boolean debug) {
		super(JsonHelper.getJsonObject(new ResourceLocation("frazionz", "models/entity/" + name + ".json")), debug);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
