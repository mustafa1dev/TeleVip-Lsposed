package com.my.televip.logging;

import static com.my.televip.utils.Utils.pkgName;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.obfuscate.struct.ResolverRegistry;

import com.my.televip.xposed.XBridge;

public class Logger {

    public static void w(String text)
    {
        XBridge.log("[TeleVip] [Warning] pkgName: "+ pkgName + " " + text);
    }

    public static void l(String text)
    {
        XBridge.log("[TeleVip] pkgName: "+ pkgName +" " + text);
    }

    public static void e(Throwable throwable) {
        try {
            StringBuilder log = new StringBuilder();

            log.append("[TeleVip] [Error] pkgName: ").append(pkgName).append(" ").append(throwable).append("\n");
            log.append("appName = ").append(ResolverRegistry.getResolverClass().getSimpleName()).append("\n");

            try {
                PackageManager pm = ApplicationLoaderHook.getApplicationContext().getPackageManager();
                PackageInfo info = pm.getPackageInfo(ApplicationLoaderHook.getApplicationContext().getPackageName(), 0);
                String versionName = info.versionName;
                int versionCode = info.versionCode;

                log.append("versionName: ").append(versionName).append("\n");
                log.append("versionCode: ").append(versionCode).append("\n");
            } catch (Throwable e) {
                log.append("versionName/versionCode: error retrieving\n");
            }

            log.append("OS Version: ").append(Build.VERSION.RELEASE).append("\n");
            log.append("SDK: ").append(Build.VERSION.SDK_INT).append("\n");
            log.append("Manufacturer: ").append(Build.MANUFACTURER).append("\n");
            log.append("Model: ").append(Build.MODEL).append("\n");

            for (StackTraceElement element : throwable.getStackTrace()) {
                log.append("[TeleVip] at ").append(element.toString()).append("\n");
            }

            XBridge.log(log.toString());
        } catch (Throwable g) {}
    }

}
