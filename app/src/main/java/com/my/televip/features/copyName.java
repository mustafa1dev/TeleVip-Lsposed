package com.my.televip.features;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Logger;
import com.my.televip.virtuals.ActionBar.SimpleTextView;
import com.my.televip.virtuals.ui.ProfileActivity;

public class copyName {

    public static void init(Context context) {
        try {
            if (loadClass.getProfileActivityClass() != null) {

                HMethod.hookMethod(loadClass.getProfileActivityClass(), AutomationResolver.resolve("ProfileActivity", "createView", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("createView", new Class[]{loadClass.getContextClass()}), new AbstractMethodHook() {
                    @Override
                    protected void afterMethod(MethodHookParam param) {
                        final ProfileActivity profileActivity = new ProfileActivity(param.thisObject);

                        Object[] nameTextViewArray = profileActivity.getNameTextView();

                        if (nameTextViewArray != null && nameTextViewArray.length > 1) {

                            SimpleTextView simpleTextView = new SimpleTextView(nameTextViewArray[1]);

                            if (simpleTextView.getSimpleTextView() != null) {
                                simpleTextView.getSimpleTextView().setOnClickListener(v -> {
                                    if (simpleTextView.getText() != null) {
                                        String name = Translator.get(Keys.Copied, simpleTextView.getText());
                                        ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", simpleTextView.getText()));
                                        Toast.makeText(context, name, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    }
                }));
            }
        } catch (Throwable t) {
            Logger.e(t);
        }
    }
}
