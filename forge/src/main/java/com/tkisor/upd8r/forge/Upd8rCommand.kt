package com.tkisor.upd8r.forge

import com.tkisor.upd8r.config.Upd8rConfig
import com.tkisor.upd8r.data.Upd8rData
import com.tkisor.upd8r.util.InfoUtil
import com.tkisor.upd8r.util.asString
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

object Upd8rCommand {
    @OptIn(DelicateCoroutinesApi::class)
    @SubscribeEvent
    fun registerCommand(event: RegisterCommandsEvent) {
        val command = event.dispatcher
        command.register(Commands.literal("upd8r").executes {

            GlobalScope.launch(Dispatchers.IO) {
                runCatching {
                    Upd8rData.updateData()
                    if (it.source.isPlayer) {
                        val player = it.source.player
                        val component = Component.empty()
                        component.append(Component.translatable(Upd8rData.welcomeMessage?.randomMessage()?.asString() ?: ""))
                        val upd8rComponent = InfoUtil.upd8rComponent()
                        upd8rComponent.append(component)
                        player!!.sendSystemMessage(upd8rComponent)
                    }

                }
            }
            0
        })
    }
}
