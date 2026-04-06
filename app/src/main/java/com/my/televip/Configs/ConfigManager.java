package com.my.televip.Configs;

import android.content.Context;

import com.my.televip.ClientChecker;
import com.my.televip.Clients.Telegraph;
import com.my.televip.features.DisableChannelSwipeBack;
import com.my.televip.features.DisableNumberRounding;
import com.my.televip.features.DisableProfileSwipeBack;
import com.my.televip.features.DisableStories;
import com.my.televip.features.DownloadSpeed;
import com.my.televip.features.EnableSavingStories;
import com.my.televip.features.FixTLError;
import com.my.televip.features.GhostMode;
import com.my.televip.features.HideOnline;
import com.my.televip.features.HidePhone;
import com.my.televip.features.HidePinnedMessages;
import com.my.televip.features.HideProxySponsor;
import com.my.televip.features.HideUpdateApp;
import com.my.televip.features.OtherFeatures;
import com.my.televip.features.PreventMedia;
import com.my.televip.features.RemovesContentSaving;
import com.my.televip.features.SaveEditsHistory;
import com.my.televip.features.SecretMediaSave;
import com.my.televip.features.ShowDeletedMessages;
import com.my.televip.features.TelePremium;
import com.my.televip.features.copyName;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.ui.Cells.ChatMessageCell;

import java.util.ArrayList;
import java.util.List;


public class ConfigManager {
    

    public static List<ConfigItem> configs = new ArrayList<>();


    // GhostMode
    public static ConfigItem ghostModeSettings;
    public static ConfigItem hideSeen;
    public static ConfigItem markReadAfterSend;
    public static ConfigItem hideTyping;
    public static ConfigItem hideStoryView;
    public static ConfigItem hidePhone;
    public static ConfigItem hideOnline;
    public static ConfigItem onlineInfo;

    public static ConfigItem shadows;

    // Stories
    public static ConfigItem stories;
    public static ConfigItem disableStories;

    // Messages
    public static ConfigItem messages;
    public static ConfigItem showDeletedMessages;
    public static ConfigItem showMessageId;
    public static ConfigItem saveEditsHistory;

    // Connections
    public static ConfigItem connections;
    public static ConfigItem downloadSpeed;

    // Media
    public static ConfigItem media;
    public static ConfigItem secretMediaSave;
    public static ConfigItem preventMedia;
    public static ConfigItem enableSavingStories;

    //UI
    public static ConfigItem ui;
    public static ConfigItem hidePinnedMessages;
    public static ConfigItem disableChannelSwipeBack;
    public static ConfigItem disableProfileSwipeBack;
    public static ConfigItem hideProxySponsor;

    // Other Features
    public static ConfigItem otherFeatures;
    public static ConfigItem removesContentSaving;
    public static ConfigItem telegramPremium;
    public static ConfigItem disableNumberRounding;
    public static ConfigItem hideUpdateApp;
    public static ConfigItem fixTLError;

    // Button
    public static ConfigItem btnChannel;
    public static ConfigItem btnRestartApp;

    public static void loadAndRead(Context context){
        ConfigPreferences.init();
        load(context);
        readFeature(context);
    }

    public static void load(Context context) {

        if (configs != null && !configs.isEmpty()) configs.clear();

        // GhostMode
        ghostModeSettings = new ConfigItem(ConfigItem.HEADER, Keys.GhostModeSettings);
        configs.add(ghostModeSettings);

        hideSeen = new ConfigItem(ConfigItem.SWITCH, Keys.HideSeen, ConfigPreferences.getBoolean(Keys.HideSeen), GhostMode::init);
        configs.add(hideSeen);

        markReadAfterSend = new ConfigItem(ConfigItem.SWITCH, Keys.MarkReadAfterSend, ConfigPreferences.getBoolean(Keys.MarkReadAfterSend), GhostMode::init);
        configs.add(markReadAfterSend);

        hideTyping = new ConfigItem(ConfigItem.SWITCH, Keys.HideTyping, ConfigPreferences.getBoolean(Keys.HideTyping), GhostMode::init);
        configs.add(hideTyping);

        hideStoryView = new ConfigItem(ConfigItem.SWITCH, Keys.HideStoryView, ConfigPreferences.getBoolean(Keys.HideStoryView), GhostMode::init);
        configs.add(hideStoryView);

        hidePhone = new ConfigItem(ConfigItem.SWITCH, Keys.HidePhone, Translator.get(Keys.RestartRequired), ConfigPreferences.getBoolean(Keys.HidePhone), HidePhone::init);
        configs.add(hidePhone);

        hideOnline = new ConfigItem(ConfigItem.SWITCH, Keys.HideOnline, Translator.get(Keys.RestartRequired), ConfigPreferences.getBoolean(Keys.HideOnline), () -> { GhostMode.init(); HideOnline.init(); });
        configs.add(hideOnline);

        onlineInfo = new ConfigItem(ConfigItem.INFO, Keys.OfflineVisibilityInfo);
        configs.add(onlineInfo);

        shadows = new ConfigItem(ConfigItem.DIVIDER);
        configs.add(shadows);

        // Stories
        stories = new ConfigItem(ConfigItem.HEADER, Keys.StoriesSettings);
        configs.add(stories);

        disableStories = new ConfigItem(ConfigItem.SWITCH, Keys.DisableStories, Translator.get(Keys.RestartRequired), ConfigPreferences.getBoolean(Keys.DisableStories), DisableStories::init);
        configs.add(disableStories);

        configs.add(shadows);

        // Messages
        messages = new ConfigItem(ConfigItem.HEADER, Keys.MessagesSettings);
        configs.add(messages);

        showDeletedMessages = new ConfigItem(ConfigItem.SWITCH, Keys.ShowDeletedMessages, ConfigPreferences.getBoolean(Keys.ShowDeletedMessages), ShowDeletedMessages::initProcessing);
        configs.add(showDeletedMessages);

        if (!ClientChecker.check(ClientChecker.ClientType.NagramX)) {
            showMessageId = new ConfigItem(ConfigItem.SWITCH, Keys.ShowMessageID, ConfigPreferences.getBoolean(Keys.ShowMessageID), ChatMessageCell::init);
            configs.add(showMessageId);
        }

        if (!ClientChecker.check(ClientChecker.ClientType.Teegra)) {
            saveEditsHistory = new ConfigItem(ConfigItem.SWITCH, Keys.SaveEditsHistory, ConfigPreferences.getBoolean(Keys.SaveEditsHistory), () -> SaveEditsHistory.init(context));
            configs.add(saveEditsHistory);
        }

        configs.add(shadows);

        // Connections
        connections = new ConfigItem(ConfigItem.HEADER, Keys.ConnectionsSettings);
        configs.add(connections);

        downloadSpeed = new ConfigItem(ConfigItem.SWITCH, Keys.DownloadSpeed, ConfigPreferences.getBoolean(Keys.DownloadSpeed), DownloadSpeed::init);
        configs.add(downloadSpeed);

        configs.add(shadows);

        // Media
        media = new ConfigItem(ConfigItem.HEADER, Keys.MediaSettings);
        configs.add(media);

        secretMediaSave = new ConfigItem(ConfigItem.SWITCH, Keys.SecretMediaSave, ConfigPreferences.getBoolean(Keys.SecretMediaSave), SecretMediaSave::init);
        configs.add(secretMediaSave);

        preventMedia = new ConfigItem(ConfigItem.SWITCH, Keys.PreventMedia, ConfigPreferences.getBoolean(Keys.PreventMedia), PreventMedia::init);
        configs.add(preventMedia);

        enableSavingStories = new ConfigItem(ConfigItem.SWITCH, Keys.EnableSavingStories, ConfigPreferences.getBoolean(Keys.EnableSavingStories), EnableSavingStories::init);
        configs.add(enableSavingStories);

        configs.add(shadows);

        // UI
        ui = new ConfigItem(ConfigItem.HEADER, Keys.UiSettings);
        configs.add(ui);

        hidePinnedMessages = new ConfigItem(ConfigItem.SWITCH, Keys.HidePinnedMessages, ConfigPreferences.getBoolean(Keys.HidePinnedMessages), HidePinnedMessages::init);
        configs.add(hidePinnedMessages);

        disableChannelSwipeBack = new ConfigItem(ConfigItem.SWITCH, Keys.DisableChannelSwipeBack, ConfigPreferences.getBoolean(Keys.DisableChannelSwipeBack), DisableChannelSwipeBack::init);
        configs.add(disableChannelSwipeBack);

        disableProfileSwipeBack = new ConfigItem(ConfigItem.SWITCH, Keys.DisableProfileSwipeBack, ConfigPreferences.getBoolean(Keys.DisableProfileSwipeBack), DisableProfileSwipeBack::init);
        configs.add(disableProfileSwipeBack);

        hideProxySponsor = new ConfigItem(ConfigItem.SWITCH, Keys.HideProxySponsor, Translator.get(Keys.RestartRequired), ConfigPreferences.getBoolean(Keys.HideProxySponsor), HideProxySponsor::init);
        configs.add(hideProxySponsor);

        configs.add(shadows);

        // Other Features
        otherFeatures = new ConfigItem(ConfigItem.HEADER, Keys.OtherFeaturesSettings);
        configs.add(otherFeatures);

        removesContentSaving = new ConfigItem(ConfigItem.SWITCH, Keys.RemovesContentSaving, ConfigPreferences.getBoolean(Keys.RemovesContentSaving), RemovesContentSaving::init);
        configs.add(removesContentSaving);

        telegramPremium = new ConfigItem(ConfigItem.SWITCH, Keys.TelegramPremium, ConfigPreferences.getBoolean(Keys.TelegramPremium), TelePremium::init);
        configs.add(telegramPremium);

        if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
            disableNumberRounding = new ConfigItem(ConfigItem.SWITCH, Keys.DisableNumberRounding, "5.3K -> 5300", ConfigPreferences.getBoolean(Keys.DisableNumberRounding), DisableNumberRounding::init);
            hideUpdateApp = new ConfigItem(ConfigItem.SWITCH, Keys.HideUpdateApp, Translator.get(Keys.RestartRequired), ConfigPreferences.getBoolean(Keys.HideUpdateApp), HideUpdateApp::init);
            fixTLError = new ConfigItem(ConfigItem.SWITCH, Keys.FixTLError, ConfigPreferences.getBoolean(Keys.FixTLError), FixTLError::init);
            configs.add(disableNumberRounding);
            configs.add(hideUpdateApp);
            configs.add(fixTLError);
        }
        configs.add(shadows);

        btnChannel = new ConfigItem(ConfigItem.BUTTON, Keys.DeveloperChannel);
        configs.add(btnChannel);

        configs.add(shadows);

        btnRestartApp = new ConfigItem(ConfigItem.BUTTON, Keys.RestartApp);
        configs.add(btnRestartApp);

        configs.add(shadows);

    }

    public static List<ConfigItem> getItems(Context context) {
        ConfigManager.load(context);

        List<ConfigItem> items = new ArrayList<>();

        for (ConfigItem configItem : ConfigManager.configs) {
            items.add(new ConfigItem(configItem.getType(), configItem.getKey(), configItem.getText(), configItem.isEnable(), configItem.getRunnable()));
        }
        return items;
    }

    public static void readFeature(Context context) {
        try {
            for (ConfigItem item : configs) {
                if (item.isEnable()) item.run();
            }

            if (!ClientChecker.check(ClientChecker.ClientType.Telegraph)) {
                OtherFeatures.init(context);
                copyName.init(context);
            } else {
                Telegraph.removeAd();
            }

        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    public static boolean isGhostMode(){
        return hideSeen.isEnable() ||
                hideStoryView.isEnable() ||
                hideTyping.isEnable() ||
                hideOnline.isEnable() ||
                markReadAfterSend.isEnable();

    }

}
