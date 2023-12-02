package com.LMMMaid.LMMMaidMod.entity;


import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtCustomerGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MerchantOffer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LMMMaidEntity extends AbstractVillagerEntity{

    public LMMMaidEntity(EntityType<? extends LMMMaidEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes(){
        
        return MobEntity
        .createMobAttributes().add(Attributes.MAX_HEALTH,10)//helth
        .add(Attributes.FOLLOW_RANGE,64)//recognition distance
        .add(Attributes.MOVEMENT_SPEED,0.5)
        .add(Attributes.ATTACK_DAMAGE,3)
        .add(Attributes.ATTACK_SPEED,2);
    }

    @Override//behaiver
    protected void registerGoals(){
        this.goalSelector.addGoal(1, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(0, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
    }

    public boolean canDespawn (double distance){
        return false;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer p_213713_1_) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rewardTradeXp'");
    }

    @Override
    protected void updateTrades() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTrades'");
    }

    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBreedOffspring'");
    }

}