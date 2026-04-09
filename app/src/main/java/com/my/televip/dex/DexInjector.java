package com.my.televip.dex;

import com.my.televip.logging.Logger;

import java.nio.ByteBuffer;

import dalvik.system.InMemoryDexClassLoader;

public class DexInjector {

    public static ClassLoader classLoader;

    public static void injectDex(ClassLoader classLoader) {
        try {
            byte[] dexBytes = DexHolder.DEX_BYTES;

            ByteBuffer buffer = ByteBuffer.wrap(dexBytes);

            DexInjector.classLoader = new InMemoryDexClassLoader(
                    buffer,
                    classLoader
            );
        } catch (Throwable e){
            Logger.e(e);
        }

    }

}
