package com.tkisor.upd8r.data

import com.google.gson.JsonObject
import com.tkisor.upd8r.util.asString

data class ChangelogData(val jsonObj: JsonObject) {
    var changelogs = LinkedHashMap<String, List<String>>()
    init {
        for ((key, value) in jsonObj.entrySet()) {
            val listOf = mutableListOf<String>()
            value.asJsonArray.forEach {
                listOf.add(it.asString)
            }
            changelogs.put(key, listOf)
        }
    }

    fun asString(): String {
        var str = ""
        changelogs.forEach {
            str += it.key + ":\n"
            str += it.value.asString() + "\n"
        }
        return str
    }
}
