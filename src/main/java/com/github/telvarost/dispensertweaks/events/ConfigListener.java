package com.github.telvarost.dispensertweaks.events;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import com.github.telvarost.dispensertweaks.Config;
import com.github.telvarost.dispensertweaks.ModHelper;
import net.glasslauncher.mods.api.gcapi.api.PreConfigSavedListener;
import net.glasslauncher.mods.api.gcapi.impl.ConfigFactories;
import net.mine_diver.unsafeevents.listener.EventListener;

import java.lang.reflect.Field;

import static com.github.telvarost.dispensertweaks.Config.config;

@EventListener
public class ConfigListener implements PreConfigSavedListener {

    @Override
    public void onPreConfigSaved(int var1, JsonObject jsonObject, JsonObject jsonObject1) {
        /** - Update max stack size on config change */
        ModHelper.AttemptToSetStackSizeOfFluids();
    }
}
