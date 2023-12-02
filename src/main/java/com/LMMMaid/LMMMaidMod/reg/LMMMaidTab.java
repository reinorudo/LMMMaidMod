package com.LMMMaid.LMMMaidMod.reg;

import com.LMMMaid.LMMMaidMod.LMMMaid;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class LMMMaidTab extends ItemGroup{

    public LMMMaidTab(){
        super(LMMMaid.MOD_ID);
    }

    @Override
    public ItemStack makeIcon(){
        //define creative tabs icon. tentative icon is TNT. I must change icon.
        ItemStack itemStack = new ItemStack(LMMMaidItems.LMMMAID_SPAWN_EGG);
        return itemStack;
    }
}