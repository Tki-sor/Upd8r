package com.tkisor.upd8r.config

import com.tkisor.upd8r.Upd8r
import com.tkisor.upd8r.data.Upd8rData
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment

@Config(name = Upd8r.MOD_ID)
class Upd8rConfig: ConfigData {
    fun get(): Upd8rConfig {
        return AutoConfig.getConfigHolder(this.javaClass).config
    }

    fun save() {
        return AutoConfig.getConfigHolder(this.javaClass).save()
    }

    @Comment("\nBase Config." +
            "\nPlease refer to \"www.awa.com\" for web config.")
    var baseCfg: BaseCfg = BaseCfg()

    class BaseCfg {
        @Comment("\nIf this is set to false, the mod will not try to fetch the version data at all." +
                "\nIt's a switch for modpack users that want to disable modpack version checking.")
        var enableVersionChecking = true
        @Comment("\nWhere the version data JSON will be fetched from, KEEP http:// or https://" +
                "\nComparison will be attempted when multiple URLs are used")
        var versionDataURL = mutableListOf<String>()
//        @Comment("The name of your modpack.")
//        var modpackName = ""
        @Comment("\nThe url that is opened when the user clicks on update button, KEEP http:// or https://" +
                "\nComparison will be attempted when multiple URLs are used." +
                "\nThe updateURL in versionDataURL has higher priority than this configuration." +
                "\nFormat: \"en_us\": \"www.awa.com\"" +
                "\nNote: This can be overridden by '")
        var updateURL = mutableMapOf<String, String>()
        @Comment("\nHow much time to connect to the URL before the connection closes with a timeout error." +
                "\nA timeout of zero is interpreted as an infinite timeout." +
                "\n# This does not block the main thread.")
        var versionCheckerConnectTimeout = 5000
        @Comment("\nHow much time to read before the connection closes with a timeout error." +
                "\nA timeout of zero is interpreted as an infinite timeout." +
                "\n# This does not block the main thread.")
        var versionCheckerReadTimeout = 5000

        fun getUpdateURL(lang: String): String? {
            if (this.updateURL.isEmpty()) {
                return null
            }
            return if (this.updateURL.get(lang) != null) {
                this.updateURL.get(lang)!!
            } else {
                if (this.updateURL.get("en_us") != null) {
                    this.updateURL.get("en_us")!!
                } else {
                    this.updateURL.toList().get(0).second
                }
            }
        }
    }

    @Comment("")
    var currentVersion: CurrentVersion = CurrentVersion()

    class CurrentVersion {
        var versionCode = 0
        var versionFormat = "%versionName%"
            get() = field
                .replace("%versionName%", versionName)
                .replace("%versionCode%", versionCode.toString())
        var versionName = "1.0.0"
    }

}
