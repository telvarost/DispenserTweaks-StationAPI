package com.github.telvarost.dispensertweaks;

public enum DispenserSoundEnum {
    NO_SOUND("No Sound"),
    DISPENSER_CLICKS("Dispenser Clicks"),
    FLUID_SOUNDS("Fluid Sounds");

    final String stringValue;

    DispenserSoundEnum() {
        this.stringValue = "Vanilla";
    }

    DispenserSoundEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}