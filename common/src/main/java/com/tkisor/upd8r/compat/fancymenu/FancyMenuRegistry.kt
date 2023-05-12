package com.tkisor.upd8r.compat.fancymenu

import de.keksuccino.fancymenu.menu.placeholder.v2.Placeholder
import de.keksuccino.fancymenu.menu.placeholder.v2.PlaceholderRegistry

object FancyMenuRegistry {
    fun init() {
        try {
            Upd8rPlace.javaClass.declaredClasses.forEach {
                try {
                    PlaceholderRegistry.registerPlaceholder(it.newInstance() as Placeholder?)
                } catch (_: Exception) {
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
