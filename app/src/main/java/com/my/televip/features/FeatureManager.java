package com.my.televip.features;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.ClientChecker;
import com.my.televip.Clients.Telegraph;
import com.my.televip.Utils;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.virtuals.ui.Cells.ChatMessageCell;


public class FeatureManager {

    private static SharedPreferences sharedPreferences;

    // ===== Keys =====
    public static final String KEY_TELE_PREMIUM = "TelePremium";
    public static final String KEY_HIDE_SEEN = "HideSeen";
    public static final String KEY_HIDE_STORY_READ = "HideStoryRead";
    public static final String KEY_HIDE_TYPING = "HideTyping";
    public static final String KEY_HIDE_UPDATE_APP = "HideUpdateApp";
    public static final String KEY_UNLOCK_CHANNEL = "UnlockChannel";
    public static final String KEY_ALLOW_SAVE_GALLERY = "AllowSaveGallery";
    public static final String KEY_HIDE_ONLINE = "HideOnline";
    public static final String KEY_PREVENT_MEDIA = "PreventMedia";
    public static final String KEY_HIDE_PHONE = "HidePhone";
    public static final String KEY_SHOW_DELETED = "ShowDeletedMessages";
    public static final String KEY_SAVE_EDITS_HISTORY = "SaveEditsHistory";
    public static final String KEY_DISABLE_STORIES = "DisableStories";
    public static final String KEY_DISABLE_NUMBER_ROUNDING = "DisableNumberRounding";
    public static final String KEY_FIX_TL_ERROR = "FixTLError";
    public static final String KEY_SHOW_MESSAGE_ID = "ShowMessageID";
    public static final String KEY_DOWNLOAD_SPEED = "DownloadSpeed";
    public static final String KEY_MARK_READ_AFTER_SEND = "markReadAfterSend";

    public static boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (ClassCastException e) {

            sharedPreferences.edit().remove(key).apply();
            return false;
        }
    }

    public static void putBoolean(String key, boolean b) {
        try {
            sharedPreferences.edit().putBoolean(key, b).apply();
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public static String getString(String key) {
        try {
            return sharedPreferences.getString(key, null);
        } catch (ClassCastException e) {

            sharedPreferences.edit().remove(key).apply();
            return null;
        }
    }

    public static void putString(String key, String v) {
        try {
            sharedPreferences.edit().putString(key, v).apply();
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public static long getLong(String key) {
        try {
            return sharedPreferences.getLong(key, 0);
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
            return 0;
        }
    }

    public static void putLong(String key, long l) {
        try {
            sharedPreferences.edit().putLong(key, l).apply();
        } catch (ClassCastException e) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public static void remove(String key){
        try {
            sharedPreferences.edit().remove(key).apply();
        } catch (Throwable t){
            Utils.log(t);
        }
    }

    public static void init() {
        sharedPreferences = loadClass.getApplicationContext().getSharedPreferences(Language.strTelevip, Activity.MODE_PRIVATE);
    }

    public static void readFeature() {

        if (getBoolean(KEY_TELE_PREMIUM)) {
            TelePremium.init();
        }
        if (getBoolean(KEY_UNLOCK_CHANNEL)) {
            UnlockChannelFeature.init();
        }
        if (getBoolean(KEY_ALLOW_SAVE_GALLERY)) {
            AllowSaveToGallery.init();
        }
        if (getBoolean(KEY_PREVENT_MEDIA)) {
            PreventMedia.init();
        }
        if (getBoolean(KEY_SHOW_DELETED) || getBoolean(KEY_SHOW_MESSAGE_ID)) {
            ChatMessageCell.init();
        }
        if (getBoolean(KEY_SHOW_DELETED)) {
            ShowDeletedMessages.initProcessing();
        }
        if (getBoolean(KEY_DISABLE_STORIES)) {
            DisableStories.init();
        }
        if (getBoolean(KEY_HIDE_PHONE)) {
            HidePhone.init();
        }
        if (getBoolean(KEY_HIDE_UPDATE_APP)) {
            HideUpdateApp.init();
        }
        if (getBoolean(KEY_DISABLE_NUMBER_ROUNDING)) {
            DisableNumberRounding.init();
        }
        if (getBoolean(KEY_SAVE_EDITS_HISTORY)) {
            SaveEditsHistory.init();
        }
        if (getBoolean(KEY_FIX_TL_ERROR)) {
            FixTLError.init();
        }
        if (getBoolean(KEY_DOWNLOAD_SPEED)) {
            DownloadSpeed.init();
        }
        if (getBoolean(KEY_HIDE_ONLINE)) {
            HideOnline.init();
        }
        if (isGhostMode()){
            GhostMode.init();
        }

        if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
            OtherFeatures.init();
            copyName.init();
        } else {
            Telegraph.removeAd();
        }
    }

    public static void readFeature(String key) {

        if (key.equals(KEY_TELE_PREMIUM) && getBoolean(key) && !TelePremium.isEnable) {
            TelePremium.init();
        } else if (key.equals(KEY_UNLOCK_CHANNEL) && getBoolean(key) && !UnlockChannelFeature.isEnable) {
            UnlockChannelFeature.init();
        } else if (key.equals(KEY_ALLOW_SAVE_GALLERY) && getBoolean(key) && !AllowSaveToGallery.isEnable) {
            AllowSaveToGallery.init();
        } else if (key.equals(KEY_PREVENT_MEDIA) && getBoolean(key) && !PreventMedia.isEnable) {
            PreventMedia.init();
        } else if ((key.equals(KEY_SHOW_DELETED) && getBoolean(key) || key.equals(KEY_SHOW_MESSAGE_ID) && getBoolean(key))  && !ChatMessageCell.isEnable) {
            ChatMessageCell.init();
        } else if (key.equals(KEY_SHOW_DELETED) && getBoolean(key) && !ShowDeletedMessages.isEnable) {
            ShowDeletedMessages.initProcessing();
        } else if (key.equals(KEY_DISABLE_STORIES) && getBoolean(key) && !DisableStories.isEnable) {
            DisableStories.init();
        } else if (key.equals(KEY_HIDE_PHONE) && getBoolean(key) && !HidePhone.isEnable) {
            HidePhone.init();
        } else if (key.equals(KEY_HIDE_UPDATE_APP) && getBoolean(key) && !HideUpdateApp.isEnable) {
            HideUpdateApp.init();
        } else if (key.equals(KEY_DISABLE_NUMBER_ROUNDING) && getBoolean(key) && !DisableNumberRounding.isEnable) {
            DisableNumberRounding.init();
        } else if (key.equals(KEY_SAVE_EDITS_HISTORY) && getBoolean(key) && !SaveEditsHistory.isEnable) {
            SaveEditsHistory.init();
        } else if (key.equals(KEY_FIX_TL_ERROR) && getBoolean(key) && !FixTLError.isEnable) {
            FixTLError.init();
        } else if (key.equals(KEY_DOWNLOAD_SPEED) && getBoolean(key) && !DownloadSpeed.isEnable) {
            DownloadSpeed.init();
        } else if (key.equals(KEY_HIDE_ONLINE) && getBoolean(key) && !HideOnline.isEnable) {
            HideOnline.init();
        } else if (isGhostMode() && !GhostMode.isEnable) {
            GhostMode.init();
        }
    }

    public static boolean isGhostMode(){
        return getBoolean(KEY_HIDE_SEEN) ||
                getBoolean(KEY_HIDE_STORY_READ) ||
                getBoolean(KEY_HIDE_TYPING) ||
                getBoolean(KEY_HIDE_ONLINE) ||
                getBoolean(KEY_MARK_READ_AFTER_SEND);

    }

}
