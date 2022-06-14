package fz.frazionz.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelDemonGolem extends ModelBase
{
	private final ModelRenderer rightLeg;
	private final ModelRenderer leg4_r1;
	private final ModelRenderer leg3_r1;
	private final ModelRenderer leg2_r1;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leg4_r2;
	private final ModelRenderer leg3_r2;
	private final ModelRenderer leg2_r2;
	private final ModelRenderer body;
	private final ModelRenderer torse_r1;
	private final ModelRenderer nuque_r1;
	private final ModelRenderer head;
	private final ModelRenderer head_spike_2_r1;
	private final ModelRenderer head_spike_base_2_r1;
	private final ModelRenderer head_spike_1_r1;
	private final ModelRenderer head_spike_base_1_r1;
	private final ModelRenderer head_back_spike_1_r1;
	private final ModelRenderer rightArm;
	private final ModelRenderer bras_r1;
	private final ModelRenderer epaule_r1;
	private final ModelRenderer pic_mid_r1;
	private final ModelRenderer pic_r1;
	private final ModelRenderer avant_bras;
	private final ModelRenderer main_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer bras_r2;
	private final ModelRenderer epaule_r2;
	private final ModelRenderer pic_mid_r2;
	private final ModelRenderer pic_r2;
	private final ModelRenderer avant_bras2;
	private final ModelRenderer main_r2;

	public ModelDemonGolem() {
		textureWidth = 256;
		textureHeight = 256;

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-6.0F, 1.0F, 0.0F);
		rightLeg.cubeList.add(new ModelBox(rightLeg, 105, 84, -4.0F, 21.0F, -2.5F, 6, 2, 7, 0.0F, false));
		rightLeg.cubeList.add(new ModelBox(rightLeg, 38, 113, -3.0F, 19.0F, -0.632F, 4, 2, 4, 0.4F, false));

		leg4_r1 = new ModelRenderer(this);
		leg4_r1.setRotationPoint(0.4262F, 2.4797F, 1.156F);
		rightLeg.addChild(leg4_r1);
		setRotationAngle(leg4_r1, 0.0F, 0.0F, 0.1309F);
		leg4_r1.cubeList.add(new ModelBox(leg4_r1, 84, 91, -3.5F, -3.5F, -3.176F, 7, 7, 7, 0.4F, false));

		leg3_r1 = new ModelRenderer(this);
		leg3_r1.setRotationPoint(-0.5F, 11.788F, 0.44F);
		rightLeg.addChild(leg3_r1);
		setRotationAngle(leg3_r1, -0.2162F, 0.0227F, 0.1731F);
		leg3_r1.cubeList.add(new ModelBox(leg3_r1, 102, 22, -3.332F, -8.096F, -3.328F, 6, 10, 6, 0.4F, false));

		leg2_r1 = new ModelRenderer(this);
		leg2_r1.setRotationPoint(-0.5F, 16.436F, 0.636F);
		rightLeg.addChild(leg2_r1);
		setRotationAngle(leg2_r1, 0.2618F, 0.0F, 0.0F);
		leg2_r1.cubeList.add(new ModelBox(leg2_r1, 111, 73, -2.5F, -3.82F, -2.096F, 4, 7, 4, 0.65F, false));

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(6.0F, 1.0F, 0.0F);
		leftLeg.cubeList.add(new ModelBox(leftLeg, 66, 105, -2.0F, 21.0F, -2.5F, 6, 2, 7, 0.0F, false));
		leftLeg.cubeList.add(new ModelBox(leftLeg, 56, 105, -1.0F, 19.0F, -0.632F, 4, 2, 4, 0.4F, false));

		leg4_r2 = new ModelRenderer(this);
		leg4_r2.setRotationPoint(-0.4262F, 2.4797F, 1.156F);
		leftLeg.addChild(leg4_r2);
		setRotationAngle(leg4_r2, 0.0F, 0.0F, -0.1309F);
		leg4_r2.cubeList.add(new ModelBox(leg4_r2, 56, 91, -3.5F, -3.5F, -3.176F, 7, 7, 7, 0.4F, false));

		leg3_r2 = new ModelRenderer(this);
		leg3_r2.setRotationPoint(0.5F, 11.788F, 0.44F);
		leftLeg.addChild(leg3_r2);
		setRotationAngle(leg3_r2, -0.2162F, -0.0227F, -0.1731F);
		leg3_r2.cubeList.add(new ModelBox(leg3_r2, 28, 95, -2.668F, -8.096F, -3.328F, 6, 10, 6, 0.4F, false));

		leg2_r2 = new ModelRenderer(this);
		leg2_r2.setRotationPoint(0.5F, 16.436F, 0.636F);
		leftLeg.addChild(leg2_r2);
		setRotationAngle(leg2_r2, 0.2618F, 0.0F, 0.0F);
		leg2_r2.cubeList.add(new ModelBox(leg2_r2, 22, 111, -1.5F, -3.82F, -2.096F, 4, 7, 4, 0.65F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -14.7712F, 0.1776F);
		setRotationAngle(body, 0.0873F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 22, -9.5F, 0.6672F, -3.9576F, 19, 15, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 109, -5.0F, 0.2112F, -4.4816F, 10, 10, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 92, 108, -5.0F, 0.2112F, 3.7184F, 10, 10, 1, 0.0F, false));

		torse_r1 = new ModelRenderer(this);
		torse_r1.setRotationPoint(0.0F, -5.3328F, -0.7576F);
		body.addChild(torse_r1);
		setRotationAngle(torse_r1, 0.1309F, 0.0F, 0.0F);
		torse_r1.cubeList.add(new ModelBox(torse_r1, 0, 0, -10.0F, -5.72F, -5.0F, 20, 12, 10, 0.4F, false));

		nuque_r1 = new ModelRenderer(this);
		nuque_r1.setRotationPoint(0.512F, -13.8328F, -0.7576F);
		body.addChild(nuque_r1);
		setRotationAngle(nuque_r1, 0.2618F, 0.0F, 0.0F);
		nuque_r1.cubeList.add(new ModelBox(nuque_r1, 27, 80, -5.012F, -2.424F, -6.056F, 9, 6, 9, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(-0.416F, -27.176F, -4.048F);
		

		head_spike_2_r1 = new ModelRenderer(this);
		head_spike_2_r1.setRotationPoint(5.52F, 2.7974F, -4.0F);
		head.addChild(head_spike_2_r1);
		setRotationAngle(head_spike_2_r1, 0.2182F, -0.1745F, 0.0F);
		head_spike_2_r1.cubeList.add(new ModelBox(head_spike_2_r1, 54, 113, 0.344F, -3.5F, -5.4322F, 2, 2, 5, 0.0F, false));

		head_spike_base_2_r1 = new ModelRenderer(this);
		head_spike_base_2_r1.setRotationPoint(-4.0F, -1.9266F, -4.0F);
		head.addChild(head_spike_base_2_r1);
		setRotationAngle(head_spike_base_2_r1, 0.2182F, 0.1309F, 0.0F);
		head_spike_base_2_r1.cubeList.add(new ModelBox(head_spike_base_2_r1, 111, 12, -2.42F, -1.368F, -2.9002F, 3, 4, 4, 0.0F, false));

		head_spike_1_r1 = new ModelRenderer(this);
		head_spike_1_r1.setRotationPoint(-4.52F, 2.7974F, -4.0F);
		head.addChild(head_spike_1_r1);
		setRotationAngle(head_spike_1_r1, 0.2182F, 0.1745F, 0.0F);
		head_spike_1_r1.cubeList.add(new ModelBox(head_spike_1_r1, 77, 91, -2.344F, -3.5F, -5.4322F, 2, 2, 5, 0.0F, false));

		head_spike_base_1_r1 = new ModelRenderer(this);
		head_spike_base_1_r1.setRotationPoint(5.0F, -1.9266F, -4.0F);
		head.addChild(head_spike_base_1_r1);
		setRotationAngle(head_spike_base_1_r1, 0.2182F, -0.1309F, 0.0F);
		head_spike_base_1_r1.cubeList.add(new ModelBox(head_spike_base_1_r1, 112, 50, -0.58F, -1.368F, -2.9002F, 3, 4, 4, 0.0F, false));

		head_back_spike_1_r1 = new ModelRenderer(this);
		head_back_spike_1_r1.setRotationPoint(0.5F, -6.26F, -0.104F);
		head.addChild(head_back_spike_1_r1);
		setRotationAngle(head_back_spike_1_r1, 0.0873F, 0.0F, 0.0F);
		head_back_spike_1_r1.cubeList.add(new ModelBox(head_back_spike_1_r1, 42, 101, -7.76F, -7.552F, 2.416F, 2, 2, 10, 0.0F, false));
		head_back_spike_1_r1.cubeList.add(new ModelBox(head_back_spike_1_r1, 102, 60, 4.0F, -7.0F, -2.0F, 3, 3, 10, 0.0F, false));
		head_back_spike_1_r1.cubeList.add(new ModelBox(head_back_spike_1_r1, 100, 38, 5.76F, -7.552F, 2.416F, 2, 2, 10, 0.0F, false));
		head_back_spike_1_r1.cubeList.add(new ModelBox(head_back_spike_1_r1, 102, 95, -7.0F, -7.0F, -2.0F, 3, 3, 10, 0.0F, false));
		head_back_spike_1_r1.cubeList.add(new ModelBox(head_back_spike_1_r1, 42, 33, -6.0F, -6.0F, -6.0F, 12, 12, 12, 0.2F, false));
		head_back_spike_1_r1.cubeList.add(new ModelBox(head_back_spike_1_r1, 0, 45, -6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F, false));

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-10.0F, -21.0F, 0.0F);
		setRotationAngle(rightArm, 0.0873F, 0.0F, 0.0F);
		

		bras_r1 = new ModelRenderer(this);
		bras_r1.setRotationPoint(-6.188F, 8.316F, -1.69F);
		rightArm.addChild(bras_r1);
		setRotationAngle(bras_r1, 0.0873F, 0.0F, 0.0F);
		bras_r1.cubeList.add(new ModelBox(bras_r1, 75, 71, -4.5F, -5.5F, -3.96F, 9, 11, 9, 0.0F, false));

		epaule_r1 = new ModelRenderer(this);
		epaule_r1.setRotationPoint(-5.0F, -1.5F, -1.69F);
		rightArm.addChild(epaule_r1);
		setRotationAngle(epaule_r1, 0.0F, 0.0F, 0.1309F);
		epaule_r1.cubeList.add(new ModelBox(epaule_r1, 54, 10, -6.0F, -5.5F, -6.0F, 12, 11, 12, 0.0F, false));
		epaule_r1.cubeList.add(new ModelBox(epaule_r1, 90, 12, -5.5F, -6.404F, -4.484F, 6, 1, 9, 0.0F, false));

		pic_mid_r1 = new ModelRenderer(this);
		pic_mid_r1.setRotationPoint(-6.4668F, -9.6653F, -1.474F);
		rightArm.addChild(pic_mid_r1);
		setRotationAngle(pic_mid_r1, -0.1745F, 0.0F, 0.1309F);
		pic_mid_r1.cubeList.add(new ModelBox(pic_mid_r1, 54, 80, -2.0F, -0.372F, -2.5F, 4, 3, 5, 0.0F, false));

		pic_r1 = new ModelRenderer(this);
		pic_r1.setRotationPoint(-5.9641F, -13.3916F, -1.438F);
		rightArm.addChild(pic_r1);
		setRotationAngle(pic_r1, -0.3491F, 0.0F, 0.1309F);
		pic_r1.cubeList.add(new ModelBox(pic_r1, 0, 22, -1.0F, -1.388F, 0.208F, 2, 5, 2, 0.0F, false));

		avant_bras = new ModelRenderer(this);
		avant_bras.setRotationPoint(-6.356F, 14.0F, 2.354F);
		rightArm.addChild(avant_bras);
		setRotationAngle(avant_bras, -0.0873F, 0.0F, 0.0F);
		

		main_r1 = new ModelRenderer(this);
		main_r1.setRotationPoint(0.0F, 3.5F, -3.94F);
		avant_bras.addChild(main_r1);
		setRotationAngle(main_r1, -0.1309F, 0.0F, 0.0F);
		main_r1.cubeList.add(new ModelBox(main_r1, 90, 0, -4.076F, 4.028F, -3.576F, 8, 4, 8, 0.0F, false));
		main_r1.cubeList.add(new ModelBox(main_r1, 0, 89, -3.5F, -5.48F, -3.136F, 7, 13, 7, 0.0F, false));

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(10.0F, -21.0F, 0.0F);
		setRotationAngle(leftArm, 0.0873F, 0.0F, 0.0F);
		

		bras_r2 = new ModelRenderer(this);
		bras_r2.setRotationPoint(6.188F, 8.316F, -1.69F);
		leftArm.addChild(bras_r2);
		setRotationAngle(bras_r2, 0.0873F, 0.0F, 0.0F);
		bras_r2.cubeList.add(new ModelBox(bras_r2, 0, 69, -4.5F, -5.5F, -3.96F, 9, 11, 9, 0.0F, false));

		epaule_r2 = new ModelRenderer(this);
		epaule_r2.setRotationPoint(5.0F, -1.5F, -1.69F);
		leftArm.addChild(epaule_r2);
		setRotationAngle(epaule_r2, 0.0F, 0.0F, -0.1309F);
		epaule_r2.cubeList.add(new ModelBox(epaule_r2, 36, 57, -6.0F, -5.5F, -6.0F, 12, 11, 12, 0.0F, false));
		epaule_r2.cubeList.add(new ModelBox(epaule_r2, 50, 0, -0.5F, -6.404F, -4.484F, 6, 1, 9, 0.0F, false));

		pic_mid_r2 = new ModelRenderer(this);
		pic_mid_r2.setRotationPoint(6.4668F, -9.6653F, -1.474F);
		leftArm.addChild(pic_mid_r2);
		setRotationAngle(pic_mid_r2, -0.1745F, 0.0F, -0.1309F);
		pic_mid_r2.cubeList.add(new ModelBox(pic_mid_r2, 71, 0, -2.0F, -0.372F, -2.5F, 4, 3, 5, 0.0F, false));

		pic_r2 = new ModelRenderer(this);
		pic_r2.setRotationPoint(5.9641F, -13.3916F, -1.438F);
		leftArm.addChild(pic_r2);
		setRotationAngle(pic_r2, -0.3491F, 0.0F, -0.1309F);
		pic_r2.cubeList.add(new ModelBox(pic_r2, 0, 0, -1.0F, -1.388F, 0.208F, 2, 5, 2, 0.0F, false));

		avant_bras2 = new ModelRenderer(this);
		avant_bras2.setRotationPoint(-26.356F, 14.0F, 2.354F);
		leftArm.addChild(avant_bras2);
		setRotationAngle(avant_bras2, -0.0873F, 0.0F, 0.0F);
		

		main_r2 = new ModelRenderer(this);
		main_r2.setRotationPoint(32.712F, 3.5F, -3.94F);
		avant_bras2.addChild(main_r2);
		setRotationAngle(main_r2, -0.1309F, 0.0F, 0.0F);
		main_r2.cubeList.add(new ModelBox(main_r2, 78, 33, -3.924F, 4.028F, -3.576F, 8, 4, 8, 0.0F, false));
		main_r2.cubeList.add(new ModelBox(main_r2, 84, 50, -3.5F, -5.48F, -3.136F, 7, 13, 7, 0.0F, false));
	
		head.offsetY = -1.8f;
		rightLeg.offsetY = -0.69f;
		leftLeg.offsetY = -0.69f;
		rightArm.offsetX = -0.98f;
		leftArm.offsetX = 0.98f;
		rightArm.offsetY = -0.05f;
		leftArm.offsetY = -0.05f;
		body.offsetY = -0.05f;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);	
		rightArm.offsetX = -0.35f;
		leftArm.offsetX = 0.35f;
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
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag)
        {
            this.head.rotateAngleX = -((float)Math.PI / 4F);
        }
        else
        {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }

        this.body.rotateAngleY = 0.0F;
        this.rightArm.rotationPointZ = 0.0F;
        this.rightArm.rotationPointX = -5.0F;
        this.leftArm.rotationPointZ = 0.0F;
        this.leftArm.rotationPointX = 5.0F;
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

        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;

        if (this.isRiding)
        {
            this.rightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.leftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.rightLeg.rotateAngleX = -1.4137167F;
            this.rightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.rightLeg.rotateAngleZ = 0.07853982F;
            this.leftLeg.rotateAngleX = -1.4137167F;
            this.leftLeg.rotateAngleY = -((float)Math.PI / 10F);
            this.leftLeg.rotateAngleZ = -0.07853982F;
        }

        this.rightArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;

        this.body.rotateAngleX = 0.0F;
        this.rightLeg.rotationPointZ = 0.1F;
        this.leftLeg.rotationPointZ = 0.1F;
        this.rightLeg.rotationPointY = 12.0F;
        this.leftLeg.rotationPointY = 12.0F;
        this.head.rotationPointY = 0.0F;

        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    public void setInvisible(boolean invisible)
    {
        this.head.showModel = invisible;
        this.body.showModel = invisible;
        this.rightArm.showModel = invisible;
        this.leftArm.showModel = invisible;
        this.rightLeg.showModel = invisible;
        this.leftLeg.showModel = invisible;
    }
}
