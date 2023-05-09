package com.tkisor.upd8r.forge

import com.mojang.brigadier.context.CommandContext
import com.tkisor.upd8r.data.Upd8rData
import com.tkisor.upd8r.util.asString
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

object Upd8rCommand {
    @OptIn(DelicateCoroutinesApi::class)
    @SubscribeEvent
    fun registerCommand(event: RegisterCommandsEvent) {
//        val command = event.dispatcher
//        command.register(Commands.literal("udp8r").executes { udp8r: CommandContext<CommandSourceStack?>? ->
//
//            GlobalScope.launch(Dispatchers.IO) {
//                runCatching {
//                    val message = Upd8rData.welcomeMessage?.randomMessage()?.asString()?:"qwq"
//
//                    udp8r?.source?.player?.sendSystemMessage(Component.translatable(message))
//                }.onFailure {
//                    udp8r?.source?.player?.sendSystemMessage(Component.literal("请求超时"))
//                }
//            }
//            0
//        })
    }
}
