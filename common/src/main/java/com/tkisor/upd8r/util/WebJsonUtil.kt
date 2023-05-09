package com.tkisor.upd8r.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.tkisor.upd8r.config.Upd8rConfig
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object WebJsonUtil {
    fun getJsonObject(): JsonObject {
        var text: String
        val upd8rConfig = Upd8rConfig()
        val baseCfg = upd8rConfig.get().baseCfg
        val versionDataURL = baseCfg.versionDataURL
        val jsonList = mutableListOf<JsonObject>()

        when (versionDataURL.size) {
            0 -> {
                return JsonObject()
            }

            1 -> {
                return try {
                        val connection = URL(versionDataURL[0]).openConnection()
                        connection.connectTimeout = baseCfg.versionCheckerConnectTimeout
                        connection.readTimeout = baseCfg.versionCheckerReadTimeout
                        val inputStream = connection.getInputStream()
                        val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
                        text = reader.readText()
                        reader.close()
                        val obj = Gson().fromJson(text, JsonElement::class.java).asJsonObject
                        obj
                } catch (_: Exception) {
                    JsonObject()
                }
            }

            else -> {
                runBlocking {
                    versionDataURL.forEach {
                        launch {
                            try {
                                val connection = URL(it).openConnection()
                                connection.connectTimeout = baseCfg.versionCheckerConnectTimeout
                                connection.readTimeout = baseCfg.versionCheckerReadTimeout
                                val inputStream = connection.getInputStream()
                                val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
                                text = reader.readText()
                                reader.close()
                                val obj = Gson().fromJson(text, JsonElement::class.java).asJsonObject
                                jsonList.add(obj)
                            } catch (_: Exception) {
                            }
                        }
                    }
                }

                val newObj: JsonObject
                if (jsonList.size == 0) {
                    return JsonObject()
                } else {
                    newObj = jsonList.getNewUpd8r()
                }
                return newObj
            }
        }
    }

    private fun List<JsonObject>.getNewUpd8r(): JsonObject {
        var newObj = JsonObject()
        this.forEach {
            runCatching {
                if (newObj == JsonObject()) {
                    newObj = it
                    return@forEach
                }

                if (it.has("upd8rCode") && newObj.has("upd8rCode")) {
                    val upd8rCode = it["upd8rCode"].asInt
                    val newObjCode = it["upd8rCode"].asInt
                    when {
                        upd8rCode > newObjCode -> {
                            newObj = it
                            return@forEach
                        }

                        upd8rCode < newObjCode -> {
                            return@forEach
                        }
                    }
                }

                if (newObj.has("versionCode") && it.has("versionCode")) {
                    val newCode = newObj["versionCode"].asInt
                    val itCode = it["versionCode"].asInt

                    when {
                        newCode > itCode -> {
                            return@forEach
                        }

                        newCode < itCode -> {
                            newObj = it
                            return@forEach
                        }
                    }
                }

                if (newObj.has("versionName") && it.has("versionName")) {
                    when (newObj["versionName"].asString.versionCompare(it["versionName"].asString)) {
                        -1 -> {
                            newObj = it
                            return@forEach
                        }

                        else -> {
                            return@forEach
                        }
                    }
                }
            }
        }
        return newObj
    }

}