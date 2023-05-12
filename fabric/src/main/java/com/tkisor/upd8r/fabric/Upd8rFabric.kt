package com.tkisor.upd8r.fabric

import com.tkisor.upd8r.Upd8r.init
import com.tkisor.upd8r.config.Upd8rConfig
import com.tkisor.upd8r.data.Upd8rData
import com.tkisor.upd8r.util.InfoUtil
import com.tkisor.upd8r.util.asString
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player


class Upd8rFabric : ModInitializer {
    val playerList = mutableListOf<Player>()
    override fun onInitialize() {
        init()
        ServerEntityEvents.ENTITY_LOAD.register(ServerEntityEvents.Load { entity: Entity, world: ServerLevel ->
            if (Upd8rConfig().get().baseCfg.enableVersionChecking) {
                val component = TranslatableComponent("")
                component.append(TranslatableComponent(Upd8rData.welcomeMessage?.randomMessage()?.asString() ?: ""))

                val upd8rComponent = InfoUtil.upd8rComponent()
                if (com.tkisor.upd8r.api.InfoUtil.getIsUpd8r() != 0) {
                    upd8rComponent
                        .append("\n")
                }
                upd8rComponent
                    .append(component)

                if (entity is ServerPlayer && entity !in playerList) {
                    entity.sendMessage(upd8rComponent, entity.uuid)
                    playerList.add(entity)
                }
            }

        })
    }
}