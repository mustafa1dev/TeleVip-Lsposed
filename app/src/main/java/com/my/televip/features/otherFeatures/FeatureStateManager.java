package com.my.televip.features.otherFeatures;


import android.content.Context;

import com.my.televip.Configs.ConfigPreferences;

public class FeatureStateManager {

    public static final String KEY_CHAT = "ChatOnItemClick";
    public static final String KEY_CHAT_BOOL = "ChatOnItemClick_boolean";

    public static final String KEY_PROFILE = "ProfileOnItemClick";
    public static final String KEY_PROFILE_BOOL = "ProfileOnItemClick_boolean";

    public static boolean isChatEnabled() {
        return ConfigPreferences.getBoolean(KEY_CHAT_BOOL);
    }

    public static boolean isProfileEnabled() {
        return ConfigPreferences.getBoolean(KEY_PROFILE_BOOL);
    }

    public static String getChatClass() {
        return ConfigPreferences.getString(KEY_CHAT);
    }

    public static String getProfileClass() {
        return ConfigPreferences.getString(KEY_PROFILE);
    }

    public static void saveChat(String className) {
        ConfigPreferences.putBoolean(KEY_CHAT_BOOL, true);
        ConfigPreferences.putString(KEY_CHAT, className);
    }

    public static void saveProfile(String className) {
        ConfigPreferences.putBoolean(KEY_PROFILE_BOOL, true);
        ConfigPreferences.putString(KEY_PROFILE, className);
    }

    public static void reset(Context context) {
        ConfigPreferences.remove(KEY_CHAT);
        ConfigPreferences.remove(KEY_CHAT_BOOL);
        ConfigPreferences.remove(KEY_PROFILE);
        ConfigPreferences.remove(KEY_PROFILE_BOOL);
        FeatureInitializer.init(context);
    }
}