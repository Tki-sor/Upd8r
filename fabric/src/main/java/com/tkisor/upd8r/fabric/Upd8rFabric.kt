package com.tkisor.upd8r.fabric

import com.tkisor.upd8r.Upd8r.init
import net.fabricmc.api.ModInitializer

class Upd8rFabric : ModInitializer {
    override fun onInitialize() {
        init()
    }
}