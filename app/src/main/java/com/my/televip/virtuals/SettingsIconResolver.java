package com.my.televip.virtuals;


import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;

import com.my.televip.reflect.XReflect;

public class SettingsIconResolver {

    private static Integer cachedIcon = null;

    public static int getIconSettings() {
        if (ClassLoad.getClass(ClassNames.DRAWABLE) == null) {
            return 0;
        }
        if (cachedIcon != null) return cachedIcon;

        String[] names = {
                "msg_settings",
                "msg_settings_old",
                "msg_settings_ny",
                "msg_settings_14",
                "msg_settings_hw"
        };

        for (String name : names) {
            try {
                int drawableResource = XReflect.getStaticIntField(ClassLoad.getClass(ClassNames.DRAWABLE), name);
                if (drawableResource != 0) {
                    cachedIcon = drawableResource;
                    return drawableResource;
                }
            } catch (Throwable ignored) {}
        }

        return 0;
    }

}
