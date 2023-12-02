package com.LMMMaid.LMMMaidMod.entity.render;

import com.LMMMaid.LMMMaidMod.LMMMaid;
import com.LMMMaid.LMMMaidMod.entity.LMMMaidEntity;
import com.LMMMaid.LMMMaidMod.entity.model.LMMMaidModel;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LMMMaidRenderer extends MobRenderer<LMMMaidEntity, LMMMaidModel<LMMMaidEntity>>{

    private static final ResourceLocation LMM_MAID = new ResourceLocation(LMMMaid.MOD_ID,"textures/entity/mob/LMMMaidMaidModel.png");

    public LMMMaidRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LMMMaidModel<>(),0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(LMMMaidEntity entity) {
        return LMM_MAID;
    }

    public ResourceLocation getEntityTexture(LMMMaidEntity entity) {
        return LMM_MAID;
    }
    
    @Override
    protected void setupRotations(LMMMaidEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        
        matrixStackIn.scale(0.8F, 0.8F, 0.8F); 
    }


}