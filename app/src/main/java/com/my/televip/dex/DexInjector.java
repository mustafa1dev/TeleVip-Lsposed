package com.my.televip.dex;

import com.my.televip.Utils;
import com.my.televip.utils.Logger;

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
                    Utils.classLoader
            );
        } catch (Exception e){
            Logger.e(e);
        }

    }

}
