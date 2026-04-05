package com.my.televip.virtuals.messenger;

import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.io.File;

import de.robv.android.xposed.XposedHelpers;

public class FileLoader {

    Object fileLoader;

    public FileLoader(Object fileLoader){
        this.fileLoader = fileLoader;
    }

    public File getLocalFile(ImageLocation location) {
        return (File) XposedHelpers.callMethod(fileLoader, AutomationResolver.resolve("FileLoader", "getLocalFile", AutomationResolver.ResolverType.Method), location.imageLocation);
    }

    public static FileLoader getInstance(int num) {
        return new FileLoader(XposedHelpers.callStaticMethod(loadClass.getFileLoaderClass(), AutomationResolver.resolve("FileLoader", "getInstance", AutomationResolver.ResolverType.Method), num));
    }

}
