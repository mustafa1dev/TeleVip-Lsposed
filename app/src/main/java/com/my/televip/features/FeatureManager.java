package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;


import androidx.annotation.NonNull;

import com.my.televip.language.Language;
import com.my.televip.xSharedPreferences;

import java.util.ArrayList;

import de.robv.android.xposed.XSharedPreferences;

public class FeatureManager {
    public static boolean isTelePremium() {
        return xSharedPreferences.xSharedPre.contains("prem");
    }
    public static boolean isHideSeenPrivate() {
        return xSharedPreferences.xSharedPre.contains("noRead");
    }
    public static boolean isHideSeenGroup() {
        return xSharedPreferences.xSharedPre.contains("noRead2");
    }
    public static boolean isNoStoryRead() {
        return xSharedPreferences.xSharedPre.contains("noStoryRead");
    }
    public static boolean isHideTyping() {
        return xSharedPreferences.xSharedPre.contains("NoTyping");
    }
    public static boolean isUnlockChannelFeature() {
        return xSharedPreferences.xSharedPre.contains("usefolow");
    }
    public static boolean isAllowSaveToGallery() {
        return xSharedPreferences.xSharedPre.contains("allowShare");
    }
    public static boolean isHideOnline() {
        return xSharedPreferences.xSharedPre.contains("HideOnline");
    }
    public static boolean isPreventMedia() {
        return xSharedPreferences.xSharedPre.contains("PreventMedia");
    }
    public static boolean isHidePhone() {
        return xSharedPreferences.xSharedPre.contains("HidePhone");
    }
    public static boolean ishowDeletedMessages() {
        return xSharedPreferences.xSharedPre.contains("shmsdel");
    }
    public static boolean isDisableStories() {
        return xSharedPreferences.xSharedPre.contains("hidestore");
    }

    public static void readFeature(){
        xSharedPreferences.xSharedPre = new XSharedPreferences(lpparam.packageName, Language.strTelevip);
        if (FeatureManager.isTelePremium()) { TelePremium.init(); }
        if (FeatureManager.isHideSeenGroup() || isHideSeenPrivate()) { HideSeen.init(); }
        if (FeatureManager.isNoStoryRead()){ NoStoryRead.init(); }
        if (FeatureManager.isHideTyping()){ HideTyping.init(); }
        if (FeatureManager.isUnlockChannelFeature()){ UnlockChannelFeature.init(); }
        if (FeatureManager.isAllowSaveToGallery()){ AllowSaveToGallery.init(); }
        if (FeatureManager.isHideOnline()){ HideOnline.init(); }
        if (FeatureManager.isPreventMedia()){ PreventMedia.init(); }
        if (FeatureManager.ishowDeletedMessages()){
            NEWAntiRecall.initProcessing(lpparam.classLoader);
            NEWAntiRecall.init(lpparam.classLoader);
            NEWAntiRecall.initAutoDownload(lpparam.classLoader);
        }
        if (FeatureManager.isDisableStories()){ DisableStories.init(); }
        if (FeatureManager.isHidePhone()){ HidePhone.init(); }
        //SaveEditMessage.init();
    }

    public static @NonNull ArrayList<String> getArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Language.HideSeenUser);
        list.add(Language.HideSeenGroups);
        list.add(Language.HideStoryView);
        list.add(Language.HideOnline);
        list.add(Language.HidePhone);
        list.add(Language.DisableStories);
        list.add(Language.HideTyping);
        list.add(Language.ShowDeletedMessages);
        list.add(Language.PreventMedia);
        list.add(Language.UnlockAllRestricted);
        list.add(Language.AllowSavingvideos);
        list.add(Language.TelegramPremium);
        return list;
    }

}
