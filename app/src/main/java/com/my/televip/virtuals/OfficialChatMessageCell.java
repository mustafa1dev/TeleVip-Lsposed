package com.my.televip.virtuals;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;

import com.my.televip.reflect.XReflect;

public class OfficialChatMessageCell extends ChatMessageCellDefault {
    public OfficialChatMessageCell(Object instance) {
        super(instance);
    }

    public CharSequence getCurrentTimeString()
    {
        return (CharSequence) XReflect.getObjectField(this.instance, AutomationResolver.resolve("ChatMessageCell", "currentTimeString", AutomationResolver.ResolverType.Field));
    }

    public void setCurrentTimeString(CharSequence currentTimeString)
    {
        try
        {
            XReflect.setObjectField(this.instance, AutomationResolver.resolve("ChatMessageCell", "currentTimeString", AutomationResolver.ResolverType.Field), currentTimeString);
        }
        catch (Throwable e)
        {
            Logger.e(e);
        }
    }
}
