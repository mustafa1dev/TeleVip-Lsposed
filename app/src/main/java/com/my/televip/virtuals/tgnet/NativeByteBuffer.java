package com.my.televip.virtuals.tgnet;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class NativeByteBuffer {

    public final Object nativeByteBuffer;

    public NativeByteBuffer(Object obj){
        nativeByteBuffer = obj;
    }
    public NativeByteBuffer(boolean calculate){
        nativeByteBuffer = XReflect.newInstance(ClassLoad.getClass(ClassNames.NATIVE_BYTE_BUFFER), calculate);
    }

    public void reuse(){
        XReflect.callMethod(nativeByteBuffer, AutomationResolver.resolve("NativeByteBuffer", "reuse", AutomationResolver.ResolverType.Method));
    }

    public int readInt32(boolean exception) {
        return (int) XReflect.callMethod(nativeByteBuffer, AutomationResolver.resolve("NativeByteBuffer","readInt32", AutomationResolver.ResolverType.Method), exception);
    }

    public void position(int i) {
        XReflect.callMethod(nativeByteBuffer, AutomationResolver.resolve("NativeByteBuffer","position", AutomationResolver.ResolverType.Method), i);
    }

    public void writeInt32(int i) {
        XReflect.callMethod(nativeByteBuffer, AutomationResolver.resolve("NativeByteBuffer","writeInt32", AutomationResolver.ResolverType.Method), i);
    }


}
