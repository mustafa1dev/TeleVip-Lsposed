package com.my.televip.language;

import com.my.televip.obfuscate.struct.TranslatorInfo;

import java.util.ArrayList;
import java.util.List;

public class Arabic {

    private static final List<TranslatorInfo> ar = new ArrayList<>();

    static {
        ar.add(new TranslatorInfo(Keys.TO_THE_BEGINNING, "اذهب إلى أول رسالة"));
        ar.add(new TranslatorInfo(Keys.TO_THE_MESSAGE, "إلى الرسالة"));
        ar.add(new TranslatorInfo(Keys.INPUT_MESSAGE_ID, "ادخل معرف الرسالة"));
        ar.add(new TranslatorInfo(Keys.DONE, "حسناً"));
        ar.add(new TranslatorInfo(Keys.NEW_NAME, "الاسم الجديد"));
        ar.add(new TranslatorInfo(Keys.CHANGE, "تغيير"));
        ar.add(new TranslatorInfo(Keys.CANCEL, "الغاء"));
        ar.add(new TranslatorInfo(Keys.CHANGE_TO, "تم التغيير الى"));
        ar.add(new TranslatorInfo(Keys.NAME_DELETED, "تم حذف الاسم"));
        ar.add(new TranslatorInfo(Keys.GHOST_MODE, "وضع الشبح 👻"));
        ar.add(new TranslatorInfo(Keys.BY_MUSTAFA, "تم تطوير من قبل @m_1_iq"));
        ar.add(new TranslatorInfo(Keys.HIDE_SEEN, "اخفاء علامة الاستلام"));
        ar.add(new TranslatorInfo(Keys.HIDE_STORY_VIEW, "اخفاء مشاهدة قصة"));
        ar.add(new TranslatorInfo(Keys.HIDE_TYPING, "اخفاء مؤشر الكتابة"));
        ar.add(new TranslatorInfo(Keys.TELEGRAM_PREMIUM, "فتح تيليجرام المميز"));
        ar.add(new TranslatorInfo(Keys.UNLOCK_ALL_RESTRICTED, "إزالة قيود حفظ المحتوى"));
        ar.add(new TranslatorInfo(Keys.ALLOW_SAVING_VIDEOS, "السماح بحفظ الفيديو في المعرض"));
        ar.add(new TranslatorInfo(Keys.GHOST_MODE_SETTINGS, "وضع الشبح"));
        ar.add(new TranslatorInfo(Keys.SAVE, "حفظ"));
        ar.add(new TranslatorInfo(Keys.DEVELOPER_CHANNEL, "قناة المطور"));
        ar.add(new TranslatorInfo(Keys.HIDE_ONLINE, "إخفاء حالة الاتصال بالإنترنت"));
        ar.add(new TranslatorInfo(Keys.PREVENT_MEDIA, "تعطيل حذف الوسائط السرية"));
        ar.add(new TranslatorInfo(Keys.HIDE_PHONE, "اخفاء رقم هاتف"));
        ar.add(new TranslatorInfo(Keys.SHOW_DELETED_MESSAGES, "اضهار الرسائل المحذوفة"));
        ar.add(new TranslatorInfo(Keys.DELETED, "محذوفه"));
        ar.add(new TranslatorInfo(Keys.COPIED, "تم نسخ '"));
        ar.add(new TranslatorInfo(Keys.TO_THE_CLIPBOARD, "' إلى الحافظة"));
        ar.add(new TranslatorInfo(Keys.USER_OFFLINE, "لست متصلاً بالإنترنت"));
        ar.add(new TranslatorInfo(Keys.DISABLE_STORIES, "اخفاء القصص"));
        ar.add(new TranslatorInfo(Keys.HIDE_UPDATE_APP, "اخفاء تحديث تطبيق"));
        ar.add(new TranslatorInfo(Keys.JOIN_TELEVIP, "انضم إلى قناة TeleVip لتكون أول من يحصل على أحدث التحديثات والميزات الجديدة أولاً بأول"));
        ar.add(new TranslatorInfo(Keys.JOIN, "انضم"));
        ar.add(new TranslatorInfo(Keys.DONT_SHOW_AGAIN, "لا تظهر مرة اخرى"));
        ar.add(new TranslatorInfo(Keys.RESTART_APP, "إعادة تشغيل التطبيق"));
        ar.add(new TranslatorInfo(Keys.RESTART_REQUIRED, "إعادة التشغيل مطلوبة"));
        ar.add(new TranslatorInfo(Keys.DISABLE_NUMBER_ROUNDING, "تعطيل تقريب الأرقام"));
        ar.add(new TranslatorInfo(Keys.EDITS_HISTORY, "تاريخ التعديلات"));
        ar.add(new TranslatorInfo(Keys.SAVE_EDITS_HISTORY, "حفظ سجل تعديلات الرسالة"));
        ar.add(new TranslatorInfo(Keys.STORIES_SETTINGS, "القصص"));
        ar.add(new TranslatorInfo(Keys.MESSAGES_SETTINGS, "الرسائل"));
        ar.add(new TranslatorInfo(Keys.MEDIA_SETTINGS, "الوسائط"));
        ar.add(new TranslatorInfo(Keys.OTHER_FEATURES_SETTINGS, "مميزات اخرى"));
        ar.add(new TranslatorInfo(Keys.FIX_TL_ERROR, "إخفاء TL Error"));
        ar.add(new TranslatorInfo(Keys.SHOW_MESSAGE_ID, "عرض معرف الرسالة"));
        ar.add(new TranslatorInfo(Keys.DOWNLOAD_SPEED, "تنزيل فائق سرعة"));
        ar.add(new TranslatorInfo(Keys.CONNECTIONS_SETTINGS, "اتصالات"));
        ar.add(new TranslatorInfo(Keys.MESSAGE, "رسالة "));
        ar.add(new TranslatorInfo(Keys.EDITED, " تم التعديل في "));
        ar.add(new TranslatorInfo(Keys.MARK_READ_AFTER_SEND, "تعيين الرسائل كمقروءة بعد الإرسال أو عند النقر على تفاعل"));
    }


    public static String get(String name) {
        for (TranslatorInfo info : ar)
            if (info.getOriginal().equals(name))
                return info.getResolved();

        return null;
    }

    public static boolean has(String name) {
        boolean has = false;
        for (TranslatorInfo info : ar) {
            if (info.getOriginal().equals(name)) {
                has = true;
                break;
            }
        }
        return has;
    }
}