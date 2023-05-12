package com.tkisor.upd8r.fabric

import com.tkisor.upd8r.compat.fancymenu.FancyMenuRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader

class Upd8rFabricClient: ClientModInitializer {
    override fun onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("fancymenu")) {
            FancyMenuRegistry.init()
        }
    }
}