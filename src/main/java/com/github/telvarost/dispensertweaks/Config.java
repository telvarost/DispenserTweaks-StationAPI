package com.github.telvarost.dispensertweaks;

import net.glasslauncher.mods.gcapi3.api.*;

public class Config {

    @ConfigRoot(value = "config", visibleName = "DispenserTweaks")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigEntry(
                name = "Dispense Fluid Sound Selection",
                multiplayerSynced = true
        )
        public DispenserSoundEnum dispenserSoundEnum = DispenserSoundEnum.FLUID_SOUNDS;

        @ConfigEntry(
                name = "Enable Lava Block As Fuel Source",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        public Boolean enableLavaBlockSmeltingRecipe = true;

        @ConfigEntry(
                name = "Modded Dispenser Fluid Placement",
                description = "Craft Fluids & Use Dispensers as Pumps",
                multiplayerSynced = true
        )
        public Boolean moddedDispenserFluidPlacement = false;

        @ConfigEntry(
                name = "Modern Dispenser Fluid Placement",
                description = "Restart required for modded variant only",
                multiplayerSynced = true
        )
        public Boolean modernDispenserFluidPlacement = false;
    }
}
