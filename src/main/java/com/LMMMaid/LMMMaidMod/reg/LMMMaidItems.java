package com.LMMMaid.LMMMaidMod.reg;

import com.LMMMaid.LMMMaidMod.LMMMaid;
import com.LMMMaid.LMMMaidMod.item.spawnEgg.ItemMaidSpawnEgg;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(LMMMaid.MOD_ID)
public class LMMMaidItems {

    public static final ItemMaidSpawnEgg LMMMAID_SPAWN_EGG = new ItemMaidSpawnEgg();

    @Mod.EventBusSubscriber(modid = LMMMaid.MOD_ID,bus = Bus.MOD)
    public static class Register {
        
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event){
            final Item[] items = {
                LMMMAID_SPAWN_EGG
            };
            event.getRegistry().registerAll(items);
        }
    }
}