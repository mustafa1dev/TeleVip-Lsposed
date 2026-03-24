package com.my.televip.virtuals.messenger;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class Utilities {

    public static DispatchQueue getStageQueue(){
        return new DispatchQueue(XposedHelpers.getStaticObjectField(loadClass.getUtilitiesClass(), AutomationResolver.resolve("Utilities", "stageQueue", AutomationResolver.ResolverType.Field)));
    }
}
