package com.my.televip.Configs;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ConfigMapper {

    public static List<ConfigItem> getItems(Context context) {
        ConfigsManager.load(context);

        List<ConfigItem> items = new ArrayList<>();

        for (ConfigItem configItem : ConfigsManager.configs) {
            items.add(new ConfigItem(configItem.getType(), configItem.getKey(), configItem.getText(), configItem.isEnable(), configItem.getRunnable()));
        }
        return items;
    }
}