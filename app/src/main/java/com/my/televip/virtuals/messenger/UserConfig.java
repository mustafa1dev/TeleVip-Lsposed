package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.tgnet.TLRPC;

import com.my.televip.reflect.XReflect;

public class UserConfig {

    Object userConfig;

    public UserConfig(Object obl){
        userConfig = obl;
    }

    public static int getSelectedAccount() {
        String selectedAccountField = AutomationResolver.resolve("UserConfig", "selectedAccount", AutomationResolver.ResolverType.Field);
        return XReflect.getStaticIntField(ClassLoad.getClass(ClassNames.USER_CONFIG), selectedAccountField);
    }

    public long getClientUserId(){
        return XReflect.getLongField(userConfig, AutomationResolver.resolve("UserConfig" , "clientUserId", AutomationResolver.ResolverType.Field));
    }

    public TLRPC.User getCurrentUser(){
        return new TLRPC.User(XReflect.callMethod(userConfig, AutomationResolver.resolve("UserConfig" , "getCurrentUser", AutomationResolver.ResolverType.Method)));
    }

}
