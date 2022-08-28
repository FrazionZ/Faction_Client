package fz.frazionz.client.model;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class JsonModel extends ModelBase {

	private final ModelBone[] cubes;
	
	public JsonModel() {
		this.cubes = new ModelBone[1];
	}
	
	public JsonModel(JSONObject json, boolean debug) {
		if(json.has("minecraft:geometry")) {
			JSONObject jsonInfos = json.getJSONArray("minecraft:geometry").getJSONObject(0);
			JSONObject description = jsonInfos.getJSONObject("description");
			JSONArray bones = jsonInfos.getJSONArray("bones");
			
			this.textureWidth = description.getInt("texture_width");
			this.textureHeight = description.getInt("texture_height");
			
			this.cubes = new ModelBone[bones.length()];
			
			for(int i = 0; i < bones.length(); i++) {
				ModelBone part = new ModelBone(bones.getJSONObject(i), this, debug);
				this.cubes[i] = part;
			}
		}
		else {
			this.cubes = new ModelBone[0];
		}
	}
	
	public ModelBone[] getCubes() {
		return cubes;
	}
	
	public void renderAll() {
		cubes[0].render(0.0625f);
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		cubes[0].render(scale);
	}
	
	public ModelBone getCube(String name) {
		return Arrays.stream(cubes).filter(cube -> cube.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
}
