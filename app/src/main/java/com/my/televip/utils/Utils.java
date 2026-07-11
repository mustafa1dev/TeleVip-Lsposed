package com.my.televip.utils;

import java.util.ArrayList;

public class Utils {
    public static String pkgName = null;
    public static String modulePath = null;
    public static ClassLoader classLoader = null;
    public static final String issue = "Your Telegram client may be an incompatible version with TeleVip. Please download the latest version that is compatible with TeleVip.";

    public static <T> ArrayList<T> castList(Object obj, Class<T> clazz)
    {
        ArrayList<T> result = new ArrayList<>();
        if (obj instanceof ArrayList<?>)
        {
            for (Object o : (ArrayList<?>) obj)
                result.add(clazz.cast(o));

            return result;
        }
        return result;
    }

    public static String getFieldAsString(Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof CharSequence) {
            return value.toString();
        }

        return String.valueOf(value);
    }

}
