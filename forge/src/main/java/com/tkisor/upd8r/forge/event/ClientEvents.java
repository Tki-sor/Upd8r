package com.tkisor.upd8r.forge.event;

import com.tkisor.upd8r.Upd8r;
import com.tkisor.upd8r.compat.fancymenu.FancyMenuRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Upd8r.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientForgeModBusEvents {
        @SubscribeEvent
        public static void onClientSetup(final FMLClientSetupEvent event) {
            if (ModList.get().isLoaded("fancymenu")) {
                FancyMenuRegistry.INSTANCE.init();
            }
        }
    }
}
