package com.my.televip.virtuals;


import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;

import de.robv.android.xposed.XposedHelpers;

public class EventType {


    public static int getIconSettings() {
        if (ClassLoad.getClass(ClassNames.DRAWABLE) == null) {
            return 0;
        }

        String[] names = {
                "msg_settings",
                "msg_settings_old",
                "msg_settings_ny",
                "msg_settings_14",
                "msg_settings_hw"
        };

        for (String name : names) {
            try {
                int drawableResource = XposedHelpers.getStaticIntField(ClassLoad.getClass(ClassNames.DRAWABLE), name);
                if (drawableResource != 0) {
                    return drawableResource;
                }
            } catch (Throwable ignored) {}
        }

        return 0;
    }

}
