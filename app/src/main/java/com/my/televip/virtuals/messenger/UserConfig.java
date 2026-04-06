package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.tgnet.TLRPC;

import de.robv.android.xposed.XposedHelpers;

public class UserConfig {

    Object userConfig;

    public UserConfig(Object obl){
        userConfig = obl;
    }

    public static int getSelectedAccount() {
        String selectedAccountField = AutomationResolver.resolve("UserConfig", "selectedAccount", AutomationResolver.ResolverType.Field);
        return XposedHelpers.getStaticIntField(ClassLoad.getClass(ClassNames.USER_CONFIG), selectedAccountField);
    }

    public long getClientUserId(){
        return XposedHelpers.getLongField(userConfig, AutomationResolver.resolve("UserConfig" , "clientUserId", AutomationResolver.ResolverType.Field));
    }

    public TLRPC.User getCurrentUser(){
        return new TLRPC.User(XposedHelpers.callMethod(userConfig, AutomationResolver.resolve("UserConfig" , "getCurrentUser", AutomationResolver.ResolverType.Method)));
    }

}
