package net.minecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelNewIllager extends ModelBase {
	
  public ModelRenderer head;
  public ModelRenderer hat;
  public ModelRenderer body;
  public ModelRenderer arms;
  public ModelRenderer leg0;
  public ModelRenderer leg1;
  public ModelRenderer nose;
  public ModelRenderer rightArm;
  public ModelRenderer leftArm;
  
  public ModelNewIllager(float scaleFactor, float p_i47227_2_, int textureWidthIn, int textureHeightIn) {
    this.head = (new ModelRenderer(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.head.setRotationPoint(0.0F, 0.0F + p_i47227_2_, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, scaleFactor);
    this.hat = (new ModelRenderer(this, 32, 0)).setTextureSize(textureWidthIn, textureHeightIn);
    this.hat.addBox(-4.0F, -10.0F, -4.0F, 8, 12, 8, scaleFactor + 0.45F);
    this.head.addChild(this.hat);
    this.hat.mirror = false;
    this.nose = (new ModelRenderer(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.nose.setRotationPoint(0.0F, p_i47227_2_ - 2.0F, 0.0F);
    this.nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, scaleFactor);
    this.head.addChild(this.nose);
    this.body = (new ModelRenderer(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.body.setRotationPoint(0.0F, 0.0F + p_i47227_2_, 0.0F);
    this.body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, scaleFactor);
    this.body.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, scaleFactor + 0.5F);
    this.arms = (new ModelRenderer(this)).setTextureSize(textureWidthIn, textureHeightIn);
    this.arms.setRotationPoint(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
    this.arms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, scaleFactor);
    ModelRenderer modelrenderer = (new ModelRenderer(this, 44, 22)).setTextureSize(textureWidthIn, textureHeightIn);
    modelrenderer.mirror = true;
    modelrenderer.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, scaleFactor);
    this.arms.addChild(modelrenderer);
    this.arms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, scaleFactor);
    this.leg0 = (new ModelRenderer(this, 0, 22)).setTextureSize(textureWidthIn, textureHeightIn);
    this.leg0.setRotationPoint(-2.0F, 12.0F + p_i47227_2_, 0.0F);
    this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
    this.leg1 = (new ModelRenderer(this, 0, 22)).setTextureSize(textureWidthIn, textureHeightIn);
    this.leg1.mirror = true;
    this.leg1.setRotationPoint(2.0F, 12.0F + p_i47227_2_, 0.0F);
    this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scaleFactor);
    this.rightArm = (new ModelRenderer(this, 40, 46)).setTextureSize(textureWidthIn, textureHeightIn);
    this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, scaleFactor);
    this.rightArm.setRotationPoint(-5.0F, 2.0F + p_i47227_2_, 0.0F);
    this.leftArm = (new ModelRenderer(this, 40, 46)).setTextureSize(textureWidthIn, textureHeightIn);
    this.leftArm.mirror = true;
    this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, scaleFactor);
    this.leftArm.setRotationPoint(5.0F, 2.0F + p_i47227_2_, 0.0F);
  }

  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    setRotateAngle(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    this.head.render(scale);
    this.body.render(scale);
    this.leg0.render(scale);
    this.leg1.render(scale);
    this.leftArm.render(scale);
    this.rightArm.render(scale);
  }






  
  public void setRotateAngle(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount * 0.5F;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount * 0.5F;
    this.leg0.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
    this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount * 0.5F;
    this.leg0.rotateAngleY = 0.0F;
    this.leg1.rotateAngleY = 0.0F;
  }

  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	    modelRenderer.rotateAngleX = x;
	    modelRenderer.rotateAngleY = y;
	    modelRenderer.rotateAngleZ = z;
  }

  
  public ModelRenderer getArm(EnumHandSide p_191216_1_) { return (p_191216_1_ == EnumHandSide.LEFT) ? this.leftArm : this.rightArm; }
}
