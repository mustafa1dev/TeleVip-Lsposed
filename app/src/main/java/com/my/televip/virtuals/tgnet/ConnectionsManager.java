package com.my.televip.virtuals.tgnet;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class ConnectionsManager {
    final Object connectionsManager;

    public ConnectionsManager(Object instance)
    {
        this.connectionsManager = instance;
    }

    public void sendRequest(Object object, Object completionBlock) {
        XReflect.callMethod(connectionsManager, AutomationResolver.resolve("ConnectionsManager", "sendRequest", AutomationResolver.ResolverType.Method), object, completionBlock);
    }

    public static ConnectionsManager getInstance(int num){
        return new ConnectionsManager(XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.CONNECTIONS_MANAGER), AutomationResolver.resolve("ConnectionsManager", "getInstance", AutomationResolver.ResolverType.Method), num));
    }
}
