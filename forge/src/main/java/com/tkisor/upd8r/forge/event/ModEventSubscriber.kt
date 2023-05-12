package com.tkisor.upd8r.forge.event

import com.tkisor.upd8r.config.Upd8rConfig
import com.tkisor.upd8r.data.Upd8rData
import net.minecraft.client.gui.screens.TitleScreen
import net.minecraftforge.client.event.ScreenEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.internal.BrandingControl
import java.lang.reflect.Method


object ModEventSubscriber {
    private var hasLoaded = false

    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent?) {
        MinecraftForge.EVENT_BUS.register(ModEventSubscriber::class.java)
    }

    @SubscribeEvent
    fun openMainMenu(event: ScreenEvent.Init) {
        if (event.screen is TitleScreen) {
            this.init()
        }
    }

    fun init() {
        if (!hasLoaded) {
            try {
                val brandingControl = BrandingControl()
                val field = BrandingControl::class.java.getDeclaredField("brandings")
                field.isAccessible = true
                val computeBranding: Method = BrandingControl::class.java.getDeclaredMethod("computeBranding")
                computeBranding.isAccessible = true
                computeBranding.invoke(null)

                val brands: List<String> = ArrayList(field.get(brandingControl) as List<String>)
                val newBrands = mutableListOf<String>()
                field.set(brandingControl, newBrands)
                newBrands.addAll(brands)

                val upd8rInfo = mutableListOf<String>()
                upd8rInfo.add("当前版本：${Upd8rConfig().get().currentVersion.versionName} or ${Upd8rConfig().get().currentVersion.versionCode}")
                upd8rInfo.add("最新版本：${Upd8rData.versionName} or ${Upd8rData.versionCode}")

                newBrands.addAll(upd8rInfo)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            hasLoaded = true
        }
    }
}