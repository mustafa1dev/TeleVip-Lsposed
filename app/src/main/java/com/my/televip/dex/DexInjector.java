package com.my.televip.dex;

import com.my.televip.MainHook;
import com.my.televip.Utils;

import java.nio.ByteBuffer;

import dalvik.system.InMemoryDexClassLoader;

public class DexInjector {

    public static ClassLoader classLoader;

    public static void injectDex() {
        try {
            byte[] dexBytes = DexHolder.DEX_BYTES;

            ByteBuffer buffer = ByteBuffer.wrap(dexBytes);

            classLoader = new InMemoryDexClassLoader(
                    buffer,
                    MainHook.lpparam.classLoader
            );
        } catch (Exception e){
            Utils.log(e);
        }

    }

}
