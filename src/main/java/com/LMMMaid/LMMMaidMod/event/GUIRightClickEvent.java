package com.LMMMaid.LMMMaidMod.event;

import com.LMMMaid.LMMMaidMod.LMMMaid;
import com.LMMMaid.LMMMaidMod.chat.CustomChatGUI;
import com.LMMMaid.LMMMaidMod.entity.LMMMaidEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LMMMaid.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)//これで登録される。しかし、staticが必要。bus = Mod.EventBusSubscriber.Bus.FORGEを登録していない時にstaticをつけるとエラー
public class GUIRightClickEvent {

    
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        PlayerEntity player = event.getPlayer();
        Entity target = event.getTarget();

        // FakePlayerを無視する（これは他のmodが発生させる場合があるため）
        if (player instanceof FakePlayer || target == null) {
            return;
        }

        if (player != null && target != null) {//test
            if (target instanceof LMMMaidEntity) {
                System.out.println("Right-clicked LMMMaidEntity!");
            }
        }

        // エンティティが特定の条件を満たす場合、CustomChatGUIを開く
        if (target instanceof LMMMaidEntity) {
            if (player.level.isClientSide()) {
                CustomChatGUI.openCustomChatGUI(player, (LMMMaidEntity) target);
            }
            event.setCanceled(true);
            event.setCancellationResult(ActionResultType.SUCCESS);
        }
    }

}
