package com.my.televip.language;

import com.my.televip.obfuscate.struct.TranslatorInfo;

import java.util.ArrayList;
import java.util.List;

public class English {

    private static final List<TranslatorInfo> en = new ArrayList<>();

    static {
        en.add(new TranslatorInfo(Keys.TO_THE_BEGINNING, "Go to First Message"));
        en.add(new TranslatorInfo(Keys.TO_THE_MESSAGE, "To The Message"));
        en.add(new TranslatorInfo(Keys.INPUT_MESSAGE_ID, "Input Message Id"));
        en.add(new TranslatorInfo(Keys.DONE, "Done"));
        en.add(new TranslatorInfo(Keys.NEW_NAME, "New Name"));
        en.add(new TranslatorInfo(Keys.CHANGE, "Change"));
        en.add(new TranslatorInfo(Keys.CANCEL, "Cancel"));
        en.add(new TranslatorInfo(Keys.CHANGE_TO, "Change to"));
        en.add(new TranslatorInfo(Keys.NAME_DELETED, "Name deleted"));
        en.add(new TranslatorInfo(Keys.GHOST_MODE, "Ghost Mode 👻"));
        en.add(new TranslatorInfo(Keys.BY_MUSTAFA, "by @m_1_iq"));
        en.add(new TranslatorInfo(Keys.HIDE_SEEN, "Hide 'Seen' status"));
        en.add(new TranslatorInfo(Keys.HIDE_STORY_VIEW, "Hide 'Story View' status"));
        en.add(new TranslatorInfo(Keys.HIDE_TYPING, "Hide Typing..."));
        en.add(new TranslatorInfo(Keys.TELEGRAM_PREMIUM, "Enable Local Premium"));
        en.add(new TranslatorInfo(Keys.UNLOCK_ALL_RESTRICTED, "Removes Content-Saving"));
        en.add(new TranslatorInfo(Keys.ALLOW_SAVING_VIDEOS, "Allow saving videos to the gallery"));
        en.add(new TranslatorInfo(Keys.GHOST_MODE_SETTINGS, "Ghost Mode"));
        en.add(new TranslatorInfo(Keys.SAVE, "Save"));
        en.add(new TranslatorInfo(Keys.DEVELOPER_CHANNEL, "Developer Channel"));
        en.add(new TranslatorInfo(Keys.HIDE_ONLINE, "Hide 'Online' status"));
        en.add(new TranslatorInfo(Keys.PREVENT_MEDIA, "Prevent Deletion of Secret Media."));
        en.add(new TranslatorInfo(Keys.HIDE_PHONE, "Hide 'Phone' number"));
        en.add(new TranslatorInfo(Keys.SHOW_DELETED_MESSAGES, "Show 'Deleted Messages'"));
        en.add(new TranslatorInfo(Keys.DELETED, "deleted"));
        en.add(new TranslatorInfo(Keys.COPIED, "Copied '"));
        en.add(new TranslatorInfo(Keys.TO_THE_CLIPBOARD, "' to the clipboard"));
        en.add(new TranslatorInfo(Keys.USER_OFFLINE, "You are currently offline"));
        en.add(new TranslatorInfo(Keys.DISABLE_STORIES, "Disable 'Stories'"));
        en.add(new TranslatorInfo(Keys.HIDE_UPDATE_APP, "Hide Update App"));
        en.add(new TranslatorInfo(Keys.JOIN_TELEVIP, "Join the TeleVip channel to be among the first to get the latest updates and new features"));
        en.add(new TranslatorInfo(Keys.JOIN, "Join"));
        en.add(new TranslatorInfo(Keys.DONT_SHOW_AGAIN, "Don't show again"));
        en.add(new TranslatorInfo(Keys.RESTART_APP, "Restart App"));
        en.add(new TranslatorInfo(Keys.RESTART_REQUIRED, "Restart required"));
        en.add(new TranslatorInfo(Keys.DISABLE_NUMBER_ROUNDING, "Disable Number Rounding"));
        en.add(new TranslatorInfo(Keys.EDITS_HISTORY, "Edits History"));
        en.add(new TranslatorInfo(Keys.SAVE_EDITS_HISTORY, "Save Messages Edits History"));
        en.add(new TranslatorInfo(Keys.STORIES_SETTINGS, "Stories"));
        en.add(new TranslatorInfo(Keys.MESSAGES_SETTINGS, "Messages"));
        en.add(new TranslatorInfo(Keys.MEDIA_SETTINGS, "Media"));
        en.add(new TranslatorInfo(Keys.OTHER_FEATURES_SETTINGS, "Other Features"));
        en.add(new TranslatorInfo(Keys.FIX_TL_ERROR, "Hide TL Error"));
        en.add(new TranslatorInfo(Keys.SHOW_MESSAGE_ID, "Show Message ID"));
        en.add(new TranslatorInfo(Keys.DOWNLOAD_SPEED, "Download Speed Boost"));
        en.add(new TranslatorInfo(Keys.CONNECTIONS_SETTINGS, "Connections"));
        en.add(new TranslatorInfo(Keys.MESSAGE, "Message "));
        en.add(new TranslatorInfo(Keys.EDITED, " Edited on "));
        en.add(new TranslatorInfo(Keys.MARK_READ_AFTER_SEND, "Mark messages as read after sending or tap a reaction"));
        en.add(new TranslatorInfo(Keys.OFFLINE_VISIBILITY_INFO, "When this option is enabled, you will not appear online unless you interact, such as sending messages, reacting to messages, or reading messages. You will return to being offline after about 30–60 seconds. It is recommended to enable all Ghost Mode options."));

    }


    public static String get(String name) {
        for (TranslatorInfo info : en)
            if (info.getOriginal().equals(name))
                return info.getResolved();

        return null;
    }

    public static boolean has(String name) {
        boolean has = false;
        for (TranslatorInfo info : en) {
            if (info.getOriginal().equals(name)) {
                has = true;
                break;
            }
        }
        return has;
    }

}
