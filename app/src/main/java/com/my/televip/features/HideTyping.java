package com.my.televip.features;

public class HideTyping {

    public static boolean isTypingRequest(Object object){
        return object.getClass().getName().contains("TL_messages_setTyping") ||
                object.getClass().getName().contains("TL_messages_setEncryptedTyping");
    }
}
