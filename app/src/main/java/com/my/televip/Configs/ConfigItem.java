package com.my.televip.Configs;

public class ConfigItem {

    public static final int HEADER = 0,
            SWITCH = 1,
            BUTTON = 2,
            DIVIDER = 3,
            INFO = 4;

    private final int type;
    private String key;
    private String text;
    private boolean value;
    private Runnable runnable;

    public ConfigItem(int type, String key, String text, boolean value, Runnable runnable) {
        this.type = type;
        this.key = key;
        this.text = text;
        this.value = value;
        this.runnable = runnable;
    }

    public ConfigItem(int type, String key, boolean value, Runnable runnable) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.runnable = runnable;
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
    public String getText() { return text; }

    public boolean isEnable() { return value; }
    public void setEnable(boolean value) {
        this.value = value;
        ConfigPreferences.putBoolean(key, value);
    }

    public Runnable getRunnable() { return runnable; }

    public void run() {
        if (runnable != null) runnable.run();
    }

}