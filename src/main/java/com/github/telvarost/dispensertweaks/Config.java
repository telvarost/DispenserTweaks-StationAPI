package com.github.telvarost.dispensertweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "DispenserTweaks")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigName("Dispense Fluid Sound Selection")
        @MultiplayerSynced
        @ValueOnVanillaServer(integerValue = 0)
        public static DispenserSoundEnum dispenserSoundEnum = DispenserSoundEnum.FLUID_SOUNDS;

        @ConfigName("Enable Lava Block As Fuel Source")
        @Comment("Restart required for changes to take effect")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableLavaBlockSmeltingRecipe = true;

        @ConfigName("Modded Dispenser Fluid Placement")
        @Comment("Craft Fluids & Use Dispensers as Pumps")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean moddedDispenserFluidPlacement = false;

        @ConfigName("Modern Dispenser Fluid Placement")
        @Comment("Restart required for modded variant only")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean modernDispenserFluidPlacement = false;
    }
}
