package com.my.televip.features;


import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.hooks.HMethod;
import com.my.televip.language.Keys;
import com.my.televip.language.Translator;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.SimpleTextView;
import com.my.televip.virtuals.ui.ProfileActivity;

public class HideOnline {
    public static boolean isEnable = false;

    public static void init() {
        isEnable = true;

        try {
            if (loadClass.getProfileActivityClass() != null) {

                HMethod.hookMethod(loadClass.getProfileActivityClass(),
                        AutomationResolver.resolve("ProfileActivity", "updateProfileData", AutomationResolver.ResolverType.Method),
                        AutomationResolver.merge(AutomationResolver.resolveObject("updateProfileData", new Class[]{boolean.class}),
                                new AbstractMethodHook() {
                                    @Override
                                    protected void afterMethod(MethodHookParam param) {
                                        if (FeatureManager.isHideOnline()) {
                                            final ProfileActivity profileActivity = new ProfileActivity(param.thisObject);

                                                if (profileActivity.getUserId() != 0 && profileActivity.getUserId() == profileActivity.getBaseFragment().getUserConfig().getClientUserId()) {
                                                    Object[] onlineTextViewArray = profileActivity.getOnlineTextView();

                                                    if (onlineTextViewArray != null && onlineTextViewArray.length > 1) {

                                                        SimpleTextView simpleTextView = new SimpleTextView(onlineTextViewArray[1]);

                                                        if (simpleTextView.getSimpleTextView() != null) {
                                                            simpleTextView.setText(Translator.get(Keys.UserOffline));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                })
                );
            }
        } catch (Throwable t) {
            Utils.log(t);
        }
    }

}
