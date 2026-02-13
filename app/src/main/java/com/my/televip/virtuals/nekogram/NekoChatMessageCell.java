package com.my.televip.virtuals.nekogram;

import android.text.SpannableStringBuilder;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.Utils;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.FieldUtils;
import com.my.televip.virtuals.ChatMessageCellDefault;

public class NekoChatMessageCell extends ChatMessageCellDefault {
    public NekoChatMessageCell(Object instance) {
        super(instance);
    }

    public SpannableStringBuilder getCurrentTimeString()
    {
        return (SpannableStringBuilder) FieldUtils.getFieldClassOfClass(this.instance, AutomationResolver.resolve("ChatMessageCell", "currentTimeString", AutomationResolver.ResolverType.Field));
    }

    public void setCurrentTimeString(SpannableStringBuilder currentTimeString)
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
            Utils.log(e);
        }
    }
}
