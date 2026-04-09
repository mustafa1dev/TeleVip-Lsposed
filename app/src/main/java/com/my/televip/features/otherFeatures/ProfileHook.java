package com.my.televip.features.otherFeatures;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.ClientChecker;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.ActionBarMenuItem;
import com.my.televip.virtuals.ui.ProfileActivity;

import de.robv.android.xposed.XposedHelpers;

public class ProfileHook {

    private static boolean initialized = false;

    public static void init(Context context, String className) {
        if (initialized || className == null) return;

        Class<?> clazz = ClassLoad.getClass(className);
        if (clazz == null) FeatureStateManager.reset(context);

        initialized = true;

        HMethod.hookMethod(ClassLoad.getClass(ClassNames.PROFILE_ACTIVITY), AutomationResolver.resolve("ProfileActivity", "createActionBarMenu", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createActionBarMenu", new Class[]{boolean.class}), new AbstractMethodHook() {
            @Override
            protected void afterMethod(MethodHookParam param) {
                ProfileActivity profileActivity = new ProfileActivity(param.thisObject);

                ActionBarMenuItem otherItem = profileActivity.getOtherItem();

                if (otherItem.getActionBarMenuItem() != null) {

                    int drawableResource = 0x7f0806d3;

                    if (!ClientChecker.check(ClientChecker.ClientType.Nagram) && !ClientChecker.check(ClientChecker.ClientType.Momogram)) {
                        drawableResource = XposedHelpers.getStaticIntField(ClassLoad.getClass(ClassNames.DRAWABLE), "msg_filled_menu_users");
                    }

                    if (getID(profileActivity) > 1) {
                        otherItem.addSubItem(8353847, drawableResource, String.valueOf(getID(profileActivity)));
                    }
                }
            }
        }));

        HMethod.hookMethod(clazz, "onItemClick", int.class, new AbstractMethodHook() {
            @Override
            protected void afterMethod(MethodHookParam param) {

                int id = (int) param.args[0];

                if (id == 8353847) {
                    final Object thisClass = XposedHelpers.getObjectField(param.thisObject, AutomationResolver.resolve("ProfileActivity", "this$0", AutomationResolver.ResolverType.Field));
                    ProfileActivity profile = new ProfileActivity(thisClass);

                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setPrimaryClip(ClipData.newPlainText("id", String.valueOf(getID(profile))));

                    Toast.makeText(context, String.valueOf(getID(profile)), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private static long getID(ProfileActivity profile) {
        if (profile.getUserId() > 1) {
            return profile.getUserId();
        } else {
            return profile.getChatId();
        }
    }

}