package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class DownloadSpeed {
    public static void init() {
        try {
            Class<?> FileLoadOperationClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.FileLoadOperation"), lpparam.classLoader);
            if (FileLoadOperationClass != null) {
                XposedHelpers.findAndHookMethod(FileLoadOperationClass, AutomationResolver.resolve("FileLoadOperation", "updateParams", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        int downloadChunkSizeBig;
                        int maxDownloadRequests = 12;
                        int maxDownloadRequestsBig = 12;
                        int maxCdnParts;
                        downloadChunkSizeBig = 1024 * 1024; // 1MB
                        long DefaulMaxFileSize = 1024L * 1024L * 2000L;

                        maxCdnParts = (int) (DefaulMaxFileSize / downloadChunkSizeBig);

                        XposedHelpers.setIntField(param.thisObject, AutomationResolver.resolve("FileLoadOperation", "downloadChunkSizeBig", AutomationResolver.ResolverType.Field), downloadChunkSizeBig);
                        XposedHelpers.setObjectField(param.thisObject, AutomationResolver.resolve("FileLoadOperation", "maxDownloadRequests", AutomationResolver.ResolverType.Field), maxDownloadRequests);
                        XposedHelpers.setObjectField(param.thisObject, AutomationResolver.resolve("FileLoadOperation", "maxDownloadRequestsBig", AutomationResolver.ResolverType.Field), maxDownloadRequestsBig);
                        XposedHelpers.setObjectField(param.thisObject, AutomationResolver.resolve("FileLoadOperation", "maxCdnParts", AutomationResolver.ResolverType.Field), maxCdnParts);


                    }
                });
            }
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
