package com.my.televip.virtuals.messenger;

import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class ImageReceiver {

    public Object imageReceiver;

    public ImageReceiver(Object imageReceiver){
        this.imageReceiver = imageReceiver;
    }

    public ImageLocation getImageLocation() {
        return new ImageLocation(XReflect.callMethod(imageReceiver, AutomationResolver.resolve("ImageReceiver", "getImageLocation", AutomationResolver.ResolverType.Method)));
    }

}
