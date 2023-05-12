package com.tkisor.upd8r.compat.fancymenu

import com.tkisor.upd8r.Upd8r
import com.tkisor.upd8r.annotation.UsedBy
import com.tkisor.upd8r.api.InfoUtil
import com.tkisor.upd8r.data.Upd8rData
import de.keksuccino.fancymenu.menu.placeholder.v2.DeserializedPlaceholderString
import de.keksuccino.fancymenu.menu.placeholder.v2.Placeholder
object Upd8rPlace {
    @UsedBy("FancyMenuRegistry")
    class CurrentVersion : Placeholder("current_version") {
        override fun getReplacementFor(deserializedPlaceholderString: DeserializedPlaceholderString): String {
            return InfoUtil.getCurrentVersion()
        }

        override fun getValueNames(): List<String>? {
            return null
        }

        override fun getDisplayName(): String {
            return "Current Version"
        }

        override fun getDescription(): List<String> {
            return listOf("Get Current Version.")
        }

        override fun getCategory(): String {
            return Upd8r.Mod_NAME
        }

        override fun getDefaultPlaceholderString(): DeserializedPlaceholderString {
            val dps = DeserializedPlaceholderString()
            dps.placeholder = this.identifier
            return dps
        }
    }

    @UsedBy("FancyMenuRegistry")
    class LatestVersion : Placeholder("latest_version") {
        override fun getReplacementFor(deserializedPlaceholderString: DeserializedPlaceholderString): String {
            return Upd8rData.versionFormat?:""
        }

        override fun getValueNames(): List<String>? {
            return null
        }

        override fun getDisplayName(): String {
            return "Latest Version"
        }

        override fun getDescription(): List<String> {
            return listOf("Get Latest Version.")
        }

        override fun getCategory(): String {
            return Upd8r.Mod_NAME
        }

        override fun getDefaultPlaceholderString(): DeserializedPlaceholderString {
            val dps = DeserializedPlaceholderString()
            dps.placeholder = this.identifier
            return dps
        }
    }

    @UsedBy("FancyMenuRegistry")
    class UpdateLink : Placeholder("update_link") {
        override fun getReplacementFor(deserializedPlaceholderString: DeserializedPlaceholderString): String {
            return InfoUtil.getUpdateURL()
        }

        override fun getValueNames(): List<String>? {
            return null
        }

        override fun getDisplayName(): String {
            return "Update Link"
        }

        override fun getDescription(): List<String> {
            return listOf("Get Update Link.")
        }

        override fun getCategory(): String {
            return Upd8r.Mod_NAME
        }

        override fun getDefaultPlaceholderString(): DeserializedPlaceholderString {
            val dps = DeserializedPlaceholderString()
            dps.placeholder = this.identifier
            return dps
        }
    }
}
