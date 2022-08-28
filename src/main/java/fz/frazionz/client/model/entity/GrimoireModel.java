package fz.frazionz.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GrimoireModel extends ModelBase {
	private final ModelRenderer grimoire;
	private final ModelRenderer back_r1;
	private final ModelRenderer left;
	private final ModelRenderer pages_left;
	private final ModelRenderer page_left_2_r1;
	private final ModelRenderer page_left_1_r1;
	private final ModelRenderer pages_pack_left;
	private final ModelRenderer left_little_sheet_2_r1;
	private final ModelRenderer left_little_sheet_1_r1;
	private final ModelRenderer right;
	private final ModelRenderer pages_right;
	private final ModelRenderer page_right_2_r1;
	private final ModelRenderer page_right_1_r1;
	private final ModelRenderer pages_pack_right;
	private final ModelRenderer right_little_sheet_1_r1;
	private final ModelRenderer right_little_sheet_1_r2;

	public static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/frazionz/grimoire/grimoire.png");
	
	public GrimoireModel() {
		textureWidth = 32;
		textureHeight = 32;

		grimoire = new ModelRenderer(this);
		grimoire.setRotationPoint(0.0F, 0.0F, 0.0F);
		grimoire.cubeList.add(new ModelBox(grimoire, 27, 22, -1.0F, -4.0F, 1.001F, 2, 7, 0, 0.0F, false));

		back_r1 = new ModelRenderer(this);
		back_r1.setRotationPoint(0.5F, -2.5F, 1.002F);
		grimoire.addChild(back_r1);
		setRotationAngle(back_r1, 0.0F, -0.1309F, 0.0F);
		back_r1.cubeList.add(new ModelBox(back_r1, 0, 13, -1.5F, -3.5F, 0.184F, 3, 7, 0, 0.0F, false));

		left = new ModelRenderer(this);
		left.setRotationPoint(-1.0F, -0.5F, 1.0F);
		grimoire.addChild(left);
		setRotationAngle(left, 0.0F, 1.1345F, 0.0F);
		left.cubeList.add(new ModelBox(left, 6, 8, -0.001F, -3.5F, -5.0F, 0, 7, 5, 0.0F, false));

		pages_left = new ModelRenderer(this);
		pages_left.setRotationPoint(1.0F, 0.0F, -3.368F);
		left.addChild(pages_left);
		setRotationAngle(pages_left, 0.0F, 0.0873F, 0.0F);
		

		page_left_2_r1 = new ModelRenderer(this);
		page_left_2_r1.setRotationPoint(0.0F, 0.0F, 3.868F);
		pages_left.addChild(page_left_2_r1);
		setRotationAngle(page_left_2_r1, 0.0436F, -0.0873F, 0.0F);
		page_left_2_r1.cubeList.add(new ModelBox(page_left_2_r1, 23, 0, 0.0413F, -3.0F, -3.88F, 0, 6, 4, 0.0F, false));

		page_left_1_r1 = new ModelRenderer(this);
		page_left_1_r1.setRotationPoint(0.0F, 0.0F, 3.868F);
		pages_left.addChild(page_left_1_r1);
		setRotationAngle(page_left_1_r1, -0.1309F, -0.0436F, 0.0F);
		page_left_1_r1.cubeList.add(new ModelBox(page_left_1_r1, 18, 22, 0.0413F, -3.0F, -3.88F, 0, 6, 4, 0.0F, false));

		pages_pack_left = new ModelRenderer(this);
		pages_pack_left.setRotationPoint(0.0F, 0.0F, 3.868F);
		pages_left.addChild(pages_pack_left);
		pages_pack_left.cubeList.add(new ModelBox(pages_pack_left, 12, 0, -0.9587F, -3.0F, -4.015F, 1, 6, 4, 0.0F, false));

		left_little_sheet_2_r1 = new ModelRenderer(this);
		left_little_sheet_2_r1.setRotationPoint(-0.3947F, 2.8356F, -2.6702F);
		pages_pack_left.addChild(left_little_sheet_2_r1);
		setRotationAngle(left_little_sheet_2_r1, 0.6981F, 0.0F, 0.0F);
		left_little_sheet_2_r1.cubeList.add(new ModelBox(left_little_sheet_2_r1, 18, 20, -0.336F, -2.488F, -0.284F, 0, 3, 2, 0.0F, false));

		left_little_sheet_1_r1 = new ModelRenderer(this);
		left_little_sheet_1_r1.setRotationPoint(-0.4547F, -1.884F, -2.88F);
		pages_pack_left.addChild(left_little_sheet_1_r1);
		setRotationAngle(left_little_sheet_1_r1, -0.3927F, 0.0F, 0.0F);
		left_little_sheet_1_r1.cubeList.add(new ModelBox(left_little_sheet_1_r1, 18, 20, 0.06F, -1.212F, -1.0F, 0, 3, 2, 0.0F, false));

		right = new ModelRenderer(this);
		right.setRotationPoint(1.0F, -0.5F, 1.0F);
		grimoire.addChild(right);
		setRotationAngle(right, 0.0F, -1.1345F, 0.0F);
		right.cubeList.add(new ModelBox(right, 0, 0, 0.002F, -3.5F, -5.0F, 0, 7, 5, 0.0F, false));

		pages_right = new ModelRenderer(this);
		pages_right.setRotationPoint(-1.0F, 0.0F, -3.364F);
		right.addChild(pages_right);
		setRotationAngle(pages_right, 0.0F, -0.0873F, 0.0F);
		

		page_right_2_r1 = new ModelRenderer(this);
		page_right_2_r1.setRotationPoint(0.0F, 0.0F, 3.864F);
		pages_right.addChild(page_right_2_r1);
		setRotationAngle(page_right_2_r1, -0.044F, 0.1308F, -0.0057F);
		page_right_2_r1.cubeList.add(new ModelBox(page_right_2_r1, 9, 21, -0.0413F, -3.0F, -3.879F, 0, 6, 4, 0.0F, false));

		page_right_1_r1 = new ModelRenderer(this);
		page_right_1_r1.setRotationPoint(0.0F, 0.0F, 3.864F);
		pages_right.addChild(page_right_1_r1);
		setRotationAngle(page_right_1_r1, 0.0873F, 0.0436F, 0.0F);
		page_right_1_r1.cubeList.add(new ModelBox(page_right_1_r1, 0, 17, -0.0413F, -3.0F, -3.879F, 0, 6, 4, 0.0F, false));

		pages_pack_right = new ModelRenderer(this);
		pages_pack_right.setRotationPoint(0.0F, 0.5F, 3.864F);
		pages_right.addChild(pages_pack_right);
		pages_pack_right.cubeList.add(new ModelBox(pages_pack_right, 17, 11, -0.0413F, -3.5F, -4.015F, 1, 6, 4, 0.0F, false));

		right_little_sheet_1_r1 = new ModelRenderer(this);
		right_little_sheet_1_r1.setRotationPoint(0.2707F, -2.5762F, -3.0711F);
		pages_pack_right.addChild(right_little_sheet_1_r1);
		setRotationAngle(right_little_sheet_1_r1, 2.4871F, 0.0F, 0.0F);
		right_little_sheet_1_r1.cubeList.add(new ModelBox(right_little_sheet_1_r1, 18, 20, 0.396F, -1.5F, -1.5F, 0, 3, 2, 0.0F, false));

		right_little_sheet_1_r2 = new ModelRenderer(this);
		right_little_sheet_1_r2.setRotationPoint(0.4587F, 1.0F, -3.015F);
		pages_pack_right.addChild(right_little_sheet_1_r2);
		setRotationAngle(right_little_sheet_1_r2, 1.2217F, 0.0F, 0.0F);
		right_little_sheet_1_r2.cubeList.add(new ModelBox(right_little_sheet_1_r2, 18, 20, -0.188F, -0.928F, -1.336F, 0, 3, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		grimoire.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}