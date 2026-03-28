package com.my.televip.language;


import com.my.televip.virtuals.messenger.LocaleController;


public class Translator {

    public static String get(String name) {
        LocaleController localeController = new LocaleController();
        String lang = localeController.getCurrentLocale().getLanguage();

        if (lang.equals("ar") && Arabic.has(name)) {
            return Arabic.get(name);
        } else if (lang.equals("zh") && Chinese.has(name)) {
            return Chinese.get(name);
        } else if (English.has(name)) {
            return English.get(name);
        }

        return "null";
    }


}
