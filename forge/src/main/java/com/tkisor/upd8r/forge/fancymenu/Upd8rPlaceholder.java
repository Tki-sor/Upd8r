package com.tkisor.upd8r.forge.fancymenu;

import com.mojang.logging.LogUtils;
import de.keksuccino.fancymenu.FancyMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;


@Mixin(value = FancyMenu.class, remap = true)
public class Upd8rPlaceholder {
//    @Inject(method = "registerAll", at = @At(value = "HEAD"))
//    public static void registerAll() {
//        LogUtils.getLogger().error("!!!!Tkisor");
//    }
//    @Inject(method = "FancyMenu", at = @At(value = "HEAD"))
//    public void FancyMenu() {
//        LogUtils.getLogger().error("!!!!Tkisor");
//    }
}
