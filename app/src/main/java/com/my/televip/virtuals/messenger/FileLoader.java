package com.my.televip.virtuals.messenger;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;

import java.io.File;

import com.my.televip.reflect.XReflect;

public class FileLoader {

    Object fileLoader;

    public FileLoader(Object fileLoader){
        this.fileLoader = fileLoader;
    }

    public File getLocalFile(ImageLocation location) {
        return (File) XReflect.callMethod(fileLoader, AutomationResolver.resolve("FileLoader", "getLocalFile", AutomationResolver.ResolverType.Method), location.imageLocation);
    }

    public static FileLoader getInstance(int num) {
        return new FileLoader(XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.FILE_LOADER), AutomationResolver.resolve("FileLoader", "getInstance", AutomationResolver.ResolverType.Method), num));
    }

}
