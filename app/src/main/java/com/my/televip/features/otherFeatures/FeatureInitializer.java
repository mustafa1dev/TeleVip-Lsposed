package com.my.televip.features.otherFeatures;

import android.content.Context;

import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class FeatureInitializer {

    public static void init(Context context) {
        try {

            if (!FeatureStateManager.isChatEnabled() || !FeatureStateManager.isProfileEnabled()) {

                Class<?> actionBarClass = XposedHelpers.findClassIfExists(
                        AutomationResolver.resolve("org.telegram.ui.ActionBar.ActionBar"),
                        Utils.classLoader
                );

                HMethod.hookMethod(
                        actionBarClass,
                        AutomationResolver.resolve("ActionBar", "setActionBarMenuOnItemClick", AutomationResolver.ResolverType.Method),
                        new AbstractMethodHook() {
                            @Override
                            protected void beforeMethod(MethodHookParam param) {

                                Object clazz = param.args[0];

                                if (clazz == null) return;

                                String name = clazz.getClass().getName();

                                if (name.contains("ChatActivity") && !FeatureStateManager.isChatEnabled()) {
                                    FeatureStateManager.saveChat(name);
                                    ChatHook.init(context, name);
                                }

                                if (name.contains("ProfileActivity") && !FeatureStateManager.isProfileEnabled()) {
                                    FeatureStateManager.saveProfile(name);
                                    ProfileHook.init(context, name);
                                }
                            }
                        });

            } else {
                ChatHook.init(context, FeatureStateManager.getChatClass());
                ProfileHook.init(context, FeatureStateManager.getProfileClass());
            }

        } catch (Throwable t) {
            Logger.e(t);
        }
    }
}