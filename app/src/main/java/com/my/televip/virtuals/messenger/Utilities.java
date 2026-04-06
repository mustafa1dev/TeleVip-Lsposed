package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class Utilities {

    public static DispatchQueue getStageQueue(){
        return new DispatchQueue(XposedHelpers.getStaticObjectField(ClassLoad.getClass(ClassNames.UTILITIES), AutomationResolver.resolve("Utilities", "stageQueue", AutomationResolver.ResolverType.Field)));
    }
}
