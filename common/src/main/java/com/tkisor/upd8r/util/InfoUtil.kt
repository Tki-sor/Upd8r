package com.tkisor.upd8r.util

import com.tkisor.upd8r.config.Upd8rConfig
import com.tkisor.upd8r.data.Upd8rData
import net.minecraft.client.Minecraft
import net.minecraft.locale.Language
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.HoverEvent
import net.minecraft.network.chat.MutableComponent


object InfoUtil {
    fun upd8rComponent(): MutableComponent {
        val component = Component.empty()
        val instance = Language.getInstance()
        val langCode = Minecraft.getInstance().languageManager.selected.code
        val updateLink = Component.translatable("text.upd8r.update_link")

        when (isUpd8r()) {
            true -> {
                component
                    .append(Component.translatable("text.upd8r.separator")).append("\n")
                    .append(Component.translatable("text.upd8r.update_chat_message_title")).append("\n")
                    .append(
                        instance.getOrDefault("text.upd8r.current_version")
                            .format(Upd8rConfig().get().currentVersion.versionName)
                    ).append("\n")
                    .append(
                        instance.getOrDefault("text.upd8r.latest_version")
                            .format(Upd8rData.versionFormat ?: "")).append("\n")

                if (Upd8rData.changelogs != null) {
                    component
                        .append(Component.translatable("text.upd8r.changelogs")).append("\n")
                        .append(Upd8rData.changelogs?.asString() ?: "")
                }
            }

            null -> {
                component
                    .append(Component.translatable("text.upd8r.separator")).append("\n")
                    .append(Component.translatable("text.upd8r.update_check_failed")).append("\n")
                    .append(
                        instance.getOrDefault("text.upd8r.current_version")
                            .format(Upd8rConfig().get().currentVersion.versionFormat)
                    ).append("\n")
            }
            else -> {

            }
        }
        if (isUpd8r() != false) {
            if (Upd8rData.updateURL != null) {
                component.append("\n")
                updateLink.withStyle {
                    it.withClickEvent(
                        ClickEvent(
                            ClickEvent.Action.OPEN_URL,
                            Upd8rData.getUpdateURL(langCode) ?: ""
                        )
                    )
                }
                updateLink.withStyle {
                    it.withHoverEvent(
                        HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            Component.translatable("text.upd8r.update_link_tooltip")
                        )
                    )
                }
                component.append(updateLink).append("\n")
            } else if (Upd8rConfig().get().baseCfg.updateURL.isNotEmpty()) {
                component.append("\n")
                updateLink.withStyle {
                    it.withClickEvent(
                        ClickEvent(
                            ClickEvent.Action.OPEN_URL,
                            Upd8rConfig().get().baseCfg.getUpdateURL(langCode)
                                ?: ""
                        )
                    )
                }
                updateLink.withStyle {
                    it.withHoverEvent(
                        HoverEvent(
                            HoverEvent.Action.SHOW_TEXT,
                            Component.translatable("text.upd8r.update_link_tooltip")
                        )
                    )
                }
                component.append(updateLink).append("\n")
            }
            component.append(Component.translatable("text.upd8r.separator"))
        }
        return component
    }

    /**
     * 判断是否需要更新
     *
     * 打算放到 api 中
     */
    @Deprecated("This method is deprecated. Use the api.InfoUtil.isUpd8r() or getIsUpd8r() instead.")
    fun isUpd8r(): Boolean? {
        val currentVersion = Upd8rConfig().get().currentVersion
        return when {
            Upd8rData.versionCode != null -> {
                currentVersion.versionCode < (Upd8rData.versionCode ?: 0)
            }

            Upd8rData.versionName != null -> {
                when (currentVersion.versionName.versionCompare(Upd8rData.versionName!!)) {
                    -1 -> {
                        true
                    }

                    else -> {
                        false
                    }
                }
            }

            else -> {
                null
            }
        }
    }
}

/**
 * @return -1为this小于，1为大于，0为等于
 */
fun String.versionCompare(versionName: String): Int {
    val regex = "\\D"

    val arr1 = this.split(".").map { it.replace(regex, "").toIntOrNull() }
    val arr2 = versionName.split(".").map { it.replace(regex, "").toIntOrNull() }

    for (i in 0 until minOf(arr1.size, arr2.size)) {
        val num1 = arr1[i]
        val num2 = arr2[i]

        if (num1 != null && num2 != null) {
            if (num1 < num2) {
                return -1
            } else if (num1 > num2) {
                return 1
            }
        } else {
            if (num1 == null && num2 != null) {
                return -1
            } else if (num1 != null && num2 == null) {
                return 1
            }
        }
    }

    return when {
        arr1.size < arr2.size -> -1
        arr1.size > arr2.size -> 1
        else -> 0
    }
}
