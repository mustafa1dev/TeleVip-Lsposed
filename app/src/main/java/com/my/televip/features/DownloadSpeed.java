package com.my.televip.features;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.FileLoadOperation;

public class DownloadSpeed {

    public static boolean isEnable = false;

    public static void init(){
        try {
            if (!isEnable) {
                isEnable = true;

                if (ClassLoad.getClass(ClassNames.FILE_LOAD_OPERATION) != null) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.FILE_LOAD_OPERATION), AutomationResolver.resolve("FileLoadOperation", "updateParams", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) {
                            if (ConfigManager.downloadSpeed.isEnable()) {
                                FileLoadOperation fileLoadOperation = new FileLoadOperation(param.thisObject);

                                int downloadChunkSizeBig = 1024 * 512;
                                int maxDownloadRequests = 8;

                                long defaultMaxFileSize = 1024L * 1024L * 2000L;

                                int maxCdnParts = (int) (defaultMaxFileSize / downloadChunkSizeBig);

                                fileLoadOperation.setDownloadChunkSizeBig(downloadChunkSizeBig);
                                fileLoadOperation.setMaxDownloadRequests(maxDownloadRequests);
                                fileLoadOperation.setMaxDownloadRequestsBig(maxDownloadRequests);
                                fileLoadOperation.setMaxCdnParts(maxCdnParts);
                                param.setResult(null);
                            }

                        }
                    });
                }
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }

}
