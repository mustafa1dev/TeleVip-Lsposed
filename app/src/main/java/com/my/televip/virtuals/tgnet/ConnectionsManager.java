package com.my.televip.virtuals.tgnet;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class ConnectionsManager {
    final Object connectionsManager;

    public ConnectionsManager(Object instance)
    {
        this.connectionsManager = instance;
    }

    public void sendRequest(Object object, Object completionBlock) {
        XposedHelpers.callMethod(connectionsManager, AutomationResolver.resolve("ConnectionsManager", "sendRequest", AutomationResolver.ResolverType.Method), object, completionBlock);
    }

    public static ConnectionsManager getInstance(int num){
        return new ConnectionsManager(XposedHelpers.callStaticMethod(loadClass.getConnectionsManagerClass(), AutomationResolver.resolve("ConnectionsManager", "getInstance", AutomationResolver.ResolverType.Method), num));
    }
}
