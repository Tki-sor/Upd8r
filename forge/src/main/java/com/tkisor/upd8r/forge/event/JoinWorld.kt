package com.tkisor.upd8r.forge.event

import com.tkisor.upd8r.data.Upd8rData
import com.tkisor.upd8r.util.InfoUtil
import com.tkisor.upd8r.util.asString
import net.minecraft.locale.Language
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

object JoinWorld {
    @SubscribeEvent
    fun joinWorldMessage(event: PlayerEvent.PlayerLoggedInEvent) {
        val player = event.entity as Player
        val component = Component.empty()

        component.append(Component.translatable(Upd8rData.welcomeMessage?.randomMessage()?.asString() ?: ""))

        val upd8rComponent = InfoUtil.upd8rComponent()
        player.sendSystemMessage(upd8rComponent)
    }
}