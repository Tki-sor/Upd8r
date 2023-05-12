package com.tkisor.upd8r.forge.mixins;

import com.mojang.logging.LogUtils;
import com.tkisor.upd8r.forge.Upd8rx;
import dev.latvian.mods.kubejs.script.ScriptManager;
import dev.latvian.mods.rhino.Context;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ScriptManager.class, remap = false)
public abstract class Upd8rKubejs {
    @Shadow(remap = false) public Context context;
    public Upd8rx upd8rx;

    @Inject(method = "load", at = @At("RETURN"), remap = false)
    public void load(CallbackInfo ci) {
        this.context.setProperty("Upd8rx", upd8rx);
    }
}
