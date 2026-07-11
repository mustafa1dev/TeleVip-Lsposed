package com.my.televip.Configs;

import java.util.List;

public class ConfigItem {

    public static final int HEADER = 0,
            SWITCH = 1,
            TEXT = 2,
            DIVIDER = 3,
            INFO = 4,
            EXPANDABLE_SWITCH = 5;

    private final int type;
    private String key;
    private String value;
    private boolean restartRequired;
    private boolean enable;
    private Runnable runnable;
    private List<ConfigItem> children;

    public ConfigItem(int type, String key, String value, boolean enable, Runnable runnable) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.enable = enable;
        this.runnable = runnable;
    }

    public ConfigItem(int type, String key, boolean restartRequired, boolean enable, Runnable runnable) {
        this.type = type;
        this.key = key;
        this.restartRequired = restartRequired;
        this.enable = enable;
        this.runnable = runnable;
    }

    public ConfigItem(int type, String key, boolean enable, Runnable runnable) {
        this.type = type;
        this.key = key;
        this.enable = enable;
        this.runnable = runnable;
    }

    public ConfigItem(int type, String key, List<ConfigItem> children) {
        this.type = type;
        this.key = key;
        this.children = children;
        boolean any = false;
        for (ConfigItem child : children) {
            if (child.isEnable()) { any = true; break; }
        }
        enable = any;
    }

    public ConfigItem(int type, String key) {
        this.type = type;
        this.key = key;
    }

    public ConfigItem(int type) {
        this.type = type;
    }

    public int getType() { return type; }
    public String getKey() { return key; }
    public String getValue() { return value; }

    public List<ConfigItem> getChildren() { return children; }

    public boolean isEnable() { return enable; }

    public boolean isRestartRequired() { return restartRequired; }

    public void setEnable(boolean value) {
        this.enable = value;
        ConfigPreferences.putBoolean(key, value);
    }

    public int getCustomCalendar() { return ConfigPreferences.getInt(key+"Int"); }

    public void setCustomCalendar(int value) {
        ConfigPreferences.putInt(key+"Int", value);
    }

    public void run() {
        if (runnable != null) runnable.run();
    }

}