package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelDemonZ extends ModelBase
{
	private final ModelRenderer bipedHead;
	private final ModelRenderer headLayerTop_r1;
	private final ModelRenderer hornRight;
	private final ModelRenderer hornEnd_r1;
	private final ModelRenderer hornMid_r1;
	private final ModelRenderer hornBase_r1;
	private final ModelRenderer hornLeft;
	private final ModelRenderer hornEnd_r2;
	private final ModelRenderer hornMid_r2;
	private final ModelRenderer hornBase_r2;
	private final ModelRenderer bipedBody;
	private final ModelRenderer nuque_r1;
	private final ModelRenderer horn;
	private final ModelRenderer hornLeft1;
	private final ModelRenderer HornEnd_r3;
	private final ModelRenderer HornBase_r3;
	private final ModelRenderer hornLeft2;
	private final ModelRenderer hornEnd_r4;
	private final ModelRenderer hornBase_r4;
	private final ModelRenderer hornRight1;
	private final ModelRenderer hornEnd_r5;
	private final ModelRenderer hornBase_r5;
	private final ModelRenderer hornRight2;
	private final ModelRenderer hornEnd_r6;
	private final ModelRenderer hornBase_r6;
	private final ModelRenderer bipedRightArm;
	private final ModelRenderer biceps_r1;
	private final ModelRenderer epaule_r1;
	private final ModelRenderer avant_bras2;
	private final ModelRenderer harmHorn_r1;
	private final ModelRenderer harmHorn_r2;
	private final ModelRenderer bipedLeftArm;
	private final ModelRenderer biceps_r2;
	private final ModelRenderer avant_bras;
	private final ModelRenderer harmHorn_r3;
	private final ModelRenderer harmHorn_r4;
	private final ModelRenderer bipedRightLeg;
	private final ModelRenderer bipedRightLeg_r1;
	private final ModelRenderer bipedRightLeg_r2;
	private final ModelRenderer bipedLeftLeg;
	private final ModelRenderer bipedRightLeg_r3;
	private final ModelRenderer bipedRightLeg_r4;

	public ModelDemonZ() {
		textureWidth = 128;
		textureHeight = 128;

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, -6.0F, 0.0F);
		

		headLayerTop_r1 = new ModelRenderer(this);
		headLayerTop_r1.setRotationPoint(0.0F, -2.372F, 0.525F);
		bipedHead.addChild(headLayerTop_r1);
		setRotationAngle(headLayerTop_r1, 0.0873F, 0.0F, 0.0F);
		headLayerTop_r1.cubeList.add(new ModelBox(headLayerTop_r1, 32, 0, -4.0F, -8.088F, -3.997F, 8, 8, 8, 0.2F, false));
		headLayerTop_r1.cubeList.add(new ModelBox(headLayerTop_r1, 0, 0, -4.0F, -8.088F, -3.997F, 8, 8, 8, 0.0F, false));

		hornRight = new ModelRenderer(this);
		hornRight.setRotationPoint(0.0F, 0.0F, 0.525F);
		bipedHead.addChild(hornRight);
		setRotationAngle(hornRight, 0.0873F, 0.0F, 0.1745F);
		

		hornEnd_r1 = new ModelRenderer(this);
		hornEnd_r1.setRotationPoint(-10.289F, -10.8781F, 2.9288F);
		hornRight.addChild(hornEnd_r1);
		setRotationAngle(hornEnd_r1, 1.4399F, 0.0F, -0.9599F);
		hornEnd_r1.cubeList.add(new ModelBox(hornEnd_r1, 8, 24, 1.17F, -2.1884F, -0.3476F, 1, 7, 1, 0.1F, true));

		hornMid_r1 = new ModelRenderer(this);
		hornMid_r1.setRotationPoint(-1.312F, -0.064F, 0.404F);
		hornRight.addChild(hornMid_r1);
		setRotationAngle(hornMid_r1, 2.2689F, 0.0F, -0.9599F);
		hornMid_r1.cubeList.add(new ModelBox(hornMid_r1, 0, 23, 4.3474F, 2.7267F, 9.0319F, 2, 7, 2, 0.0F, true));

		hornBase_r1 = new ModelRenderer(this);
		hornBase_r1.setRotationPoint(-1.312F, -0.064F, 0.404F);
		hornRight.addChild(hornBase_r1);
		setRotationAngle(hornBase_r1, -2.7489F, 0.0F, -0.9599F);
		hornBase_r1.cubeList.add(new ModelBox(hornBase_r1, 0, 16, 3.7194F, 7.3709F, -1.5742F, 3, 4, 3, 0.0F, true));

		hornLeft = new ModelRenderer(this);
		hornLeft.setRotationPoint(0.0F, 0.0F, 0.525F);
		bipedHead.addChild(hornLeft);
		setRotationAngle(hornLeft, 0.0873F, 0.0F, -0.1745F);
		

		hornEnd_r2 = new ModelRenderer(this);
		hornEnd_r2.setRotationPoint(10.289F, -10.8781F, 2.9288F);
		hornLeft.addChild(hornEnd_r2);
		setRotationAngle(hornEnd_r2, 1.4399F, 0.0F, 0.9599F);
		hornEnd_r2.cubeList.add(new ModelBox(hornEnd_r2, 20, 24, -2.17F, -2.1884F, -0.3476F, 1, 7, 1, 0.1F, false));

		hornMid_r2 = new ModelRenderer(this);
		hornMid_r2.setRotationPoint(1.312F, -0.064F, 0.404F);
		hornLeft.addChild(hornMid_r2);
		setRotationAngle(hornMid_r2, 2.2689F, 0.0F, 0.9599F);
		hornMid_r2.cubeList.add(new ModelBox(hornMid_r2, 12, 23, -6.3474F, 2.7267F, 9.0319F, 2, 7, 2, 0.0F, false));

		hornBase_r2 = new ModelRenderer(this);
		hornBase_r2.setRotationPoint(1.312F, -0.064F, 0.404F);
		hornLeft.addChild(hornBase_r2);
		setRotationAngle(hornBase_r2, -2.7489F, 0.0F, 0.9599F);
		hornBase_r2.cubeList.add(new ModelBox(hornBase_r2, 12, 16, -6.7194F, 7.3709F, -1.5742F, 3, 4, 3, 0.0F, false));

		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(-0.012F, 2.288F, 0.364F);
		bipedBody.cubeList.add(new ModelBox(bipedBody, 0, 43, -5.0F, -5.372F, -1.476F, 10, 12, 4, 0.0F, false));
		bipedBody.cubeList.add(new ModelBox(bipedBody, 0, 32, -5.488F, -9.508F, -1.96F, 11, 6, 5, 0.05F, false));

		nuque_r1 = new ModelRenderer(this);
		nuque_r1.setRotationPoint(-0.424F, -10.46F, 0.528F);
		bipedBody.addChild(nuque_r1);
		setRotationAngle(nuque_r1, 0.1309F, 0.0F, 0.0F);
		nuque_r1.cubeList.add(new ModelBox(nuque_r1, 26, 16, -2.064F, -2.568F, -2.656F, 5, 4, 5, 0.0F, false));

		horn = new ModelRenderer(this);
		horn.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(horn);
		

		hornLeft1 = new ModelRenderer(this);
		hornLeft1.setRotationPoint(1.012F, -4.288F, 0.636F);
		horn.addChild(hornLeft1);
		setRotationAngle(hornLeft1, -0.9163F, 1.1345F, -1.8326F);
		

		HornEnd_r3 = new ModelRenderer(this);
		HornEnd_r3.setRotationPoint(-6.289F, -1.8781F, 7.4538F);
		hornLeft1.addChild(HornEnd_r3);
		setRotationAngle(HornEnd_r3, 1.4399F, 0.0F, -0.9599F);
		HornEnd_r3.cubeList.add(new ModelBox(HornEnd_r3, 67, 57, 1.0987F, -0.4277F, -1.8655F, 1, 7, 1, 0.12F, true));

		HornBase_r3 = new ModelRenderer(this);
		HornBase_r3.setRotationPoint(2.688F, 8.936F, 4.929F);
		hornLeft1.addChild(HornBase_r3);
		setRotationAngle(HornBase_r3, 2.2689F, 0.0F, -0.9599F);
		HornBase_r3.cubeList.add(new ModelBox(HornBase_r3, 67, 48, 4.2762F, 2.7971F, 6.7082F, 2, 7, 2, 0.1F, true));

		hornLeft2 = new ModelRenderer(this);
		hornLeft2.setRotationPoint(1.012F, -4.288F, 0.636F);
		horn.addChild(hornLeft2);
		setRotationAngle(hornLeft2, -0.9163F, 1.1345F, -1.8326F);
		

		hornEnd_r4 = new ModelRenderer(this);
		hornEnd_r4.setRotationPoint(-6.9562F, 3.0551F, -0.9253F);
		hornLeft2.addChild(hornEnd_r4);
		setRotationAngle(hornEnd_r4, 1.7453F, -0.1309F, -0.9163F);
		hornEnd_r4.cubeList.add(new ModelBox(hornEnd_r4, 67, 71, 0.8428F, -0.8386F, -0.6182F, 1, 4, 1, 0.1F, false));

		hornBase_r4 = new ModelRenderer(this);
		hornBase_r4.setRotationPoint(-5.8322F, 3.8812F, -3.7781F);
		hornLeft2.addChild(hornBase_r4);
		setRotationAngle(hornBase_r4, 2.5744F, -0.2618F, -1.0472F);
		hornBase_r4.cubeList.add(new ModelBox(hornBase_r4, 67, 66, 0.9863F, -0.982F, -2.1885F, 2, 3, 2, 0.0F, true));

		hornRight1 = new ModelRenderer(this);
		hornRight1.setRotationPoint(-0.988F, -4.288F, 0.636F);
		horn.addChild(hornRight1);
		setRotationAngle(hornRight1, -0.9163F, -1.1345F, 1.8326F);
		

		hornEnd_r5 = new ModelRenderer(this);
		hornEnd_r5.setRotationPoint(6.289F, -1.8781F, 7.4538F);
		hornRight1.addChild(hornEnd_r5);
		setRotationAngle(hornEnd_r5, 1.4399F, 0.0F, 0.9599F);
		hornEnd_r5.cubeList.add(new ModelBox(hornEnd_r5, 76, 57, -2.0987F, -0.4277F, -1.8655F, 1, 7, 1, 0.12F, false));

		hornBase_r5 = new ModelRenderer(this);
		hornBase_r5.setRotationPoint(-2.688F, 8.936F, 4.929F);
		hornRight1.addChild(hornBase_r5);
		setRotationAngle(hornBase_r5, 2.2689F, 0.0F, 0.9599F);
		hornBase_r5.cubeList.add(new ModelBox(hornBase_r5, 76, 48, -6.2762F, 2.7971F, 6.7082F, 2, 7, 2, 0.1F, false));

		hornRight2 = new ModelRenderer(this);
		hornRight2.setRotationPoint(-0.988F, -4.288F, 0.636F);
		horn.addChild(hornRight2);
		setRotationAngle(hornRight2, -0.9163F, -1.1345F, 1.8326F);
		

		hornEnd_r6 = new ModelRenderer(this);
		hornEnd_r6.setRotationPoint(6.9562F, 3.0551F, -0.9253F);
		hornRight2.addChild(hornEnd_r6);
		setRotationAngle(hornEnd_r6, 1.7453F, 0.1309F, 0.9163F);
		hornEnd_r6.cubeList.add(new ModelBox(hornEnd_r6, 75, 71, -1.8428F, -0.8346F, -0.7182F, 1, 4, 1, 0.1F, false));

		hornBase_r6 = new ModelRenderer(this);
		hornBase_r6.setRotationPoint(5.8322F, 3.8812F, -3.7781F);
		hornRight2.addChild(hornBase_r6);
		setRotationAngle(hornBase_r6, 2.5744F, 0.2618F, 1.0472F);
		hornBase_r6.cubeList.add(new ModelBox(hornBase_r6, 75, 66, -2.9863F, -0.982F, -2.1885F, 2, 3, 2, 0.0F, false));

		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(-5.2F, -4.0F, 0.0F);
		

		biceps_r1 = new ModelRenderer(this);
		biceps_r1.setRotationPoint(-2.0F, 1.488F, 1.0F);
		bipedRightArm.addChild(biceps_r1);
		setRotationAngle(biceps_r1, 0.0873F, 0.0F, 0.1309F);
		biceps_r1.cubeList.add(new ModelBox(biceps_r1, 34, 41, -2.516F, -5.008F, -1.816F, 4, 9, 4, 0.0F, false));

		epaule_r1 = new ModelRenderer(this);
		epaule_r1.setRotationPoint(-5.08F, 1.488F, 1.0F);
		bipedRightArm.addChild(epaule_r1);
		setRotationAngle(epaule_r1, 0.0873F, 0.0F, 0.1309F);
		epaule_r1.cubeList.add(new ModelBox(epaule_r1, 34, 33, 0.476F, -5.34F, -1.852F, 4, 4, 4, 0.3F, true));

		avant_bras2 = new ModelRenderer(this);
		avant_bras2.setRotationPoint(-2.95F, 7.8F, 1.0F);
		bipedRightArm.addChild(avant_bras2);
		setRotationAngle(avant_bras2, -0.2618F, 0.0F, 0.0436F);
		avant_bras2.cubeList.add(new ModelBox(avant_bras2, 34, 54, -1.7208F, -3.263F, -1.6149F, 3, 8, 3, 0.0F, true));
		avant_bras2.cubeList.add(new ModelBox(avant_bras2, 67, 38, -2.2405F, -0.0113F, -0.6728F, 2, 2, 1, -0.1F, false));

		harmHorn_r1 = new ModelRenderer(this);
		harmHorn_r1.setRotationPoint(-1.641F, 4.1057F, 2.2326F);
		avant_bras2.addChild(harmHorn_r1);
		setRotationAngle(harmHorn_r1, 0.0F, 0.0F, -0.0873F);
		harmHorn_r1.cubeList.add(new ModelBox(harmHorn_r1, 67, 44, -1.3276F, -5.7213F, -2.9053F, 1, 2, 1, -0.27F, false));

		harmHorn_r2 = new ModelRenderer(this);
		harmHorn_r2.setRotationPoint(-1.641F, 4.1057F, 2.2326F);
		avant_bras2.addChild(harmHorn_r2);
		setRotationAngle(harmHorn_r2, 0.0F, 0.0F, -0.6545F);
		harmHorn_r2.cubeList.add(new ModelBox(harmHorn_r2, 67, 41, 1.0074F, -4.143F, -2.9053F, 1, 2, 1, -0.2F, false));

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(5.2F, -4.0F, 0.0F);
		

		biceps_r2 = new ModelRenderer(this);
		biceps_r2.setRotationPoint(5.08F, 1.488F, 1.0F);
		bipedLeftArm.addChild(biceps_r2);
		setRotationAngle(biceps_r2, 0.0873F, 0.0F, -0.1309F);
		biceps_r2.cubeList.add(new ModelBox(biceps_r2, 50, 41, -4.516F, -5.384F, -1.816F, 4, 9, 4, 0.0F, false));
		biceps_r2.cubeList.add(new ModelBox(biceps_r2, 50, 33, -4.476F, -5.34F, -1.852F, 4, 4, 4, 0.3F, false));

		avant_bras = new ModelRenderer(this);
		avant_bras.setRotationPoint(2.95F, 7.8F, 1.0F);
		bipedLeftArm.addChild(avant_bras);
		setRotationAngle(avant_bras, -0.2618F, 0.0F, -0.0436F);
		avant_bras.cubeList.add(new ModelBox(avant_bras, 50, 54, -1.2792F, -3.263F, -1.6149F, 3, 8, 3, 0.0F, false));
		avant_bras.cubeList.add(new ModelBox(avant_bras, 74, 38, 0.2405F, -0.0113F, -0.6728F, 2, 2, 1, -0.1F, true));

		harmHorn_r3 = new ModelRenderer(this);
		harmHorn_r3.setRotationPoint(1.641F, 4.1057F, 2.2326F);
		avant_bras.addChild(harmHorn_r3);
		setRotationAngle(harmHorn_r3, 0.0F, 0.0F, 0.0873F);
		harmHorn_r3.cubeList.add(new ModelBox(harmHorn_r3, 74, 44, 0.3276F, -5.7213F, -2.9053F, 1, 2, 1, -0.27F, true));

		harmHorn_r4 = new ModelRenderer(this);
		harmHorn_r4.setRotationPoint(1.641F, 4.1057F, 2.2326F);
		avant_bras.addChild(harmHorn_r4);
		setRotationAngle(harmHorn_r4, 0.0F, 0.0F, 0.6545F);
		harmHorn_r4.cubeList.add(new ModelBox(harmHorn_r4, 74, 41, -2.0074F, -4.143F, -2.9053F, 1, 2, 1, -0.2F, true));

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-3.0F, 11.2F, 0.0F);
		bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 85, -3.0F, 11.8F, -1.0F, 3, 1, 3, 0.15F, false));
		bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 79, -2.5F, 7.8F, -0.156F, 2, 4, 2, 0.15F, false));

		bipedRightLeg_r1 = new ModelRenderer(this);
		bipedRightLeg_r1.setRotationPoint(-0.7778F, 1.6029F, -0.192F);
		bipedRightLeg.addChild(bipedRightLeg_r1);
		setRotationAngle(bipedRightLeg_r1, -0.2182F, 0.0F, 0.3054F);
		bipedRightLeg_r1.cubeList.add(new ModelBox(bipedRightLeg_r1, 0, 59, -2.012F, -4.9219F, -2.008F, 4, 7, 4, 0.25F, false));

		bipedRightLeg_r2 = new ModelRenderer(this);
		bipedRightLeg_r2.setRotationPoint(-1.468F, 6.23F, 0.1622F);
		bipedRightLeg.addChild(bipedRightLeg_r2);
		setRotationAngle(bipedRightLeg_r2, 0.3054F, 0.0F, 0.0436F);
		bipedRightLeg_r2.cubeList.add(new ModelBox(bipedRightLeg_r2, 0, 70, -1.5F, -3.902F, -1.5F, 3, 6, 3, 0.16F, false));

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(3.0F, 11.2F, 0.0F);
		bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 16, 85, 0.0F, 11.8F, -1.0F, 3, 1, 3, 0.15F, false));
		bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 16, 79, 0.5F, 7.8F, -0.156F, 2, 4, 2, 0.15F, false));

		bipedRightLeg_r3 = new ModelRenderer(this);
		bipedRightLeg_r3.setRotationPoint(0.5852F, 1.529F, 0.3145F);
		bipedLeftLeg.addChild(bipedRightLeg_r3);
		setRotationAngle(bipedRightLeg_r3, -0.2182F, 0.0F, -0.3054F);
		bipedRightLeg_r3.cubeList.add(new ModelBox(bipedRightLeg_r3, 16, 59, -1.784F, -4.696F, -2.4665F, 4, 7, 4, 0.25F, false));

		bipedRightLeg_r4 = new ModelRenderer(this);
		bipedRightLeg_r4.setRotationPoint(1.5F, 5.3F, 0.844F);
		bipedLeftLeg.addChild(bipedRightLeg_r4);
		setRotationAngle(bipedRightLeg_r4, 0.3054F, 0.0F, -0.0436F);
		bipedRightLeg_r4.cubeList.add(new ModelBox(bipedRightLeg_r4, 16, 70, -1.5F, -3.34F, -2.312F, 3, 6, 3, 0.16F, false));
	
		bipedRightLeg.offsetY = -0.055f;
		bipedHead.offsetY = -0.405f;
		bipedBody.offsetY = -0.055f;
		bipedRightArm.offsetY = -0.055f;
		bipedLeftArm.offsetY = -0.055f;
		bipedRightLeg.offsetY = -0.055f;
		bipedLeftLeg.offsetY = -0.055f;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bipedHead.render(f5);
		bipedBody.render(f5);
		bipedRightArm.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);	
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}


    @SuppressWarnings("incomplete-switch")

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
        this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag)
        {
            this.bipedHead.rotateAngleX = -((float)Math.PI / 4F);
        }
        else
        {
            this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
        }

        this.bipedBody.rotateAngleY = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;
        float f = 1.0F;

        if (flag)
        {
            f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F)
        {
            f = 1.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -1.4137167F;
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedRightLeg.rotateAngleZ = 0.07853982F;
            this.bipedLeftLeg.rotateAngleX = -1.4137167F;
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;

        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.1F;
        this.bipedLeftLeg.rotationPointZ = 0.1F;
        this.bipedRightLeg.rotationPointY = 12.0F;
        this.bipedLeftLeg.rotationPointY = 12.0F;
        this.bipedHead.rotationPointY = 0.0F;

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    public void setInvisible(boolean invisible)
    {
        this.bipedHead.showModel = invisible;
        this.bipedBody.showModel = invisible;
        this.bipedRightArm.showModel = invisible;
        this.bipedLeftArm.showModel = invisible;
        this.bipedRightLeg.showModel = invisible;
        this.bipedLeftLeg.showModel = invisible;
    }
}
