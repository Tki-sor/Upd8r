package com.tkisor.upd8r

import com.tkisor.upd8r.config.Upd8rConfig
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer


object Upd8r {
    const val MOD_ID = "upd8r"
    const val Mod_NAME = "Upd8r"
    fun init() {
        AutoConfig.register(
            Upd8rConfig().javaClass
        ) { definition: me.shedaniel.autoconfig.annotation.Config?, configClass: Class<Upd8rConfig>? ->
            JanksonConfigSerializer(
                definition,
                configClass
            )
        }
    }
}
