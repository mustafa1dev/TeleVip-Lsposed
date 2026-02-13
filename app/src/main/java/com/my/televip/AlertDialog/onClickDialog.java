package com.my.televip.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.CheckBox;
import android.widget.EditText;

import com.my.televip.ClientChecker;
import com.my.televip.MainHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Language;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.xSharedPreferences;

import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class onClickDialog extends Language {
    public static void onClickOpenUrl(Context applicationContext, final Object LaunchActivtiy) {
        XposedHelpers.callStaticMethod(
                XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.browser.Browser"), MainHook.lpparam.classLoader),
                AutomationResolver.resolve("Browser", "openUrl", AutomationResolver.ResolverType.Method), applicationContext, "https://t.me/t_l0_e"
        );
        if (LaunchActivtiy != null) {
            Object drawerLayoutContainer = XposedHelpers.getObjectField(LaunchActivtiy, AutomationResolver.resolve("LaunchActivity", "drawerLayoutContainer", AutomationResolver.ResolverType.Field));
            if (drawerLayoutContainer != null) {
                XposedHelpers.callMethod(drawerLayoutContainer, AutomationResolver.resolve("DrawerLayoutContainer", "closeDrawer", AutomationResolver.ResolverType.Method));
            }
        }
            //XposedHelpers.callMethod(dialog, "dismiss");
    }

    public static void onClickSave(List<CheckBox> checkBoxes){
            // الكود الذي يتم تنفيذه عند الضغط على الزر
            for (int i = 0; i < checkBoxes.size(); i++) {
                CheckBox checkBox = checkBoxes.get(i);
                if (checkBox.isChecked()) {
                    if (checkBox.getText().toString().equals(TelegramPremium)) {
                        xSharedPreferences.SharedPre.edit().putString("prem", "true").apply();
                    } else if (checkBox.getText().toString().equals(HideSeenUser)) {
                        xSharedPreferences.SharedPre.edit().putString("noRead", "true").apply();
                    } else if (checkBox.getText().toString().equals(HideSeenGroups)) {
                        xSharedPreferences.SharedPre.edit().putString("noRead2", "true").apply();
                    } else if (checkBox.getText().toString().equals(HideTyping)) {
                        xSharedPreferences.SharedPre.edit().putString("NoTyping", "true").apply();
                    } else if (checkBox.getText().toString().equals(HideStoryView)) {
                        xSharedPreferences.SharedPre.edit().putString("noStoryRead", "true").apply();
                    } else if (checkBox.getText().toString().equals(UnlockAllRestricted)) {
                        xSharedPreferences.SharedPre.edit().putString("usefolow", "true").apply();
                    } else if (checkBox.getText().toString().equals(AllowSavingvideos)) {
                        xSharedPreferences.SharedPre.edit().putString("allowShare", "true").apply();
                    } else if (checkBox.getText().toString().equals(HideOnline)) {
                        xSharedPreferences.SharedPre.edit().putString("HideOnline", "true").apply();
                    } else if (checkBox.getText().toString().equals(PreventMedia)) {
                        xSharedPreferences.SharedPre.edit().putString("PreventMedia", "true").apply();
                    } else if (checkBox.getText().toString().equals(HidePhone)) {
                        xSharedPreferences.SharedPre.edit().putString("HidePhone", "true").apply();
                    } else if (checkBox.getText().toString().equals(ShowDeletedMessages)) {
                        xSharedPreferences.SharedPre.edit().putString("shmsdel", "true").apply();
                    } else if (checkBox.getText().toString().equals(DisableStories)) {
                        xSharedPreferences.SharedPre.edit().putString("hidestore", "true").apply();
                    }
                } else {
                    if (checkBox.getText().toString().equals(TelegramPremium)) {
                        xSharedPreferences.SharedPre.edit().remove("prem").apply();
                    } else if (checkBox.getText().toString().equals(HideSeenUser)) {
                        xSharedPreferences.SharedPre.edit().remove("noRead").apply();
                    } else if (checkBox.getText().toString().equals(HideSeenGroups)) {
                        xSharedPreferences.SharedPre.edit().remove("noRead2").apply();
                    } else if (checkBox.getText().toString().equals(HideTyping)) {
                        xSharedPreferences.SharedPre.edit().remove("NoTyping").apply();
                    } else if (checkBox.getText().toString().equals(HideStoryView)) {
                        xSharedPreferences.SharedPre.edit().remove("noStoryRead").apply();
                    } else if (checkBox.getText().toString().equals(UnlockAllRestricted)) {
                        xSharedPreferences.SharedPre.edit().remove("usefolow").apply();
                    } else if (checkBox.getText().toString().equals(AllowSavingvideos)) {
                        xSharedPreferences.SharedPre.edit().remove("allowShare").apply();
                    } else if (checkBox.getText().toString().equals(HideOnline)) {
                        xSharedPreferences.SharedPre.edit().remove("HideOnline").apply();
                    } else if (checkBox.getText().toString().equals(PreventMedia)) {
                        xSharedPreferences.SharedPre.edit().remove("PreventMedia").apply();
                    } else if (checkBox.getText().toString().equals(HidePhone)) {
                        xSharedPreferences.SharedPre.edit().remove("HidePhone").apply();
                    } else if (checkBox.getText().toString().equals(ShowDeletedMessages)) {
                        xSharedPreferences.SharedPre.edit().remove("shmsdel").apply();
                    } else if (checkBox.getText().toString().equals(DisableStories)) {
                        xSharedPreferences.SharedPre.edit().remove("hidestore").apply();
                    }
                }
            }

            // غلق الـ AlertDialog بعد التحقق
          //  XposedHelpers.callMethod(dialog, "dismiss");

        FeatureManager.readFeature();
    }
    public static void onClickToMessageId(EditText editText, Object chatActivity){
        String inputText = editText.getText().toString().trim();
        // التحقق من المدخلات
        if (!inputText.isEmpty()) {
            int msid = Integer.parseInt(inputText);
            XposedHelpers.callMethod(chatActivity, AutomationResolver.resolve("ChatActivity", "scrollToMessageId", AutomationResolver.ResolverType.Method), msid, 0, true, 0, true, 0);
            // XposedBridge.log("scrollToMessageId is call.");

        }
    }
    public static void onClickDismiss(DialogInterface dialog){
        XposedHelpers.callMethod(dialog, AutomationResolver.resolve("AlertDialog", "dismiss", AutomationResolver.ResolverType.Method));
    }
}
