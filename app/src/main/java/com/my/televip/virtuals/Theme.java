package com.my.televip.virtuals;

import android.graphics.Color;
import android.text.TextPaint;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.obfuscate.AutomationResolver;

import com.my.televip.reflect.XReflect;

public class Theme {

    public static TextPaint getTextPaint()
    {
        return (TextPaint) XReflect.getStaticObjectField(ClassLoad.getClass(ClassNames.THEME), AutomationResolver.resolve("Theme", "chat_timePaint", AutomationResolver.ResolverType.Field));
    }

    public static boolean isLight(){
        return !((boolean)XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.THEME), AutomationResolver.resolve("Theme", "isCurrentThemeDark", AutomationResolver.ResolverType.Method)));
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

    public static int getToolBarRippleColor(){
        return isLight() ? 0x20000000 : 0x20FFFFFF;
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
