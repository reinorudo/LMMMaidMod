package com.LMMMaid.LMMMaidMod.reg;
import com.LMMMaid.LMMMaidMod.LMMMaid;
import com.LMMMaid.LMMMaidMod.entity.render.LMMMaidEntityTypes;
import com.LMMMaid.LMMMaidMod.entity.render.LMMMaidRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = LMMMaid.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
            
        renderRegister();
            
    }  

    private static void renderRegister(){
        RenderingRegistry.registerEntityRenderingHandler(LMMMaidEntityTypes.LMM_MAID.get(), LMMMaidRenderer::new);
    }

}
