package com.my.televip.virtuals;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.logging.Logger;

import de.robv.android.xposed.XposedHelpers;

public class OfficialChatMessageCell extends ChatMessageCellDefault {
    public OfficialChatMessageCell(Object instance) {
        super(instance);
    }

    public CharSequence getCurrentTimeString()
    {
        return (CharSequence) XposedHelpers.getObjectField(this.instance, AutomationResolver.resolve("ChatMessageCell", "currentTimeString", AutomationResolver.ResolverType.Field));
    }

    public void setCurrentTimeString(CharSequence currentTimeString)
    {
        try
        {
            XposedHelpers.setObjectField(this.instance, AutomationResolver.resolve("ChatMessageCell", "currentTimeString", AutomationResolver.ResolverType.Field), currentTimeString);
            /*Field currentTimeStringField = FieldUtils.getFieldOfClass(this.instance, "currentTimeString");
            if (currentTimeStringField != null)
                currentTimeStringField.set(this.instance, currentTimeString);
            else
                throw new NullPointerException("Not found currentTimeString in " + this.instance.getClass().getName());*/
        }
        catch (Throwable e)
        {
            Logger.e(e);
        }
    }
}
