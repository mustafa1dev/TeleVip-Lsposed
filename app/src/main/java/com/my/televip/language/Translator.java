package com.my.televip.language;

import com.my.televip.utils.Utils;
import com.my.televip.logging.Logger;
import com.my.televip.virtuals.messenger.LocaleController;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Translator {

    private static final String LANG_DIR = "lang/";
    /** Shipped packs; the index file may add more without a code change. */
    private static final String[] BUNDLED_LANGUAGES = {"en", "ar", "fa", "zh"};

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

    /**
     * Loads the bundled language packs.
     *
     * <p>They used to be read by opening the module APK at {@code Utils.modulePath}, which came from
     * the legacy {@code initZygote} callback. The modern libxposed API has no such callback, and
     * under Zygisk Next the module APK path is not always readable from inside the hooked app, so
     * the packs now live in {@code src/main/resources/lang/} and are read straight off our own class
     * loader. The old APK scan is kept as a fallback (and still auto-discovers files the index does
     * not list).</p>
     */
    private static void loadAllLanguages() {
        int loaded = loadFromClassLoader();
        if (loaded == 0) {
            loaded = loadFromModuleApk();
        }
        if (loaded == 0) {
            Logger.w("no language pack could be loaded, falling back to raw keys");
        }
    }

    private static int loadFromClassLoader() {
        int loaded = 0;
        try {
            ClassLoader loader = Translator.class.getClassLoader();
            if (loader == null) return 0;
            for (String code : listLanguages(loader)) {
                try (InputStream is = loader.getResourceAsStream(LANG_DIR + code + ".json")) {
                    if (is == null) continue;
                    langMap.put(code, new JSONObject(readFully(is)));
                    loaded++;
                } catch (Throwable e) {
                    Logger.e(e);
                }
            }
        } catch (Throwable e) {
            Logger.e(e);
        }
        return loaded;
    }

    private static Set<String> listLanguages(ClassLoader loader) {
        Set<String> codes = new LinkedHashSet<>();
        try (InputStream index = loader.getResourceAsStream(LANG_DIR + "languages.txt")) {
            if (index != null) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(index, Charset.forName("UTF-8")));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty() && !line.startsWith("#")) codes.add(line);
                }
            }
        } catch (Throwable ignored) {
        }
        // Never let a missing or short index drop the shipped packs.
        for (String fallback : BUNDLED_LANGUAGES) codes.add(fallback);
        return codes;
    }

    private static int loadFromModuleApk() {
        String modulePath = Utils.modulePath;
        if (modulePath == null || modulePath.isEmpty()) return 0;

        int loaded = 0;
        try (ZipFile zipFile = new ZipFile(modulePath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.endsWith(".json")) continue;
                if (!name.startsWith(LANG_DIR) && !name.startsWith("assets/lang/")) continue;

                String langCode = name.substring(name.lastIndexOf('/') + 1, name.lastIndexOf('.'));
                try (InputStream is = zipFile.getInputStream(entry)) {
                    langMap.put(langCode, new JSONObject(readFully(is)));
                    loaded++;
                }
            }
        } catch (Throwable e) {
            Logger.e(e);
        }
        return loaded;
    }

    private static String readFully(InputStream is) throws Exception {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toString("UTF-8");
    }

    public static String get(String key) {
        if (localeController == null) return key;
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
        if (localeController == null) return key;
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
