package fz.frazionz.client.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import fz.frazionz.client.model.geom.ModelCube;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.Config;
import net.optifine.model.ModelSprite;

public class ModelBone extends ModelRenderer {

	private ModelBone parent;
	private List<ModelCube> cubes = new ArrayList<>();
	private float jsonPivotX = 0.0f;
	private float jsonPivotY = 0.0f;
	private float jsonPivotZ = 0.0f;
	
    public ModelBone(ModelBase model)
    {
    	super(model);
    }
	
    public ModelBone(ModelBase model, String name)
    {
    	super(model, name);
    }

    public ModelBone(String name, ModelBase model)
    {
    	super(model);
    }

    public ModelBone(String name, ModelBase model, int texOffX, int texOffY)
    {
    	super(model, texOffX, texOffY);
    }
    
    public ModelBone(JSONObject bone, JsonModel model, boolean debug) {
    	super(model, bone.getString("name"));
		if(bone.has("parent")) {
			String parent = bone.getString("parent");
			for(ModelBone part : model.getCubes()) {
				if(part.boxName.equalsIgnoreCase(parent)) {
					this.parent = part;
					this.parent.addChild(this);
					break;
				}
			}
		}

		if(bone.has("pivot")) {
			JSONArray pivot = bone.getJSONArray("pivot");
			this.jsonPivotX = pivot.getFloat(0);
			this.jsonPivotY = pivot.getFloat(1);
			this.jsonPivotZ = pivot.getFloat(2);
			float pivotX = pivot.getFloat(0) - (this.parent == null ? 0 : this.parent.jsonPivotX);
			float pivotY = -pivot.getFloat(1) + (this.parent == null ? 24 : this.parent.jsonPivotY);
			float pivotZ = pivot.getFloat(2) - (this.parent == null ? 0 : this.parent.jsonPivotZ);
			setRotationPoint(pivotX, pivotY, pivotZ);
		}
		if(bone.has("rotation")) {
			JSONArray rotation = bone.getJSONArray("rotation");
			this.rotateAngleX = (float) Math.toRadians(rotation.getFloat(0));
			this.rotateAngleY = (float) Math.toRadians(rotation.getFloat(1));
			this.rotateAngleZ = (float) Math.toRadians(rotation.getFloat(2));
		}
		
    	if(bone.has("cubes")) {
    		JSONArray cubes = bone.getJSONArray("cubes");
    		for(int i = 0; i < cubes.length(); i++) {
    			JSONObject cube = cubes.getJSONObject(i);
    			
    			float inflate = 0.0f;
    			float originX = 0.0f;
    			float originY = 0.0f;
    			float originZ = 0.0f;
				JSONArray size = cube.getJSONArray("size");
				int sizeX = size.getInt(0);
				int sizeY = size.getInt(1);
				int sizeZ = size.getInt(2);
				
    			if(cube.has("origin")) {
        			JSONArray origin = cube.getJSONArray("origin");
        			originX = origin.getFloat(0);
        			originY = -(origin.getFloat(1) + sizeY);
        			originZ = origin.getFloat(2);
    			}
    			if(cube.has("inflate"))
    				inflate = cube.getFloat("inflate");
    			JSONArray uv = cube.getJSONArray("uv");
    			    			
				if(cube.has("rotation") && cube.has("pivot")) {
					LogManager.getLogger().info("WITH ROTATION: ");
					ModelBone part = new ModelBone(model);
	    			part.parent = this;
	    			
	    			JSONArray pivot = cube.getJSONArray("pivot");
	    			float pivotX = pivot.getFloat(0) - this.jsonPivotX;
	    			float pivotY = this.jsonPivotY - pivot.getFloat(1);
	    			float pivotZ = pivot.getFloat(2) - this.jsonPivotZ;
	    			part.setRotationPoint(pivotX, pivotY, pivotZ);
	    				
	    			JSONArray rotation = cube.getJSONArray("rotation");
	    			part.rotateAngleX = (float) Math.toRadians(rotation.getFloat(0));
	    			part.rotateAngleY = (float) Math.toRadians(rotation.getFloat(1));
	    			part.rotateAngleZ = (float) Math.toRadians(rotation.getFloat(2));
	    				
	    			ModelCube box = new ModelCube(
	    					part, uv.getInt(0), uv.getInt(1),
	    					//originX-part.rotationPointX, pivot.getFloat(1)+originY, originZ-part.rotationPointZ,
	    					originX - pivot.getFloat(0), pivot.getFloat(1)+originY, originZ - pivot.getFloat(2),
	    					sizeX, sizeY, sizeZ,
	    					inflate, false, debug
	    			);
	    			part.cubes.add(box);
	    			this.addChild(part);
	    			if(debug)
	    				LogManager.getLogger().info("SubBone: " + part);
				}
				else {
					LogManager.getLogger().info("Nothing: ");
	    			ModelCube box = new ModelCube(
	    					this, uv.getInt(0), uv.getInt(1),
	    					originX-this.jsonPivotX, (this.parent != null ? this.jsonPivotY + originY : originY), originZ-this.jsonPivotZ,
	    					sizeX, sizeY, sizeZ,
	    					inflate, false, debug
	    			);
	    			this.cubes.add(box);
				}
    			
    		}
    	}
    	if(debug)
    		LogManager.getLogger().info("Bone: " + this);
		
    }
    
    public void render(float scale)
    {
        if (!this.isHidden && this.showModel)
        {
            this.checkResetDisplayList();

            if (!this.compiled)
            {
                this.compileDisplayList(scale);
            }

            int i = 0;

            if (this.textureLocation != null && !this.renderGlobal.renderOverlayDamaged)
            {
                if (this.renderGlobal.renderOverlayEyes)
                {
                    return;
                }

                i = GlStateManager.getBoundTexture();
                Config.getTextureManager().bindTexture(this.textureLocation);
            }

            if (this.modelUpdater != null)
            {
                this.modelUpdater.update();
            }

            boolean flag = this.scaleX != 1.0F || this.scaleY != 1.0F || this.scaleZ != 1.0F;
            GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);

            if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
            {
                if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F)
                {
                    if (flag)
                    {
                        GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
                    }

                    GlStateManager.callList(this.displayList);

                    if (this.childModels != null)
                    {
                        for (int l = 0; l < this.childModels.size(); ++l)
                        {
                            ((ModelRenderer)this.childModels.get(l)).render(scale);
                        }
                    }

                    if (flag)
                    {
                        GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
                    }
                }
                else
                {
                    GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

                    if (flag)
                    {
                        GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
                    }

                    GlStateManager.callList(this.displayList);

                    if (this.childModels != null)
                    {
                        for (int k = 0; k < this.childModels.size(); ++k)
                        {
                            ((ModelRenderer)this.childModels.get(k)).render(scale);
                        }
                    }

                    if (flag)
                    {
                        GlStateManager.scale(1.0F / this.scaleX, 1.0F / this.scaleY, 1.0F / this.scaleZ);
                    }

                    GlStateManager.translate(-this.rotationPointX * scale, -this.rotationPointY * scale, -this.rotationPointZ * scale);
                }
            }
            else
            {
                GlStateManager.pushMatrix();
                GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

                if (this.rotateAngleZ != 0.0F)
                {
                    GlStateManager.rotate(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                }

                if (this.rotateAngleY != 0.0F)
                {
                    GlStateManager.rotate(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                }

                if (this.rotateAngleX != 0.0F)
                {
                    GlStateManager.rotate(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                }

                if (flag)
                {
                    GlStateManager.scale(this.scaleX, this.scaleY, this.scaleZ);
                }

                GlStateManager.callList(this.displayList);

                if (this.childModels != null)
                {
                    for (int j = 0; j < this.childModels.size(); ++j)
                    {
                        ((ModelRenderer)this.childModels.get(j)).render(scale);
                    }
                }

                GlStateManager.popMatrix();
            }

            GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);

            if (i != 0)
            {
                GlStateManager.bindTexture(i);
            }
        }
    }
    /**
     * Compiles a GL display list for this model
     */
    protected void compileDisplayList(float scale)
    {
        if (this.displayList == 0)
        {
            this.displayList = GLAllocation.generateDisplayLists(1);
        }

        GlStateManager.glNewList(this.displayList, 4864);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        
        for (int i = 0; i < this.cubes.size(); ++i)
        {
            ((ModelCube)this.cubes.get(i)).render(bufferbuilder, scale);
        }
        
        for (int j = 0; j < this.spriteList.size(); ++j)
        {
            ModelSprite modelsprite = (ModelSprite)this.spriteList.get(j);
            modelsprite.render(Tessellator.getInstance(), scale);
        }

        GlStateManager.glEndList();
        this.compiled = true;
    }
    
    public ModelBone setOffset(float x, float y, float z) {
    	this.offsetX = x;
    	this.offsetY = y;
    	this.offsetZ = z;
    	return this;
    }
    
    public String getName() {
		return boxName;
	}
    
    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("name: " + this.boxName + ", boxes: " + (this.cubes != null ? this.cubes.size() : null) + ", submodels: " + (this.childModels != null ? this.childModels.size() : null));
        stringbuffer.append("\nparent: " + (this.parent != null ? this.parent.getName() : null));
        stringbuffer.append("\nrotationPoint: [" + this.rotationPointX + ", " + this.rotationPointY + ", " + this.rotationPointZ + "]");
        stringbuffer.append("\nrotateAngle: [" + this.rotateAngleX + ", " + this.rotateAngleY + ", " + this.rotateAngleZ + "]");
        return stringbuffer.toString();
    }
}
