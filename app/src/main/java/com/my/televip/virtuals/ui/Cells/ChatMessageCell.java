package com.my.televip.virtuals.ui.Cells;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.ShowDeletedMessages;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.OfficialChatMessageCell;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.messenger.MessageObject;
import com.my.televip.virtuals.tgnet.TLRPC;

import com.my.televip.reflect.XReflect;

public class ChatMessageCell {

    public static boolean isEnable = false;

    public static void init() {
        try {
            if (!isEnable) {
                isEnable = true;
                if (ClassLoad.getClass(ClassNames.CHAT_MESSAGE_CELL) != null) {
                    HMethod.hookMethod(ClassLoad.getClass(ClassNames.CHAT_MESSAGE_CELL), AutomationResolver.resolve("ChatMessageCell", "measureTime", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("measureTime", new Class[]{ClassLoad.getClass(ClassNames.MESSAGE_OBJECT)}), new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) {
                            boolean showDeleted = ConfigManager.showDeletedMessages != null && ConfigManager.showDeletedMessages.isEnable();
                            boolean showMessageId = ConfigManager.showMessageId != null && ConfigManager.showMessageId.isEnable();

                            if (showDeleted || showMessageId) {
                                try {
                                    MessageObject messageObject = new MessageObject(param.args[0]);

                                    if (messageObject.getMessageObject() == null) return;

                                    TLRPC.Message owner = messageObject.getMessageOwner();
                                    if (owner == null)
                                        return;

                                    if (showMessageId) {
                                        if (owner.getID() != 0) {
                                            String textId = "ID " + owner.getID();
                                            setSpannableStringBuilderText(textId, param.thisObject, false);
                                        }
                                    }

                                    int flags = owner.getFlags();

                                    if (((flags & ShowDeletedMessages.FLAG_DELETED) != 0)  && showDeleted) {
                                        setSpannableStringBuilderText(Translator.get(Keys.Deleted), param.thisObject, true);
                                    } else {
                                        TextPaint paint = Theme.getTextPaint();
                                        paint.setShadowLayer(0, 0, 0, Color.WHITE);
                                    }
                                } catch (Throwable throwable) {
                                    Logger.e(throwable);
                                }
                            }
                        }
                    }));
                }
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
        TextPaint paint = Theme.getTextPaint();
        if (paint != null) {
            int ceil = (int) Math.ceil(paint.measureText(spannableStringBuilder, 0, spannableStringBuilder.length()));
            cell.setTimeTextWidth(ceil + cell.getTimeTextWidth());
            cell.setTimeWidth(ceil + cell.getTimeWidth());
        }
    }

    Object chatMessageCell;

    public ChatMessageCell(Object cell){ chatMessageCell = cell; }

    public MessageObject getMessageObject() {
        return new MessageObject(XReflect.callMethod(chatMessageCell, AutomationResolver.resolve("ChatMessageCell", "getMessageObject", AutomationResolver.ResolverType.Method)));
    }

    public Object getChatMessageCell(){
        return chatMessageCell;
    }

}
