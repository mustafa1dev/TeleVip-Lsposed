package com.my.televip;

import java.util.ArrayList;

public class Utils {
    public static String pkgName = null;
    public static String modulePath = null;
    public static ClassLoader classLoader = null;
    public static final String issue = "Your telegram may have been modified! You can submit issue to let developer to try support to the telegram client you are using.";


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

}
