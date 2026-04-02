package com.my.televip.virtuals;

import android.graphics.Color;
import android.text.TextPaint;

import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XposedHelpers;

public class Theme {

    public static TextPaint getTextPaint(ClassLoader classLoader)
    {
        Class<?> theme = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.Theme"), classLoader);
        List<Field> fields = new ArrayList<>();
        for (Field declaredField : theme.getDeclaredFields())
            if (declaredField.getName().equals(AutomationResolver.resolve("Theme", "chat_timePaint", AutomationResolver.ResolverType.Field)))
                fields.add(declaredField);

        if (!fields.isEmpty()) {
            try
            {
                Field textPaintField = null;
                for (Field field : fields) {
                    if (field.getType().equals(TextPaint.class))
                    {
                        textPaintField = field;
                    }
                }
                if (textPaintField != null)
                    return (TextPaint) textPaintField.get(null);
                else {
                    for (Field field : fields) {
                        if (field.getType().getName().contains("TextPaint"))
                        {
                            textPaintField = field;
                        }
                    }
                    if (textPaintField != null)
                        return (TextPaint) textPaintField.get(null);
                    else
                        Utils.log("Not found chat_timePaint field in Theme, " + Utils.issue);
                }
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
        }
        else
            Utils.log("Not found chat_timePaint field in Theme, " + Utils.issue);

        return null;
    }

    public static boolean isLight(){
        return !((boolean)XposedHelpers.callStaticMethod(loadClass.getThemeClass(), AutomationResolver.resolve("Theme", "isCurrentThemeDark", AutomationResolver.ResolverType.Method)));
    }

    public static int getBackgroundGrayColor(){
        return isLight() ? Color.rgb(241, 241, 243) : Color.rgb(21, 30, 39);
    }

    public static int getBackgroundWhiteOrBlueColor(){
        return isLight() ? Color.WHITE : Color.rgb(29, 39, 51);
    }

    public static int getToolBarColor(){
        return isLight() ? Color.WHITE : Color.rgb(36, 45, 57);
    }

    public static int getTextToolBarColor(){
        return isLight() ? Color.BLACK : Color.WHITE;
    }
    public static int getTextColor(){
        return isLight() ? Color.BLACK : Color.WHITE;
    }

    public static int getTextBlueColor(){
        return isLight() ? Color.rgb(100, 164, 221) : Color.rgb(112, 184, 221);
    }
    public static int getTextGrayColor(){
        return isLight() ? Color.rgb(128, 128, 128) : Color.rgb(103,115,128);
    }

    public static int getArrowDrawableColor(){
        return isLight() ? Color.BLACK : Color.WHITE;
    }

}
