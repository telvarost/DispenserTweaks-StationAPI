package com.github.telvarost.dispensertweaks;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class Config {

    @GConfig(value = "config", visibleName = "DispenserTweaks Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Enable Lava Block As Fuel Source")
        @Comment("Restart required for changes to take effect")
        public static Boolean enableLavaBlockSmeltingRecipe = true;

        @ConfigName("Modded Dispenser Fluid Placement")
        @Comment("Craft Fluids & Use Dispensers as Pumps")
        public static Boolean moddedDispenserFluidPlacement = false;

        @ConfigName("Modern Dispenser Fluid Placement")
        @Comment("Restart required for modded variant only")
        public static Boolean modernDispenserFluidPlacement = false;
    }
}
