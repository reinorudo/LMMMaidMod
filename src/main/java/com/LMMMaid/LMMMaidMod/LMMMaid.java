package com.LMMMaid.LMMMaidMod;

import com.LMMMaid.LMMMaidMod.entity.render.LMMMaidEntityTypes;
import com.LMMMaid.LMMMaidMod.reg.LMMMaidTab;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LMMMaid.MOD_ID)
public class LMMMaid{

    public static final String MOD_ID = "lmmmaid";//変更時はjsonも変えること
    public static final ItemGroup LMMMAID_TAB = new LMMMaidTab();                                                       //crative tab constracter

    public LMMMaid() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        LMMMaidEntityTypes.REGISTER.register(bus);
    }

    
}
