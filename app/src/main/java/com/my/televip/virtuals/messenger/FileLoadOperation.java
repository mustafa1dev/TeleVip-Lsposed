package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class FileLoadOperation {

    private final Object fileOperation;

    public FileLoadOperation(Object fileOperation){ this.fileOperation = fileOperation; }

    public void setDownloadChunkSizeBig(int v){
        XposedHelpers.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "downloadChunkSizeBig", AutomationResolver.ResolverType.Field), v);
    }

    public void setMaxDownloadRequests(int v){
        XposedHelpers.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "maxDownloadRequests", AutomationResolver.ResolverType.Field), v);
    }

    public void setMaxDownloadRequestsBig(int v){
        XposedHelpers.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "maxDownloadRequestsBig", AutomationResolver.ResolverType.Field), v);
    }

    public void setMaxCdnParts(int v){
        XposedHelpers.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "maxCdnParts", AutomationResolver.ResolverType.Field), v);
    }

}
