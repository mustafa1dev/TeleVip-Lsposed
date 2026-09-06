package com.my.televip.virtuals;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;

import com.my.televip.reflect.XReflect;

public class ChatMessageCellDefault {
    protected final Object instance;

    protected ChatMessageCellDefault(Object instance)
    {
        this.instance = instance;
    }

    public int getTimeTextWidth()
    {
        return XReflect.getIntField(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeTextWidth", AutomationResolver.ResolverType.Field));
    }

    public int getTimeWidth()
    {
        return XReflect.getIntField(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeWidth", AutomationResolver.ResolverType.Field));
    }

    public void setTimeTextWidth(int width)
    {
        try
        {
            XReflect.setIntField(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeTextWidth", AutomationResolver.ResolverType.Field), width);
        }
        catch (Throwable e)
        {
            Logger.e(e);
        }
    }

    public void setTimeWidth(int width)
    {
        try
        {
            XReflect.setIntField(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeWidth", AutomationResolver.ResolverType.Field), width);
        }
        catch (Throwable e)
        {
            Logger.e(e);
        }
    }
}
