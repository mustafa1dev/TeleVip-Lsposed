package com.my.televip.virtuals.ui;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.UserConfig;

import de.robv.android.xposed.XposedHelpers;

public class BaseFragment {

    Object baseFragment;

    public BaseFragment(Object obj){
        baseFragment = obj;
    }

    public UserConfig getUserConfig(){
        return new UserConfig(XposedHelpers.callMethod(baseFragment, AutomationResolver.resolve("BaseFragment", "getUserConfig", AutomationResolver.ResolverType.Method)));
    }

}
