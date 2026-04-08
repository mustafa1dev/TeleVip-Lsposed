package com.my.televip.language;

import com.my.televip.Utils;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.messenger.LocaleController;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Translator {

    private static final Map<String, JSONObject> langMap = new HashMap<>();

    private static LocaleController localeController;

    public static void init() {
        try {
            loadAllLanguages();

            localeController = new LocaleController();
        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    private static void loadAllLanguages() {
        try (ZipFile zipFile = new ZipFile(Utils.modulePath)) {

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                String name = entry.getName();

                if (name.startsWith("assets/lang/") && name.endsWith(".json")) {

                    String langCode = name.substring(
                            name.lastIndexOf("/") + 1,
                            name.lastIndexOf(".")
                    );

                    InputStream is = zipFile.getInputStream(entry);
                    String json = readFully(is);
                    is.close();

                    langMap.put(langCode, new JSONObject(json));
                }
            }

        } catch (Throwable e) {
            Logger.e(e);
        }
    }

    private static String readFully(InputStream is) throws Exception {
        byte[] buffer = new byte[4096];
        int bytesRead;
        StringBuilder sb = new StringBuilder();

        while ((bytesRead = is.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
        }

        return sb.toString();
    }

    public static String get(String key) {
        try {
            if (localeController.getCurrentLocale() != null) {
                String lang = localeController.getCurrentLocale().getLanguage();
                JSONObject langJson = langMap.get(lang);

                if (langJson != null && langJson.has(key)) {
                    return langJson.optString(key);
                }
                JSONObject enJson = langMap.get("en");
                String text;
                if (enJson != null && enJson.has(key)) {
                    text = enJson.optString(key);
                } else {
                    text = key;
                }

                return text;
            }

        } catch (Throwable e) {
            Logger.e(e);
        }

        return key;
    }

    public static String get(String key, Object... args) {
        try {
            if (localeController.getCurrentLocale() != null) {
                String lang = localeController.getCurrentLocale().getLanguage();

                String text = null;

                JSONObject langJson = langMap.get(lang);

                if (langJson != null && langJson.has(key)) {
                    text = langJson.optString(key);
                }

                if (text == null) {
                    JSONObject enJson = langMap.get("en");
                    if (enJson != null && enJson.has(key)) {
                        text = enJson.optString(key);
                    } else {
                        text = key;
                    }
                }

                if (args != null && args.length > 0) {
                    return String.format(text, args);
                }

                return text;
            }
        } catch (Throwable e) {
            Logger.e(e);
        }

        return key;
    }

}
