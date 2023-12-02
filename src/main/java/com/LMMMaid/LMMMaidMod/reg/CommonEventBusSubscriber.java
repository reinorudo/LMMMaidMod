package com.LMMMaid.LMMMaidMod.reg;

import com.LMMMaid.LMMMaidMod.LMMMaid;
import com.LMMMaid.LMMMaidMod.entity.LMMMaidEntity;
import com.LMMMaid.LMMMaidMod.entity.render.LMMMaidEntityTypes;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = LMMMaid.MOD_ID, bus = Bus.MOD)
public class CommonEventBusSubscriber {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){

        mobAttributes();
    }

    private static void mobAttributes(){
        DeferredWorkQueue.runLater(()->{
            GlobalEntityTypeAttributes.put(LMMMaidEntityTypes.LMM_MAID.get(), LMMMaidEntity.registerAttributes().build());
        });
    }



}