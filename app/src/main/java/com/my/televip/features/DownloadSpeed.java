package com.my.televip.features;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.FileLoadOperation;

import de.robv.android.xposed.XposedHelpers;

public class DownloadSpeed {

    public static boolean isEnable = false;

    public static void init(){

        isEnable = true;

        try {

            if (loadClass.getFileLoadOperationClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getFileLoadOperationClass(), AutomationResolver.resolve("FileLoadOperation", "updateParams", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        if (FeatureManager.getBoolean(FeatureManager.KEY_DOWNLOAD_SPEED)) {
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
        } catch (Throwable t){
            Utils.log(t);
        }
    }

}
