package com.LMMMaid.LMMMaidMod.item.spawnEgg;

import javax.annotation.Nullable;

import com.LMMMaid.LMMMaidMod.LMMMaid;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.*;

public class LMMMaidSpawnEgg extends SpawnEggItem{

    protected static final List<LMMMaidSpawnEgg> MOD_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityType;

    public LMMMaidSpawnEgg(final RegistryObject<? extends EntityType<?>> typeIn, int primaryColorIn, int secondaryColorIn) {
        super(null, primaryColorIn, secondaryColorIn,new Properties().tab(LMMMaid.LMMMAID_TAB));
        this.entityType = Lazy.of(typeIn);
        MOD_EGGS.add(this);
    }
    
    //初期化
    private static void initModSpawnEgg(){
        final Map<EntityType<?>,SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
        DefaultDispenseItemBehavior behavior = new DefaultDispenseItemBehavior(){
            
            @Override
            protected ItemStack execute(IBlockSource source, ItemStack stack){
                net.minecraft.util.Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
                BlockPos blockPos = source.getPos().relative(direction);

                type.spawn(source.getLevel(), stack, null, blockPos, SpawnReason.DISPENSER, direction == net.minecraft.util.Direction.DOWN, true);
                stack.shrink(1);
                return stack;
            }
        };
        for(SpawnEggItem egg : MOD_EGGS){
            EGGS.put(egg.getType(null), egg);
            DispenserBlock.registerBehavior(egg, behavior);
        }
        MOD_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT nbt){
        return entityType.get();
    }

    @Mod.EventBusSubscriber(modid = LMMMaid.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Register{
        @SubscribeEvent
        public static void spawnEgg(final RegistryEvent.Register<EntityType<?>> event){
            initModSpawnEgg();
        }
    }
    
}