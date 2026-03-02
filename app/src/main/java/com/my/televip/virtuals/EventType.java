package com.my.televip.virtuals;


import com.my.televip.Utils;
import com.my.televip.loadClass;

import de.robv.android.xposed.XposedHelpers;

public class EventType {


    public static int getIconSettings() {
        if (loadClass.getDrawableClass() == null) {
            Utils.log("Not found org.telegram.messenger.R$drawable, " + Utils.issue);
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
                int drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), name);
                if (drawableResource != 0) {
                    return drawableResource;
                }
            } catch (Throwable ignored) {}
        }

        return 0;
    }

}
