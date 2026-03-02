package com.my.televip.virtuals.messenger;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class UserConfig {

    private static Object userConfig;

    public UserConfig(Object obl){
        userConfig = obl;
    }

    public static int getSelectedAccount() {
        String selectedAccountField = AutomationResolver.resolve("UserConfig", "selectedAccount", AutomationResolver.ResolverType.Field);
        return XposedHelpers.getStaticIntField(loadClass.getUserConfigClass(), selectedAccountField);
    }

    public int getClientUserId(){
        return (int) XposedHelpers.getLongField(userConfig, AutomationResolver.resolve("UserConfig" , "clientUserId", AutomationResolver.ResolverType.Field));
    }

}
