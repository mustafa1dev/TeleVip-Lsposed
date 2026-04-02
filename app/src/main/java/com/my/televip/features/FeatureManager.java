package com.my.televip.features;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.ClientChecker;
import com.my.televip.Clients.Telegraph;
import com.my.televip.Utils;
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
    public static final String KEY_REMOVES_CONTENT_SAVING = "RemovesContentSaving";
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
    public static final String KEY_HIDE_PINNED_MESSAGES = "HidePinnedMessages";
    public static final String KEY_DISABLE_CHANNEL_SWIPE_BACK = "DisableChannelSwipeBack";
    public static final String KEY_DISABLE_PROFILE_SWIPE_BACK = "DisableProfileSwipeBack";

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
        sharedPreferences = loadClass.getApplicationContext().getSharedPreferences("TeleVip", Activity.MODE_PRIVATE);
    }

    public static void readFeature() {

        if (isTelePremium()) {
            TelePremium.init();
        }
        if (isRemovesContentSaving()) {
            RemovesContentSaving.init();
        }
        if (isAllowSaveGallery()) {
            AllowSaveToGallery.init();
        }
        if (isPreventMedia()) {
            PreventMedia.init();
        }
        if (isShowDeleted() || isShowMessageID()) {
            ChatMessageCell.init();
        }
        if (isShowDeleted()) {
            ShowDeletedMessages.initProcessing();
        }
        if (isDisableStories()) {
            DisableStories.init();
        }
        if (isHidePhone()) {
            HidePhone.init();
        }
        if (isHideUpdateApp()) {
            HideUpdateApp.init();
        }
        if (isDisableNumberRounding()) {
            DisableNumberRounding.init();
        }
        if (isSaveEditsHistory()) {
            SaveEditsHistory.init();
        }
        if (isFixTLError()) {
            FixTLError.init();
        }
        if (isDownloadSpeed()) {
            DownloadSpeed.init();
        }
        if (isHideOnline()) {
            HideOnline.init();
        }
        if (isGhostMode()) {
            GhostMode.init();
        }
        if (isHidePinnedMessages()) {
            HidePinnedMessages.init();
        }
        if (isDisableChannelSwipeBack()) {
            DisableChannelSwipeBack.init();
        }
        if (isDisableProfileSwipeBack()) {
            DisableProfileSwipeBack.init();
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
        } else if (key.equals(KEY_REMOVES_CONTENT_SAVING) && getBoolean(key) && !RemovesContentSaving.isEnable) {
            RemovesContentSaving.init();
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
        } else if (key.equals(KEY_HIDE_PINNED_MESSAGES) && getBoolean(key) && !HidePinnedMessages.isEnable) {
            HidePinnedMessages.init();
        } else if (key.equals(KEY_DISABLE_CHANNEL_SWIPE_BACK) && getBoolean(key) && !DisableChannelSwipeBack.isEnable) {
            DisableChannelSwipeBack.init();
        } else if (key.equals(KEY_DISABLE_PROFILE_SWIPE_BACK) && getBoolean(key) && !DisableProfileSwipeBack.isEnable) {
            DisableProfileSwipeBack.init();
        }
    }

    public static boolean isTelePremium() {
        return getBoolean(KEY_TELE_PREMIUM);
    }

    public static boolean isHideSeen() {
        return getBoolean(KEY_HIDE_SEEN);
    }

    public static boolean isHideStoryRead() {
        return getBoolean(KEY_HIDE_STORY_READ);
    }

    public static boolean isHideTyping() {
        return getBoolean(KEY_HIDE_TYPING);
    }

    public static boolean isHideUpdateApp() {
        return getBoolean(KEY_HIDE_UPDATE_APP);
    }

    public static boolean isRemovesContentSaving() {
        return getBoolean(KEY_REMOVES_CONTENT_SAVING);
    }

    public static boolean isAllowSaveGallery() {
        return getBoolean(KEY_ALLOW_SAVE_GALLERY);
    }

    public static boolean isHideOnline() {
        return getBoolean(KEY_HIDE_ONLINE);
    }

    public static boolean isPreventMedia() {
        return getBoolean(KEY_PREVENT_MEDIA);
    }

    public static boolean isHidePhone() {
        return getBoolean(KEY_HIDE_PHONE);
    }

    public static boolean isShowDeleted() {
        return getBoolean(KEY_SHOW_DELETED);
    }

    public static boolean isSaveEditsHistory() {
        return getBoolean(KEY_SAVE_EDITS_HISTORY);
    }

    public static boolean isDisableStories() {
        return getBoolean(KEY_DISABLE_STORIES);
    }

    public static boolean isDisableNumberRounding() {
        return getBoolean(KEY_DISABLE_NUMBER_ROUNDING);
    }

    public static boolean isFixTLError() {
        return getBoolean(KEY_FIX_TL_ERROR);
    }

    public static boolean isShowMessageID() {
        return getBoolean(KEY_SHOW_MESSAGE_ID);
    }

    public static boolean isDownloadSpeed() {
        return getBoolean(KEY_DOWNLOAD_SPEED);
    }

    public static boolean isMarkReadAfterSend() {
        return getBoolean(KEY_MARK_READ_AFTER_SEND);
    }

    public static boolean isHidePinnedMessages() {
        return getBoolean(KEY_HIDE_PINNED_MESSAGES);
    }

    public static boolean isDisableChannelSwipeBack() {
        return getBoolean(KEY_DISABLE_CHANNEL_SWIPE_BACK);
    }

    public static boolean isDisableProfileSwipeBack() {
        return getBoolean(KEY_DISABLE_PROFILE_SWIPE_BACK);
    }

    public static boolean isGhostMode(){
        return getBoolean(KEY_HIDE_SEEN) ||
                getBoolean(KEY_HIDE_STORY_READ) ||
                getBoolean(KEY_HIDE_TYPING) ||
                getBoolean(KEY_HIDE_ONLINE) ||
                getBoolean(KEY_MARK_READ_AFTER_SEND);

    }

}
