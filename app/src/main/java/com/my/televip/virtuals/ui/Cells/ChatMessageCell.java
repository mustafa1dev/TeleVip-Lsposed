package com.my.televip.virtuals.ui.Cells;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

import com.my.televip.Configs.ConfigsManager;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.ShowDeletedMessages;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.OfficialChatMessageCell;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.messenger.MessageObject;
import com.my.televip.virtuals.tgnet.TLRPC;

import de.robv.android.xposed.XposedHelpers;

public class ChatMessageCell {

    public static boolean isEnable = false;
    public static MessageObject currentMessageObject;
    public static long lastVisibleTime = -1;

    public static void init()
    {
        isEnable = true;
        try {
            if (loadClass.getChatMessageCellClass() != null) {
                XposedHelpers.findAndHookMethod(loadClass.getChatMessageCellClass(), AutomationResolver.resolve("ChatMessageCell", "measureTime", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("measureTime", new Class[]{loadClass.getMessageObjectClass()}),  new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        if (ConfigsManager.showDeletedMessages.isEnable() || ConfigsManager.showMessageId.isEnable()) {
                            try {
                                currentMessageObject = new MessageObject(XposedHelpers.getObjectField(param.thisObject, AutomationResolver.resolve("ChatMessageCell", "currentMessageObject", AutomationResolver.ResolverType.Field)));
                                lastVisibleTime = System.currentTimeMillis();
                                Object msgObj = param.args[0];
                                if (msgObj == null)
                                    return;
                                MessageObject messageObject = new MessageObject(msgObj);
                                TLRPC.Message owner = messageObject.getMessageOwner();
                                if (owner == null)
                                    return;
                                int flags = owner.getFlags();
                                if (ConfigsManager.showMessageId.isEnable()) {
                                    if (owner.getID() != 0) {
                                        String textId = "ID " + owner.getID();
                                        setSpannableStringBuilderText(textId, param.thisObject, false);
                                    }
                                }

                                if ((flags & ShowDeletedMessages.FLAG_DELETED) != 0 & ConfigsManager.showDeletedMessages.isEnable()) {
                                    setSpannableStringBuilderText(Translator.get(Keys.Deleted), param.thisObject, true);
                                } else {
                                    TextPaint paint = Theme.getTextPaint(Utils.classLoader);
                                    paint.setShadowLayer(0, 0, 0, Color.WHITE);
                                }
                            } catch (Throwable throwable) {
                                Logger.e(throwable);
                            }
                        }
                    }
                }));
            } else {
                Logger.w("Not found ChatMessageCell, " + Utils.issue);
            }
        } catch (Throwable t){
            Logger.e(t);
        }
    }


    public static SpannableStringBuilder convertToStringBuilder(CharSequence charSequence) {
        if (charSequence != null)
            return charSequence instanceof SpannableStringBuilder ? (SpannableStringBuilder) charSequence : new SpannableStringBuilder(charSequence);
        else
            return null;
    }

    private static void setSpannableStringBuilderText(String text, Object thisObject, boolean color){
        OfficialChatMessageCell cell = new OfficialChatMessageCell(thisObject);
        SpannableStringBuilder time = convertToStringBuilder(cell.getCurrentTimeString());
        if (time == null)
            return;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        if (color)
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.rgb(255, 0, 0)), 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(" ");
        time.insert(0, spannableStringBuilder);
        cell.setCurrentTimeString(time);
        TextPaint paint = Theme.getTextPaint(Utils.classLoader);
        if (paint != null) {
            int ceil = (int) Math.ceil(paint.measureText(spannableStringBuilder, 0, spannableStringBuilder.length()));
            cell.setTimeTextWidth(ceil + cell.getTimeTextWidth());
            cell.setTimeWidth(ceil + cell.getTimeWidth());
        }
    }

    Object chatMessageCell;

    public ChatMessageCell(Object cell){ chatMessageCell = cell; }

    public MessageObject getMessageObject() {
        return new MessageObject(XposedHelpers.callMethod(chatMessageCell, AutomationResolver.resolve("ChatMessageCell", "getMessageObject", AutomationResolver.ResolverType.Method)));
    }

    public Object getChatMessageCell(){
        return chatMessageCell;
    }

}
