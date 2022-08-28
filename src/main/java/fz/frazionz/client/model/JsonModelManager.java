package fz.frazionz.client.model;

import java.util.List;

public class JsonModelManager {

	public static List<JsonEntityModel> models;
	
	public static JsonEntityModel getJsonEntityModel(String modelName) {
		return models.stream().filter(model -> model.getName().equalsIgnoreCase(modelName)).findFirst().orElse(null);
	}
	
	public static JsonModel createJsonModel(ModelType modelType, String name) {
		switch(modelType) {
			case ENTITY:
				return createJsonEntityModel(name);
			default:
				return createDefaultModel();
		}
	}
	
	public static JsonEntityModel createJsonEntityModel(String name) {
		return new JsonEntityModel(name);
	}
	
	public static JsonModel createDefaultModel() {
		return new JsonModel();
	}
	
	public enum ModelType {
		
		ENTITY,
		COSMETIC
		
	}
	
}
