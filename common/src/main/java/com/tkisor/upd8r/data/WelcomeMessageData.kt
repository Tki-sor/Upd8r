package com.tkisor.upd8r.data

import com.google.gson.JsonObject
import java.util.*

data class WelcomeMessageData(val jsonObj: JsonObject) {
    val welcomeMessageList = mutableListOf<List<String>>()
    init {
        for ((_, value) in jsonObj.entrySet()) {
            if (value.isJsonArray) {
                val listOf = mutableListOf<String>()
                value.asJsonArray.forEach {
                    listOf.add(it.asString)
                }
                welcomeMessageList.add(listOf)
            }
        }
    }

    fun randomMessage(): List<String> {
        val num: Int
        if (this.welcomeMessageList.size != 0) {
            num = Random().nextInt(this.welcomeMessageList.size)
        } else {
            return listOf()
        }
        return this.welcomeMessageList[num]
    }
}
