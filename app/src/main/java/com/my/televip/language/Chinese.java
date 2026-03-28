package com.my.televip.language;

import com.my.televip.obfuscate.struct.TranslatorInfo;

import java.util.ArrayList;
import java.util.List;

public class Chinese {

    private static final List<TranslatorInfo> zh = new ArrayList<>();

    static {
        zh.add(new TranslatorInfo(Keys.TO_THE_BEGINNING, "跳转到第一条消息"));
        zh.add(new TranslatorInfo(Keys.TO_THE_MESSAGE, "跳转到消息"));
        zh.add(new TranslatorInfo(Keys.INPUT_MESSAGE_ID, "输入消息 ID"));
        zh.add(new TranslatorInfo(Keys.DONE, "完成"));
        zh.add(new TranslatorInfo(Keys.NEW_NAME, "新名字"));
        zh.add(new TranslatorInfo(Keys.CHANGE, "更改"));
        zh.add(new TranslatorInfo(Keys.CANCEL, "取消"));
        zh.add(new TranslatorInfo(Keys.CHANGE_TO, "更改为"));
        zh.add(new TranslatorInfo(Keys.NAME_DELETED, "名称已删除"));
        zh.add(new TranslatorInfo(Keys.GHOST_MODE, "幽灵模式 👻"));
        zh.add(new TranslatorInfo(Keys.BY_MUSTAFA, "by @m_1_iq"));
        zh.add(new TranslatorInfo(Keys.HIDE_SEEN, "隐藏“已查看”状态"));
        zh.add(new TranslatorInfo(Keys.HIDE_STORY_VIEW, "隐藏 '故事观看' 状态"));
        zh.add(new TranslatorInfo(Keys.HIDE_TYPING, "隐藏正在输入..."));
        zh.add(new TranslatorInfo(Keys.TELEGRAM_PREMIUM, "启用 Telegram 本地会员"));
        zh.add(new TranslatorInfo(Keys.UNLOCK_ALL_RESTRICTED, "移除内容保存限制"));
        zh.add(new TranslatorInfo(Keys.ALLOW_SAVING_VIDEOS, "允许将视频保存到图库"));
        zh.add(new TranslatorInfo(Keys.GHOST_MODE_SETTINGS, "幽灵模式"));
        zh.add(new TranslatorInfo(Keys.SAVE, "保存"));
        zh.add(new TranslatorInfo(Keys.DEVELOPER_CHANNEL, "开发者频道"));
        zh.add(new TranslatorInfo(Keys.HIDE_ONLINE, "隐藏 '在线' 状态"));
        zh.add(new TranslatorInfo(Keys.PREVENT_MEDIA, "防止删除秘密媒体"));
        zh.add(new TranslatorInfo(Keys.HIDE_PHONE, "隐藏 '电话' 号码"));
        zh.add(new TranslatorInfo(Keys.SHOW_DELETED_MESSAGES, "显示已删除的消息"));
        zh.add(new TranslatorInfo(Keys.DELETED, "已删除"));
        zh.add(new TranslatorInfo(Keys.COPIED, "已复制 '"));
        zh.add(new TranslatorInfo(Keys.TO_THE_CLIPBOARD, "' 到剪贴板"));
        zh.add(new TranslatorInfo(Keys.USER_OFFLINE, "您当前处于离线状态"));
        zh.add(new TranslatorInfo(Keys.DISABLE_STORIES, "禁用 故事"));
        zh.add(new TranslatorInfo(Keys.HIDE_UPDATE_APP, "隐藏应用更新"));
        zh.add(new TranslatorInfo(Keys.JOIN_TELEVIP, "加入 TeleVip 频道，第一时间获取最新更新和新功能"));
        zh.add(new TranslatorInfo(Keys.JOIN, "加入"));
        zh.add(new TranslatorInfo(Keys.DONT_SHOW_AGAIN, "不再显示"));
        zh.add(new TranslatorInfo(Keys.RESTART_APP, "重新启动应用"));
        zh.add(new TranslatorInfo(Keys.RESTART_REQUIRED, "需要重新启动"));
        zh.add(new TranslatorInfo(Keys.DISABLE_NUMBER_ROUNDING, "禁用数字四舍五入"));
        zh.add(new TranslatorInfo(Keys.EDITS_HISTORY, "编辑历史"));
        zh.add(new TranslatorInfo(Keys.SAVE_EDITS_HISTORY, "保存消息编辑历史"));
        zh.add(new TranslatorInfo(Keys.STORIES_SETTINGS, "故事"));
        zh.add(new TranslatorInfo(Keys.MESSAGES_SETTINGS, "消息"));
        zh.add(new TranslatorInfo(Keys.MEDIA_SETTINGS, "媒体"));
        zh.add(new TranslatorInfo(Keys.OTHER_FEATURES_SETTINGS, "其他功能"));
        zh.add(new TranslatorInfo(Keys.FIX_TL_ERROR, "隐藏 TL Error"));
        zh.add(new TranslatorInfo(Keys.SHOW_MESSAGE_ID, "显示消息ID"));
        zh.add(new TranslatorInfo(Keys.DOWNLOAD_SPEED, "下载速度提升"));
        zh.add(new TranslatorInfo(Keys.CONNECTIONS_SETTINGS, "连接数"));
        zh.add(new TranslatorInfo(Keys.MESSAGE, "消息 "));
        zh.add(new TranslatorInfo(Keys.EDITED, " 编辑于 "));
        zh.add(new TranslatorInfo(Keys.MARK_READ_AFTER_SEND, "发送后将消息标记为已读"));

    }


    public static String get(String name) {
        for (TranslatorInfo info : zh)
            if (info.getOriginal().equals(name))
                return info.getResolved();

        return null;
    }

    public static boolean has(String name) {
        boolean has = false;
        for (TranslatorInfo info : zh) {
            if (info.getOriginal().equals(name)) {
                has = true;
                break;
            }
        }
        return has;
    }

}
