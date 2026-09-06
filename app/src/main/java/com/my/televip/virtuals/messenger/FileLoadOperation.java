package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class FileLoadOperation {

    private final Object fileOperation;

    public FileLoadOperation(Object fileOperation){ this.fileOperation = fileOperation; }

    public void setDownloadChunkSizeBig(int v){
        XReflect.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "downloadChunkSizeBig", AutomationResolver.ResolverType.Field), v);
    }

    public void setMaxDownloadRequests(int v){
        XReflect.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "maxDownloadRequests", AutomationResolver.ResolverType.Field), v);
    }

    public void setMaxDownloadRequestsBig(int v){
        XReflect.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "maxDownloadRequestsBig", AutomationResolver.ResolverType.Field), v);
    }

    public void setMaxCdnParts(int v){
        XReflect.setIntField(fileOperation, AutomationResolver.resolve("FileLoadOperation", "maxCdnParts", AutomationResolver.ResolverType.Field), v);
    }

}
