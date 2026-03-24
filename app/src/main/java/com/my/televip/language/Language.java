package com.my.televip.language;


import com.my.televip.virtuals.messenger.LocaleController;


public class Language {
    public static String ToTheBeginning;
    public static String ToTheMessage;
    public static String  InputMessageId;
    public static String Done;
    public static String NewName;
    public static String Change;
    public static String Cancel;
    public static String ChangeTo;
    public static String NameDeleted;
    public static String GhostMode;
    public static String byMustafa;
    public static String HideSeen;
    public static String MarkReadAfterSend;
    public static String HideStoryView;
    public static String HideTyping;
    public static String TelegramPremium;
    public static String UnlockAllRestricted;
    public static String AllowSavingVideos;

    public static String GhostModeSettings;
    public static String StoriesSettings;
    public static String MessagesSettings;
    public static String MediaSettings;
    public static String OtherFeaturesSettings;
    public static String Connections;

    public static String Save;
    public static String DeveloperChannel;
    public static String HideOnline;
    public static String PreventMedia;
    public static String HidePhone;
    public static String ShowDeletedMessages;
    public static String deleted;
    public static String Copied;
    public static String ToTheClipboard;
    public static String UserOffline;
    public static String DisableStories;
    public static String HideUpdateApp;
    public static String JoinTeleVip;
    public static String Join;
    public static String DontShowAgain;
    public static String RestartApp;
    public static String Restartrequired;
    public static String DisableNumberRounding;
    public static String EditsHistory;
    public static String SaveEditsHistory;
    public static String FixTLError;
    public static String ShowMessageID;
    public static String DownloadSpeed;
    public static String Message;
    public static String Edited;
    public static String strTelevip="TeleVip";

    public static void init(){
        LocaleController localeController = new LocaleController();
        init(localeController.getCurrentLocale().getLanguage());
    }

    public static void init(String language) {

        English.init();

        if (language.equals("ar")) {
            Arabic.init();
        } else if (language.equals("zh")) {
            Chinese.init();
        }
    }

}
