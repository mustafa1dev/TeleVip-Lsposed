package com.my.televip.virtuals;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.Utils;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.FieldUtils;

public class ChatMessageCellDefault {
    protected final Object instance;

    protected ChatMessageCellDefault(Object instance)
    {
        this.instance = instance;
    }

    public int getTimeTextWidth()
    {
        return FieldUtils.getFieldIntOfClass(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeTextWidth", AutomationResolver.ResolverType.Field));
    }

    public int getTimeWidth()
    {
        return FieldUtils.getFieldIntOfClass(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeWidth", AutomationResolver.ResolverType.Field));
    }

    public void setTimeTextWidth(int width)
    {
        try
        {
            XposedHelpers.setIntField(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeTextWidth", AutomationResolver.ResolverType.Field), width);
            //Field timeTextWidthField = FieldUtils.getFieldOfClass(this.instance, "timeTextWidth");
            //if (timeTextWidthField != null)
            //    timeTextWidthField.setInt(this.instance, width);
            //else
            //    throw new NullPointerException("Not found timeTextWidth in " + this.instance.getClass().getName());
        }
        catch (Throwable e)
        {
            Utils.log(e);
        }
    }

    public void setTimeWidth(int width)
    {
        try
        {
            XposedHelpers.setIntField(this.instance, AutomationResolver.resolve("ChatMessageCell", "timeWidth", AutomationResolver.ResolverType.Field), width);
            /*Field timeWidthField = FieldUtils.getFieldOfClass(this.instance, "timeWidth");
            if (timeWidthField != null)
                timeWidthField.setInt(this.instance, width);
            else
                throw new NullPointerException("Not found timeWidth in " + this.instance.getClass().getName());*/
        }
        catch (Throwable e)
        {
            Utils.log(e);
        }
    }
}
