package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class BaseController {

    Object baseController;

    public BaseController(Object obj){baseController = obj;}

    public UserConfig getUserConfig(){
        return new UserConfig(XposedHelpers.callMethod(baseController, AutomationResolver.resolve("BaseController", "getUserConfig", AutomationResolver.ResolverType.Method)));
    }

}
