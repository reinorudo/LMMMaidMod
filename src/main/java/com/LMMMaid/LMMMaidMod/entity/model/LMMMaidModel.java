package com.LMMMaid.LMMMaidMod.entity.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LMMMaidModel <T extends Entity> extends SegmentedModel<T> implements IHasHead, IHeadToggle{
	private final ModelRenderer Head;
	private final ModelRenderer headhon;
	private final ModelRenderer headhonLeft;
	private final ModelRenderer headhonRight;
	private final ModelRenderer headhonCenter;
	private final ModelRenderer income;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cap;
	private final ModelRenderer Body;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;

	public LMMMaidModel(){
		this(64,64);
	}

	public LMMMaidModel(int w,int h) {
		

		Head = new ModelRenderer(this).setTexSize(w,h);
		Head.setPos(0.0F, 0.0F, 0.0F);
		Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		headhon = new ModelRenderer(this).setTexSize(w,h);
		headhon.setPos(0.0F, 24.0F, 0.0F);
		Head.addChild(headhon);
		

		headhonLeft = new ModelRenderer(this).setTexSize(w,h);
		headhonLeft.setPos(0.0F, 0.0F, 0.0F);
		headhon.addChild(headhonLeft);
		headhonLeft.texOffs(1, 35).addBox(4.0F, -30.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
		headhonLeft.texOffs(0, 0).addBox(5.0F, -29.0F, -1.0F, 0.5F, 2.0F, 2.0F, 0.0F, false);

		headhonRight = new ModelRenderer(this).setTexSize(w,h);
		headhonRight.setPos(0.0F, 0.0F, 0.0F);
		headhon.addChild(headhonRight);
		headhonRight.texOffs(1, 35).addBox(-5.0F, -30.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, true);
		headhonRight.texOffs(0, 0).addBox(-5.5F, -29.0F, -1.0F, 0.5F, 2.0F, 2.0F, 0.0F, true);

		headhonCenter = new ModelRenderer(this).setTexSize(w,h);
		headhonCenter.setPos(0.0F, 0.0F, 0.0F);
		headhon.addChild(headhonCenter);
		headhonCenter.texOffs(29, 40).addBox(-4.0F, -32.5F, -1.0F, 8.0F, 0.5F, 2.0F, 0.0F, false);
		headhonCenter.texOffs(22, 38).addBox(-4.5F, -32.5F, -1.0F, 0.5F, 2.5F, 2.0F, 0.0F, false);
		headhonCenter.texOffs(22, 38).addBox(4.0F, -32.5F, -1.0F, 0.5F, 2.5F, 2.0F, 0.0F, true);

		income = new ModelRenderer(this).setTexSize(w,h);
		income.setPos(0.0F, 0.0F, 0.0F);
		headhon.addChild(income);
		

		cube_r1 = new ModelRenderer(this).setTexSize(w,h);
		cube_r1.setPos(4.75F, -27.25F, -4.75F);
		income.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.4363F, 0.0F, 0.0F);
		cube_r1.texOffs(26, 1).addBox(-0.24F, 1.75F, -2.25F, 0.49F, 0.5F, 5.0F, 0.0F, false);

		cap = new ModelRenderer(this).setTexSize(w,h);
		cap.setPos(0.0F, 24.0F, 0.0F);
		Head.addChild(cap);
		cap.texOffs(0, 43).addBox(-4.5F, -32.5F, -4.0F, 9.0F, 0.5F, 3.0F, 0.0F, false);
		cap.texOffs(1, 43).addBox(-4.5F, -32.5F, 1.0F, 9.0F, 0.5F, 3.0F, 0.0F, false);
		cap.texOffs(0, 49).addBox(-4.5F, -32.0F, -4.5F, 9.0F, 1.0F, 0.5F, 0.0F, false);
		cap.texOffs(0, 51).addBox(-4.5F, -31.0F, -4.5F, 9.0F, 1.5F, 0.5F, 0.0F, false);
		cap.texOffs(-1, 50).addBox(-4.5F, -29.5F, -5.5F, 9.0F, 0.5F, 1.5F, 0.0F, false);
		cap.texOffs(55, 43).addBox(-4.5F, -32.0F, -4.0F, 0.5F, 3.0F, 3.0F, 0.0F, false);
		cap.texOffs(55, 43).addBox(4.0F, -32.0F, -4.0F, 0.5F, 3.0F, 3.0F, 0.0F, true);
		cap.texOffs(52, 53).addBox(-4.5F, -32.0F, 1.0F, 0.5F, 5.0F, 3.0F, 0.0F, false);
		cap.texOffs(52, 53).addBox(4.0F, -32.0F, 1.0F, 0.5F, 5.0F, 3.0F, 0.0F, true);
		cap.texOffs(40, 34).addBox(-4.5F, -32.0F, 4.0F, 9.0F, 5.5F, 0.5F, 0.0F, false);

		Body = new ModelRenderer(this).setTexSize(w,h);
		Body.setPos(0.0F, 0.0F, 0.0F);
		Body.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		RightArm = new ModelRenderer(this).setTexSize(w,h);
		RightArm.setPos(-5.0F, 2.5F, 0.0F);
		RightArm.texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this).setTexSize(w,h);
		LeftArm.setPos(5.0F, 2.5F, 0.0F);
		LeftArm.texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this).setTexSize(w,h);
		RightLeg.setPos(-1.9F, 12.0F, 0.0F);
		RightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this).setTexSize(w,h);
		LeftLeg.setPos(1.9F, 12.0F, 0.0F);
		LeftLeg.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	private void setRotationAngle(ModelRenderer cube_r12, float f, float g, float h) {
		this.cube_r1.xRot = 0.4363F;
	}

	@Override
	public void hatVisible(boolean p_217146_1_) {
		this.Head.visible = p_217146_1_;
	}

	@Override
	public ModelRenderer getHead() {
		return this.Head;
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(this.Head, this.Body, this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg);
	}

	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_,
			float p_225597_6_) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'setupAnim'");
	}

}