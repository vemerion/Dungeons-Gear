package com.infamous.dungeons_gear.armor.models;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ChampionsArmorModel<T extends LivingEntity> extends BipedModel<T> {
	private final EquipmentSlotType slot;
	private final LivingEntity entity;
	private final ModelRenderer Everything;
	private final ModelRenderer Head;
	private final ModelRenderer Wings;
	private final ModelRenderer Body;
	private final ModelRenderer Chestplate;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;

	public ChampionsArmorModel(float modelSize, EquipmentSlotType slot, LivingEntity entity) {
		super(modelSize, 0.0F, 64, 64);
		this.slot = slot;
		this.entity = entity;
		textureWidth = 64;
		textureHeight = 64;

		Everything = new ModelRenderer(this);
		Everything.setRotationPoint(0.0F, 24.0F, 0.0F);

		boolean testificate =
				this.entity instanceof AbstractIllagerEntity ||
						this.entity.getType() == ForgeRegistries.ENTITIES.getValue(new ResourceLocation("savageandravage:skeleton_villager")) ||
						this.entity instanceof ZombieVillagerEntity ||
						this.entity instanceof AbstractVillagerEntity ||
						this.entity.getType() == ForgeRegistries.ENTITIES.getValue(new ResourceLocation("guardvillagers:guard"));

		int testificateHelmetShiftUp = testificate ? 2 : 0;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Everything.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -32.0F + 24.0F - testificateHelmetShiftUp, -4.0F, 8.0F, 8.0F, 8.0F, 1.0F, false);

		Wings = new ModelRenderer(this);
		Wings.setRotationPoint(0.0F, -7.0F, 17.0F);
		Head.addChild(Wings);
		setRotationAngle(Wings, 0.6545F, 0.0F, 0.0F);
		Wings.setTextureOffset(27, 16).addBox(-5.2F, -31.0F + 19.0F - testificateHelmetShiftUp, -2.0F - 13.0F, 0.0F, 3.0F, 8.0F, 0.0F, false);
		Wings.setTextureOffset(27, 16).addBox(5.2F, -31.0F + 19.0F - testificateHelmetShiftUp, -2.0F - 13.0F, 0.0F, 3.0F, 8.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Everything.addChild(Body);
		Body.setTextureOffset(40, 0).addBox(-4.0F, -24.0F + 24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 1.0F, false);

		Chestplate = new ModelRenderer(this);
		Chestplate.setRotationPoint(0.0F, -15.0F, 0.0F);
		Body.addChild(Chestplate);
		Chestplate.setTextureOffset(46, 23).addBox(0.7F, -7.7467F + 24.0F, -4.0F, 5.0F, 4.0F, 2.0F, 0.3F, false);
		Chestplate.setTextureOffset(46, 23).addBox(-5.7F, -7.7467F + 24.0F, -4.0F, 5.0F, 4.0F, 2.0F, 0.3F, true);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(-5.0F, 35.0F, -15.0F);
		Everything.addChild(LeftArm);
		LeftArm.setTextureOffset(48, 16).addBox(5.0F-8.0F + 2.0F, -24.0F + 22.0F, -2.0F, 4.0F, 3.0F, 4.0F, 1.0F, false);
		LeftArm.setTextureOffset(32, 16).addBox(4.0F-8.0F + 2.0F, -22.0F + 22.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, false);
		LeftArm.setTextureOffset(0, 16).addBox(8.0F-8.0F + 2.0F, -15.0F + 22.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.5F, true);
		LeftArm.setTextureOffset(10, 16).addBox(7.0F-8.0F + 2.0F, -12.0F + 22.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.5F, true);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 35.0F, -15.0F);
		Everything.addChild(RightArm);
		RightArm.setTextureOffset(48, 16).addBox(-9.0F + 8.0F - 2.0F, -24.0F + 22.0F, -2.0F, 4.0F, 3.0F, 4.0F, 1.0F, true);
		RightArm.setTextureOffset(32, 16).addBox(-8.0F + 8.0F - 2.0F, -22.0F + 22.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, true);
		RightArm.setTextureOffset(0, 16).addBox(-9.0F + 8.0F - 2.0F, -15.0F + 22.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.5F, false);
		RightArm.setTextureOffset(10, 16).addBox(-9.0F + 8.0F - 2.0F, -12.0F + 22.0F, -2.0F, 2.0F, 1.0F, 4.0F, 0.5F, false);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha){
		boolean testificate =
				this.entity instanceof AbstractIllagerEntity ||
						this.entity.getType() == ForgeRegistries.ENTITIES.getValue(new ResourceLocation("savageandravage:skeleton_villager")) ||
						this.entity instanceof ZombieVillagerEntity ||
						this.entity instanceof AbstractVillagerEntity ||
						this.entity.getType() == ForgeRegistries.ENTITIES.getValue(new ResourceLocation("guardvillagers:guard"));

		boolean piglin =
				this.entity instanceof PiglinEntity ||
						this.entity instanceof ZombifiedPiglinEntity;



		if (this.slot == EquipmentSlotType.HEAD) {
		/*
			if (piglin) {
				matrixStackIn.push();
				this.piglin_helmet1.copyModelAngles(this.bipedHead);
				if (this.entity.isChild()) {
					matrixStack.scale(0.8F, 0.8F, 0.8F);
					this.piglin_helmet1.setRotationPoint(0.0F, 15.0F, 0.0F);
				}
				this.piglin_helmet1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
				matrixStackIn.pop();

			} else if (testificate) {
				matrixStackIn.push();
				this.illager_helmet1.copyModelAngles(this.bipedHead);
				if (this.entity.isChild()) {
					matrixStackIn.scale(0.8F, 0.8F, 0.8F);
					this.illager_helmet1.setRotationPoint(0.0F, 15.0F, 0.0F);
				}
				this.illager_helmet1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
				matrixStackIn.pop();
			} else {

		 */
			matrixStackIn.push();
			this.Head.copyModelAngles(this.bipedHead);
			if (this.entity.isChild()) {
				matrixStackIn.scale(0.8F, 0.8F, 0.8F);
				this.Head.setRotationPoint(0.0F, 15.0F, 0.0F);
			}
			this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			matrixStackIn.pop();
			//}
		}

		if (this.slot == EquipmentSlotType.CHEST) {
			matrixStackIn.push();

			this.Body.copyModelAngles(this.bipedBody);
			this.LeftArm.copyModelAngles(this.bipedLeftArm);
			this.RightArm.copyModelAngles(this.bipedRightArm);
			if (this.entity.isChild()) {
				matrixStackIn.scale(0.5F, 0.5F, 0.5F);
				this.Body.setRotationPoint(0.0F, 24.0F, 0.0F);
				this.LeftArm.setRotationPoint(5.0F, 24.0F, 0.0F);
				this.RightArm.setRotationPoint(-5.0F, 24.0F, 0.0F);
			}
			this.LeftArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			this.RightArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			if (testificate) {
				matrixStackIn.scale(1.0F, 1.0F, 1.3F);
			}
			this.Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			matrixStackIn.pop();
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}