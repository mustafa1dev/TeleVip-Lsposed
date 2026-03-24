package com.my.televip.configs;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.my.televip.Utils;
import com.my.televip.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {
    public static File cfgPath = null;

    public static void read()
    {
        if (cfgPath == null)
            return;

        try
        {
            JsonElement valueJsonElement = JsonParser.parseReader(new BufferedReader(new FileReader(cfgPath)));
            if (!valueJsonElement.isJsonNull() && valueJsonElement instanceof JsonObject)
            {
                JsonObject jsonObject = (JsonObject) valueJsonElement;
                jsonObject.entrySet().forEach(entry -> {
                    JsonObject jsonModule = entry.getValue().getAsJsonObject();
         if (jsonModule.get("AntiRecall") != null)
                        Configs.setAntiRecall(jsonModule.get("AntiRecall").getAsBoolean());
                    //if (jsonModule.get("UnlockedNoPremiumAccountsLimit") != null)
                    //    Configs.setUnlockedNoPremiumAccountsLimit(jsonModule.get("UnlockedNoPremiumAccountsLimit").getAsBoolean());
                });
            }
        }
        catch (IOException e)
        {
            Utils.log(e);
        }
    }

    public static void save()
    {
        if (cfgPath == null)
            return;

        JsonObject valueJsonObject = new JsonObject();
        JsonObject jsonModule = new JsonObject();
        valueJsonObject.add("TeleVip", jsonModule);
        jsonModule.add("AntiRecall", new JsonPrimitive(Configs.isAntiRecall()));
             //jsonModule.add("UnlockedNoPremiumAccountsLimit", new JsonPrimitive(Configs.isUnlockedNoPremiumAccountsLimit()));
        FileUtils.save(cfgPath, new GsonBuilder().setPrettyPrinting().create().toJson(valueJsonObject), false);
    }
}
