package com.my.televip.features.otherFeatures;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.Toast;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.Configs.ConfigManager;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.SimpleTextView;
import com.my.televip.virtuals.Theme;
import com.my.televip.virtuals.ui.ProfileActivity;

public class EditOnlineTextView {
    public static boolean isEnable = false;

    public static void init(Context context) {
        try {
            if (!isEnable) {
                isEnable = true;
                HMethod.hookMethod(ClassLoad.getClass(ClassNames.PROFILE_ACTIVITY),
                        AutomationResolver.resolve("ProfileActivity", "updateProfileData", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("updateProfileData", new Class[]{boolean.class}),
                                new AbstractMethodHook() {
                                    @Override
                                    protected void afterMethod(MethodHookParam param) {
                                        final ProfileActivity profileActivity = new ProfileActivity(param.thisObject);

                                        Object[] onlineTextViewArray = profileActivity.getOnlineTextView();

                                        if (onlineTextViewArray != null && onlineTextViewArray.length > 1) {
                                            if (ConfigManager.hideOnline != null && ConfigManager.hideOnline.isEnable()) {
                                                SimpleTextView simpleTextView = new SimpleTextView(onlineTextViewArray[1]);

                                                if (simpleTextView.getSimpleTextView() != null) {
                                                    if ((profileActivity.getUserId() != 0 && profileActivity.getUserId() == profileActivity.getBaseFragment().getUserConfig().getClientUserId())) {
                                                        simpleTextView.setText(Translator.get(Keys.UserOffline));
                                                    }
                                                }
                                            }

                                            if (ConfigManager.showUserID != null && ConfigManager.showUserID.isEnable()) {
                                                SimpleTextView simpleTextView2 = new SimpleTextView(onlineTextViewArray[3]);
                                                if (simpleTextView2.getSimpleTextView() != null) {

                                                    CharSequence oldText = simpleTextView2.getText();

                                                    SpannableStringBuilder sb = new SpannableStringBuilder();
                                                    sb.append("\u200E");

                                                    sb.append("\u200F");
                                                    sb.append(oldText);
                                                    sb.append("\n");

                                                    int start = sb.length();
                                                    String id = String.valueOf(getID(profileActivity));
                                                    sb.append("ID: ").append(id);

                                                    sb.setSpan(new RelativeSizeSpan(1.0f), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                                    sb.setSpan(new ForegroundColorSpan(Theme.getTextColor()), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                                    simpleTextView2.setMaxLines(2);
                                                    simpleTextView2.setText(sb, true);
                                                    simpleTextView2.getSimpleTextView().setOnClickListener(v -> {
                                                        if (simpleTextView2.getText() != null) {
                                                            String name = Translator.get(Keys.Copied, id);
                                                            ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", id));
                                                            Toast.makeText(context, name, Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                })
                );
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }

    private static long getID(ProfileActivity profile) {
        if (profile.getUserId() > 1) {
            return profile.getUserId();
        } else {
            return profile.getChatId();
        }
    }

}
