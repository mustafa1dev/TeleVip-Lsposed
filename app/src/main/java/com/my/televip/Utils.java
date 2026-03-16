package com.my.televip;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.my.televip.obfuscate.struct.ResolverRegistry;

import java.io.File;
import java.util.ArrayList;

import de.robv.android.xposed.XposedBridge;

public class Utils {
    public static String pkgName = null;
    public static final String issue = "Your telegram may have been modified! You can submit issue to let developer to try support to the telegram client you are using.";
    private static final Gson BUILDER_GSON = new GsonBuilder().setPrettyPrinting().create();
    //public static File deletedMessagesSavePath = null;
    public static File deletedMessagesDatabasePath = null;

    public static void log(String text)
    {
        XposedBridge.log("[TeleVip] pkgName: "+ pkgName +" " + text);
    }

    public static void log(Throwable throwable) {
        StringBuilder log = new StringBuilder();

        log.append("[TeleVip] pkgName: ").append(pkgName).append(" ").append(throwable).append("\n");
        log.append("appName = ").append(ResolverRegistry.getResolverClass().getSimpleName()).append("\n");

        try {
            PackageManager pm = MainHook.launchActivity.getPackageManager();
            PackageInfo info = pm.getPackageInfo(MainHook.launchActivity.getPackageName(), 0);
            String versionName = info.versionName;
            int versionCode = info.versionCode;

            log.append("versionName: ").append(versionName).append("\n");
            log.append("versionCode: ").append(versionCode).append("\n");
        } catch (Exception e) {
            log.append("versionName/versionCode: error retrieving\n");
        }

        log.append("OS Version: ").append(Build.VERSION.RELEASE).append("\n");
        log.append("SDK: ").append(Build.VERSION.SDK_INT).append("\n");
        log.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
        log.append("Model: ").append(Build.MODEL).append("\n");

        for (StackTraceElement element : throwable.getStackTrace()) {
            log.append("[TeleVip] at ").append(element.toString()).append("\n");
        }

        XposedBridge.log(log.toString());
    }


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
    public static Gson getBuilderGson() {
        return BUILDER_GSON;
    }
}
