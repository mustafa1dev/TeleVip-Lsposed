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
    public static String HideSeenUser;
    public static String HideSeenGroups;
    public static String HideStoryView;
    public static String HideTyping;
    public static String TelegramPremium;
    public static String UnlockAllRestricted;
    public static String AllowSavingvideos;
    public static String GhostModeSettings;
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
    public static String strTelevip="TeleVip";

    public static void init(){
        LocaleController localeController = new LocaleController();
        init(localeController.getCurrentLocale().getLanguage());
    }

    public static void init(String language)
    {
        if (language.equals("ar")) {
            ToTheBeginning="اذهب إلى أول رسالة";
            ToTheMessage="إلى الرسالة";
            InputMessageId="ادخل معرف الرسالة";
            Done="حسناً";
            NewName="الاسم الجديد";
            Change="تغير";
            Cancel="الغاء";
            ChangeTo="تم تغير الى";
            NameDeleted="تم حذف الاسم";
            GhostMode="وضع الشبح 👻";
            byMustafa="تم تطوير من قبل @m_1_iq";
            HideSeenUser="اخفاء علامة الاستلام من المحادثة الخاصة";
            HideSeenGroups="اخفاء علامة الاستلام من المجموعات والقنوات";
            HideStoryView="اخفاء مشاهدة قصة";
            HideTyping="اخفاء مؤشر الكتاب";
            TelegramPremium="فتح تيليجرام المميز";
            UnlockAllRestricted="فتح جميع الخصائص المشفره والمغلقه";
            AllowSavingvideos="سماح حفظ الفيديو في معرض";
            GhostModeSettings="إعدادات وضع شبح";
            Save="حفظ";
            DeveloperChannel="قناة المطور";
            HideOnline="إخفاء حالة الاتصال بالإنترنت";
            PreventMedia ="تعطيل حذف الوسائط السرية";
            HidePhone="اخفاء رقم هاتف";
            ShowDeletedMessages="اضهار الرسائل المحذوفة";
            deleted="محذوفه";
            Copied = "تم نسخ '";
            ToTheClipboard = "' إلى الحافظة";
            UserOffline ="لست متصلاً بالإنترنت";
            DisableStories="اخفاء القصص";
            HideUpdateApp = "اخفاء تحديث تطبيق";
            JoinTeleVip = "انضم إلى قناة TeleVip لتكون أول من يحصل على أحدث التحديثات والميزات الجديدة أولاً بأول";
            Join = "انضم";
            DontShowAgain = "لا تظهر مرة اخرى";
            RestartApp = "إعادة تشغيل التطبيق";
        }else if (language.equals("zh")) {
            ToTheBeginning = "跳转到第一条消息";
            ToTheMessage = "跳转到消息";
            InputMessageId = "输入消息 ID";
            Done = "完成";
            NewName = "新名字";
            Change = "更改";
            Cancel = "取消";
            ChangeTo = "更改为";
            NameDeleted = "名称已删除";
            GhostMode = "幽灵模式 👻";
            byMustafa="by @m_1_iq";
            HideSeenUser = "隐藏私人聊天的已读状态";
            HideSeenGroups = "隐藏群组和频道的已读状态";
            HideStoryView = "隐藏 '故事观看' 状态";
            HideTyping = "隐藏正在输入...";
            TelegramPremium = "启用 Telegram 本地会员";
            UnlockAllRestricted = "解锁频道的所有受限和加密功能";
            AllowSavingvideos = "允许将视频保存到图库";
            GhostModeSettings = "幽灵模式设置";
            Save = "保存";
            DeveloperChannel = "开发者频道";
            HideOnline = "隐藏 '在线' 状态";
            PreventMedia = "防止删除秘密媒体";
            HidePhone = "隐藏 '电话' 号码";
            ShowDeletedMessages = "显示已删除的消息";
            deleted = "已删除";
            Copied = "已复制 '";
            ToTheClipboard = "' 到剪贴板";
            UserOffline="您当前处于离线状态";
            DisableStories = "禁用 故事";
            HideUpdateApp = "隐藏应用更新";
            JoinTeleVip = "加入 TeleVip 频道，第一时间获取最新更新和新功能";
            Join = "加入";
            DontShowAgain = "不再显示";
            RestartApp = "重新启动应用";
        }else {
            ToTheBeginning = "Go to First Message";
            ToTheMessage="To The Message";
            InputMessageId = "Input Message Id";
            Done = "Done";
            NewName = "New Name";
            Change = "Change";
            Cancel = "Cancel";
            ChangeTo="Change to";
            NameDeleted = "Name deleted";
            GhostMode="Ghost Mode 👻";
            byMustafa="by @m_1_iq";
            HideSeenUser = "Hide 'Seen' status for private chats";
            HideSeenGroups = "Hide 'Seen' status for groups and channels";
            HideStoryView = "Hide 'Story View' status";
            HideTyping = "Hide Typing...";
            TelegramPremium = "Enable Local Premium";
            UnlockAllRestricted = "Unlock all restricted and encrypted features for channels";
            AllowSavingvideos = "Allow saving videos to the gallery";
            GhostModeSettings = "Ghost Mode Settings";
            Save="Save";
            DeveloperChannel="Developer Channel";
            HideOnline="Hide 'Online' status";
            PreventMedia ="Prevent Deletion of Secret Media.";
            HidePhone ="Hide 'Phone' number";
            ShowDeletedMessages = "Show 'Deleted Messages'";
            deleted = "deleted";
            Copied = "Copied '";
            ToTheClipboard = "' to the clipboard";
            UserOffline = "You are currently offline";
            DisableStories ="Disable 'Stories'";
            HideUpdateApp = "Hide Update App";
            JoinTeleVip = "Join the TeleVip channel to be among the first to get the latest updates and new features";
            Join = "Join";
            DontShowAgain = "Don't show again";
            RestartApp = "Restart App";
        }
    }

}
