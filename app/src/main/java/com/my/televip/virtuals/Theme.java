package com.my.televip.virtuals;

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

    public static int getColor(int key){
        return (int)XposedHelpers.callStaticMethod(loadClass.getThemeClass(), "getColor", key);
    }

    public static int getKey_windowBackgroundGray(){
        return (int)XposedHelpers.getStaticObjectField(loadClass.getThemeClass(), "key_windowBackgroundGray");
    }

    public static int getKey_actionBarDefault(){
        return (int)XposedHelpers.getStaticObjectField(loadClass.getThemeClass(), "key_actionBarDefault");
    }

    public static int getKey_actionBarDefaultTitle(){
        return (int)XposedHelpers.getStaticObjectField(loadClass.getThemeClass(), "key_actionBarDefaultTitle");
    }

    public static int getKey_actionBarDefaultIcon(){
        return (int)XposedHelpers.getStaticObjectField(loadClass.getThemeClass(), "key_actionBarDefaultIcon");
    }

    public static int getKey_windowBackgroundWhite(){
        return (int)XposedHelpers.getStaticObjectField(loadClass.getThemeClass(), "key_windowBackgroundWhite");
    }

    public static int getKey_switchTrackBlueChecked(){
        return (int)XposedHelpers.getStaticObjectField(loadClass.getThemeClass(), "key_switchTrackBlueChecked");
    }



}
