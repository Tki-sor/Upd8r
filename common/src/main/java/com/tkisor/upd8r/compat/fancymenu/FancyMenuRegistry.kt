package com.tkisor.upd8r.compat.fancymenu

import de.keksuccino.fancymenu.menu.placeholder.v2.PlaceholderRegistry
object FancyMenuRegistry {
    fun init() {
        try {
            PlaceholderRegistry.registerPlaceholder(Upd8rPlace.CurrentVersion())
            PlaceholderRegistry.registerPlaceholder(Upd8rPlace.LatestVersion())
            PlaceholderRegistry.registerPlaceholder(Upd8rPlace.UpdateLink())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
