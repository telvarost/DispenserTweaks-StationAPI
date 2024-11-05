package com.github.telvarost.dispensertweaks;

import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.gcapi3.api.*;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;

import java.lang.reflect.*;
import java.util.function.*;

public class DispenserSoundEnumFactory implements ConfigFactoryProvider {

    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> immutableBuilder) {
        immutableBuilder.put(DispenserSoundEnum.class, ((id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) ->
        {
            int enumOrdinal;
            if(enumOrOrdinal instanceof Integer ordinal) {
                enumOrdinal = ordinal;
            }
            else {
                enumOrdinal = ((DispenserSoundEnum) enumOrOrdinal).ordinal();
            }
            return new EnumConfigEntryHandler<DispenserSoundEnum>(id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrdinal, ((DispenserSoundEnum) defaultEnum).ordinal(), DispenserSoundEnum.class);
        }));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> immutableBuilder) {
        immutableBuilder.put(DispenserSoundEnum.class, enumEntry -> enumEntry);
    }
}
