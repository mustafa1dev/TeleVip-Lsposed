package com.my.televip;

import static com.my.televip.obfuscate.AutomationResolver.resolverRegistry;

import android.content.Context;

import com.my.televip.Configs.ConfigManager;
import com.my.televip.application.AndroidUtilities;
import com.my.televip.application.ApplicationLoaderHook;
import com.my.televip.dex.DexInjector;
import com.my.televip.language.Translator;
import com.my.televip.settings.SettingsManager;
import com.my.televip.settings.controller.SettingsController;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.TeleVip.Bridge.Bridge;

public class TeleVip {
    
    public static void startHook(Context context) {
        try {
            resolverRegistry.loadParameter();
            Translator.init();
            AndroidUtilities.init(context);
            DexInjector.injectDex();

            SettingsController settingsController = new SettingsController(context);

            Bridge.init(context, settingsController);
            ConfigManager.loadAndRead(context);
            SettingsManager.init(settingsController);

            ApplicationLoaderHook.init();

        } catch (Throwable e){
            Logger.e(e);
        }

    }

}
