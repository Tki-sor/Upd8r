package com.tkisor.upd8r

import com.tkisor.upd8r.config.Upd8rConfig
import dev.architectury.injectables.annotations.ExpectPlatform
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer


object Upd8r {
    const val MOD_ID = "upd8r"
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
