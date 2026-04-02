package com.my.televip.language;

import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.virtuals.messenger.LocaleController;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Translator {

    private static JSONObject arJson;
    private static JSONObject enJson;
    private static JSONObject zhJson;

    private static LocaleController localeController;

    public static void init() {
        try {
            arJson = new JSONObject(loadLangFromModule("lang/ar.json"));
            enJson = new JSONObject(loadLangFromModule("lang/en.json"));
            zhJson = new JSONObject(loadLangFromModule("lang/zh.json"));

            localeController = new LocaleController();
        } catch (Exception e) {
            Utils.log(e);
        }
    }

    private static String loadLangFromModule(String assetPath) {
        try (ZipFile zipFile = new ZipFile(MainHook.modulePath)) {

            ZipEntry entry = zipFile.getEntry("assets/" + assetPath);

            if (entry == null) return "{}";

            InputStream is = zipFile.getInputStream(entry);
            String json = readFully(is);
            is.close();
            return json;

        } catch (Exception e) {
            Utils.log(e);
        }
        return "{}";
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
            String lang = localeController.getCurrentLocale().getLanguage();
            String text;
            if (lang.equals("ar") && arJson != null && arJson.has(key)) {
                text = arJson.getString(key);
            } else if (lang.equals("zh") && zhJson != null && zhJson.has(key)) {
                text = zhJson.getString(key);
            } else {
               text = enJson != null && enJson.has(key) ? enJson.getString(key) : key;
            }
            return text;

        } catch (Exception e) {
            Utils.log(e);
        }

        return key;
    }

    public static String get(String key, Object... args) {
        try {
            String lang = localeController.getCurrentLocale().getLanguage();

            String text;

            if (lang.equals("ar") && arJson != null && arJson.has(key)) {
                text = arJson.getString(key);
            } else if (lang.equals("zh") && zhJson != null && zhJson.has(key)) {
                text = zhJson.getString(key);
            } else {
                text = (enJson != null && enJson.has(key)) ? enJson.getString(key) : key;
            }

            if (args != null && args.length > 0) {
                return String.format(text, args);
            }

            return text;

        } catch (Exception e) {
            Utils.log(e);
        }

        return key;
    }

}
