package com.my.televip.virtuals.messenger.browser;

import android.content.Context;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class Browser {

    public static void openUrl(Context context, String url){
        XReflect.callStaticMethod(
                ClassLoad.getClass(ClassNames.BROWSER),
                AutomationResolver.resolve("Browser", "openUrl", AutomationResolver.ResolverType.Method), context, url
        );
    }

}
