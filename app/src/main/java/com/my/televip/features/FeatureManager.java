package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;

import android.app.Activity;
import android.content.SharedPreferences;

import com.my.televip.ClientChecker;
import com.my.televip.Clients.Telegraph;
import com.my.televip.language.Language;
import com.my.televip.loadClass;


public class FeatureManager {

    private static SharedPreferences sharedPreferences;

    // ===== Keys =====
    public static final String KEY_TELE_PREMIUM = "TelePremium";
    public static final String KEY_HIDE_SEEN_PRIVATE = "HideSeenPrivate";
    public static final String KEY_HIDE_SEEN_GROUP = "HideSeenGroup";
    public static final String KEY_HIDE_STORY_READ = "HideStoryRead";
    public static final String KEY_HIDE_TYPING = "HideTyping";
    public static final String KEY_HIDE_UPDATE_APP = "HideUpdateApp";
    public static final String KEY_UNLOCK_CHANNEL = "UnlockChannel";
    public static final String KEY_ALLOW_SAVE_GALLERY = "AllowSaveGallery";
    public static final String KEY_HIDE_ONLINE = "HideOnline";
    public static final String KEY_PREVENT_MEDIA = "PreventMedia";
    public static final String KEY_HIDE_PHONE = "HidePhone";
    public static final String KEY_SHOW_DELETED = "ShowDeletedMessages";
    public static final String KEY_DISABLE_STORIES = "DisableStories";

    public static boolean getBoolean(String key) {
        try {
            return sharedPreferences.getBoolean(key, false);
        } catch (ClassCastException e) {
            // حذف القيمة القديمة الخاطئة
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

    public static void init() {
        sharedPreferences = loadClass.getApplicationContext().getSharedPreferences(Language.strTelevip, Activity.MODE_PRIVATE);

    }

    public static void readFeature() {

        if (getBoolean(KEY_TELE_PREMIUM)) {
            TelePremium.init();
        }
        if (getBoolean(KEY_HIDE_SEEN_PRIVATE) || getBoolean(KEY_HIDE_SEEN_GROUP)) {
            HideSeen.init();
        }
        if (getBoolean(KEY_HIDE_STORY_READ)) {
            HideStoryRead.init();
        }
        if (getBoolean(KEY_HIDE_TYPING)) {
            HideTyping.init();
        }
        if (getBoolean(KEY_UNLOCK_CHANNEL)) {
            UnlockChannelFeature.init();
        }
        if (getBoolean(KEY_ALLOW_SAVE_GALLERY)) {
            AllowSaveToGallery.init();
        }
        if (getBoolean(KEY_HIDE_ONLINE)) {
            HideOnline.init();
        }
        if (getBoolean(KEY_PREVENT_MEDIA)) {
            PreventMedia.init();
        }
        if (getBoolean(KEY_SHOW_DELETED)) {
            NEWAntiRecall.initProcessing();
            NEWAntiRecall.init(lpparam.classLoader);
            NEWAntiRecall.initAutoDownload(lpparam.classLoader);
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

        DownloadSpeed.init();
        //SaveEditMessage.init();

        NEWAntiRecall.initUI(lpparam.classLoader);
        if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
            OtherFeatures.init();
            copyName.init();
        } else {
            Telegraph.removeAd();
        }
    }

    public static void readFeature(String key) {

        if (key.equals(KEY_TELE_PREMIUM) && getBoolean(key)) {
            TelePremium.init();
        } else if (key.equals(KEY_HIDE_SEEN_PRIVATE) && getBoolean(key) || key.equals(KEY_HIDE_SEEN_GROUP) && getBoolean(key)) {
            HideSeen.init();
        } else if (key.equals(KEY_HIDE_STORY_READ) && getBoolean(key)) {
            HideStoryRead.init();
        } else if (key.equals(KEY_HIDE_TYPING) && getBoolean(key)) {
            HideTyping.init();
        } else if (key.equals(KEY_UNLOCK_CHANNEL) && getBoolean(key)) {
            UnlockChannelFeature.init();
        } else if (key.equals(KEY_ALLOW_SAVE_GALLERY) && getBoolean(key)) {
            AllowSaveToGallery.init();
        } else if (key.equals(KEY_HIDE_ONLINE) && getBoolean(key)) {
            HideOnline.init();
        } else if (key.equals(KEY_PREVENT_MEDIA) && getBoolean(key)) {
            PreventMedia.init();
        } else if (key.equals(KEY_SHOW_DELETED) && getBoolean(key)) {
            NEWAntiRecall.initProcessing();
            NEWAntiRecall.init(lpparam.classLoader);
            NEWAntiRecall.initAutoDownload(lpparam.classLoader);
        } else if (key.equals(KEY_DISABLE_STORIES) && getBoolean(key)) {
            DisableStories.init();
        } else if (key.equals(KEY_HIDE_PHONE) && getBoolean(key)) {
            HidePhone.init();
        } else if (key.equals(KEY_HIDE_UPDATE_APP) && getBoolean(key)) {
            HideUpdateApp.init();
        }
    }

}
