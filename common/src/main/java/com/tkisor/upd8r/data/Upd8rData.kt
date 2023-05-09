package com.tkisor.upd8r.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.tkisor.upd8r.util.WebJsonUtil
import com.tkisor.upd8r.util.asString
import net.minecraft.network.chat.Component

object Upd8rData {
    var versionName: String? = null
    var versionFormat: String? = null
        get() = field
            ?.replace("%versionName%", versionName?:"")
            ?.replace("%versionCode%", versionCode.toString())
    var versionCode: Int? = null
    var updateURL: MutableMap<String, String>? = null
    var changelogs: ChangelogData? = null
    var welcomeMessage: WelcomeMessageData? = null

    init {
        val jsonObj = WebJsonUtil.getJsonObject()
        if (jsonObj.has("versionName")) {
            versionName = jsonObj["versionName"]?.asString
        }
        if (jsonObj.has("versionFormat")) {
            versionFormat = jsonObj["versionFormat"]?.asString
        }
        if (jsonObj.has("versionCode")) {
            versionCode = jsonObj["versionCode"]?.asInt
        }
        if (jsonObj.has("changelogs")) {
            val changelogsJson = jsonObj["changelogs"]?.asJsonObject!!
            changelogs = ChangelogData(changelogsJson)
        }
        if (jsonObj.has("welcomeMessage")) {
            val wmJson = jsonObj["welcomeMessage"]?.asJsonObject!!
            welcomeMessage = WelcomeMessageData(wmJson)
        }
        if (jsonObj.has("updateURL")) {
            when {
                jsonObj["updateURL"].isJsonObject -> {
                    runCatching {
                        val type = object : TypeToken<Map<String, Any>>() {}.type
                        val map: Map<String, Any> = Gson().fromJson(jsonObj, type)
                        updateURL = map["updateURL"] as? MutableMap<String, String>
                    }
                }

                jsonObj["updateURL"].isJsonPrimitive -> {
                    runCatching {
                        updateURL?.put("en_us", jsonObj["updateURL"].asString)
                    }
                }
            }
        }
    }

    fun updateData(jsonObj: JsonObject) {
        if (jsonObj.has("versionName")) {
            versionName = jsonObj["versionName"]?.asString
        }
        if (jsonObj.has("versionFormat")) {
            versionFormat = jsonObj["versionFormat"]?.asString
        }
        if (jsonObj.has("changelogs")) {
            val changelogsJson = jsonObj["changelogs"]?.asJsonObject!!
            changelogs = ChangelogData(changelogsJson)
        }
        if (jsonObj.has("welcomeMessage")) {
            val wmJson = jsonObj["welcomeMessage"]?.asJsonObject!!
            welcomeMessage = WelcomeMessageData(wmJson)
        }
    }

    fun getUpdateURL(lang: String): String? {
        if (this.updateURL?.isEmpty() == true || this.updateURL == null) {
            return null
        }
        return if (this.updateURL?.get(lang) != null) {
            this.updateURL!!.get(lang)!!
        } else {
            if (this.updateURL!!.get("en_us") != null) {
                this.updateURL!!.get("en_us")!!
            } else {
                this.updateURL!!.toList()[0].second
            }
        }
    }
}
