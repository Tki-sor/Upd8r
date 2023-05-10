package com.tkisor.upd8r.api;

import com.tkisor.upd8r.config.Upd8rConfig;
import com.tkisor.upd8r.data.Upd8rData;
import net.minecraft.client.Minecraft;

import java.util.Map;

public class InfoUtil {
    private InfoUtil() {
    }
    public static boolean isUpd8r() {
        return Boolean.TRUE.equals(com.tkisor.upd8r.util.InfoUtil.INSTANCE.isUpd8r());
    }
    public static int getIsUpd8r() {
        Boolean upd8r = com.tkisor.upd8r.util.InfoUtil.INSTANCE.isUpd8r();
        if (Boolean.TRUE.equals(upd8r)) return 1;
        if (Boolean.FALSE.equals(upd8r)) return 0;
        if (upd8r == null) return -1;
        return 0;
    }

    public static String getCurrentVersion() {
        return new Upd8rConfig().get().getCurrentVersion().getVersionFormat();
    }

    public static String getLatestVersion() {
        if (Upd8rData.INSTANCE.getVersionFormat() != null) {
            return Upd8rData.INSTANCE.getVersionFormat();
        } else {
            return "";
        }
    }

    public static String getUpdateURL() {
        Map<String, String> updateURL1 = Upd8rData.INSTANCE.getUpdateURL();
        Map<String, String> updateURL2 = new Upd8rConfig().get().getBaseCfg().getUpdateURL();
        String langCode = Minecraft.getInstance().getLanguageManager().getSelected().getCode();

        if (updateURL1 != null && !updateURL1.isEmpty()) {
            if (updateURL1.get(langCode) != null) {
                return updateURL1.get(langCode);
            } else {
                if (updateURL1.get("en_us") != null) {
                    return updateURL1.get("en_us");
                } else {
                    return updateURL1.values().iterator().next();
                }
            }
        } else if (!updateURL2.isEmpty()) {
            if (updateURL2.get(langCode) != null) {
                return updateURL2.get(langCode);
            } else {
                if (updateURL2.get("en_us") != null) {
                    return updateURL2.get("en_us");
                } else {
                    return updateURL2.values().iterator().next();
                }
            }
        }
        return "";
    }


}
