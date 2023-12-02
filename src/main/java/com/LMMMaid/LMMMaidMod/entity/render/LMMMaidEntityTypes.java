package com.LMMMaid.LMMMaidMod.entity.render;

import java.util.function.BiFunction;

import com.LMMMaid.LMMMaidMod.LMMMaid;
import com.LMMMaid.LMMMaidMod.entity.LMMMaidEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LMMMaidEntityTypes {

    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES,LMMMaid.MOD_ID);

    public static final RegistryObject<EntityType<LMMMaidEntity>> LMM_MAID = villagerEntityRegister("lmm_maid", LMMMaidEntity::new);
    

    private static <T extends Entity>RegistryObject<EntityType<T>> villagerEntityRegister(String id, BiFunction<EntityType<T>, World, T> function){
        EntityType<T> type = EntityType.Builder.of(function::apply, EntityClassification.MISC).sized(0.5F, 1.6F).setTrackingRange(32).build(id);
        return REGISTER.register(id, ()->type);
    }
}