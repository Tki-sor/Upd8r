package com.tkisor.upd8r.compat.fancymenu

import com.tkisor.upd8r.Upd8r
import com.tkisor.upd8r.api.InfoUtil
import com.tkisor.upd8r.config.Upd8rConfig
import com.tkisor.upd8r.data.Upd8rData
import de.keksuccino.fancymenu.menu.placeholder.v2.DeserializedPlaceholderString
import de.keksuccino.fancymenu.menu.placeholder.v2.Placeholder
object Upd8rPlace {
    class CurrentVersion : Placeholder("current_version") {
        override fun getReplacementFor(deserializedPlaceholderString: DeserializedPlaceholderString): String {
            return Upd8rConfig().get().currentVersion.versionFormat
        }

        override fun getValueNames(): List<String>? {
            return null
        }

        override fun getDisplayName(): String {
            return "Current Version"
        }

        override fun getDescription(): List<String> {
            return listOf("获取当前版本")
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
            return listOf("获取最新版本")
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
            return listOf("获取更新链接")
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
