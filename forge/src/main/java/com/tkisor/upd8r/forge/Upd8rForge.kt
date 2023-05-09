package com.tkisor.upd8r.forge

import com.tkisor.upd8r.Upd8r
import com.tkisor.upd8r.Upd8r.init
import com.tkisor.upd8r.forge.event.JoinWorld
import com.tkisor.upd8r.forge.event.ModEventSubscriber
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod


@Mod(Upd8r.MOD_ID)
class Upd8rForge {
    init {
        init()
        MinecraftForge.EVENT_BUS.register(Upd8rCommand)
        MinecraftForge.EVENT_BUS.register(JoinWorld)
//        MinecraftForge.EVENT_BUS.register(ModEventSubscriber)
    }
}