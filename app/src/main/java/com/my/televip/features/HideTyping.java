package com.my.televip.features;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.ClientChecker;
import com.my.televip.obfuscate.AutomationResolver;

public class HideTyping {

    public static boolean isTypingRequest(Object object) {
        if (!ClientChecker.isTgnetObfuscated()) {
            String className = object.getClass().getName();
            return className.contains("TL_messages_setTyping") ||
                    className.contains("TL_messages_setEncryptedTyping");
        } else {
            Class<?> objectClass = object.getClass();
            return objectClass.equals(ClassLoad.getClass(AutomationResolver.resolve(ClassNames.TL_MESSAGES_SET_TYPING))) ||
                    objectClass.equals(ClassLoad.getClass(AutomationResolver.resolve(ClassNames.TL_MESSAGES_SET_ENCRYPTED_TYPING)));
        }
    }
}
