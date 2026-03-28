package com.my.televip.virtuals.ui;

import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActionBar.ActionBarMenuItem;

import de.robv.android.xposed.XposedHelpers;

public class ProfileActivity {

    Object profileActivity;

    public ProfileActivity(Object obj){
        profileActivity = obj;
    }

    public long getChatId(){
        return XposedHelpers.getLongField(profileActivity, AutomationResolver.resolve("ProfileActivity", "chatId", AutomationResolver.ResolverType.Field));
    }

    public long getUserId(){
        return XposedHelpers.getLongField(profileActivity, AutomationResolver.resolve("ProfileActivity", "userId", AutomationResolver.ResolverType.Field));
    }

    public Object[] getNameTextView(){
        return  (Object[]) XposedHelpers.getObjectField(profileActivity, AutomationResolver.resolve("ProfileActivity", "nameTextView", AutomationResolver.ResolverType.Field));
    }
    public Object[] getOnlineTextView(){
        return  (Object[]) XposedHelpers.getObjectField(profileActivity, AutomationResolver.resolve("ProfileActivity", "onlineTextView", AutomationResolver.ResolverType.Field));
    }

    public ActionBarMenuItem getOtherItem(){
        return new ActionBarMenuItem(XposedHelpers.getObjectField(profileActivity, AutomationResolver.resolve("ProfileActivity", "otherItem", AutomationResolver.ResolverType.Field)));
    }

    public BaseFragment getBaseFragment(){
        return new BaseFragment(profileActivity);
    }
}
