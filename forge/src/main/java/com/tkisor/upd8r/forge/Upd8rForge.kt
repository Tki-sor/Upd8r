package com.tkisor.upd8r.forge

import com.tkisor.upd8r.Upd8r
import com.tkisor.upd8r.Upd8r.init
import com.tkisor.upd8r.forge.event.ClientEvents
import com.tkisor.upd8r.forge.event.JoinWorld
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS


@Mod(Upd8r.MOD_ID)
class Upd8rForge {
    init {
        init()
        MinecraftForge.EVENT_BUS.register(JoinWorld)
    }
}