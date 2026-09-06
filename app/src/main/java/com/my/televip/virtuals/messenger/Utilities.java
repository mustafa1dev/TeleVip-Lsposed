package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class Utilities {

    public static DispatchQueue getStageQueue(){
        return new DispatchQueue(XReflect.getStaticObjectField(ClassLoad.getClass(ClassNames.UTILITIES), AutomationResolver.resolve("Utilities", "stageQueue", AutomationResolver.ResolverType.Field)));
    }
}
