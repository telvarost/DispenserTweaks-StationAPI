package com.github.telvarost.dispensertweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModHelper {
    public static void AttemptToSetStackSizeOfFluids()
    {
        if (ModHelper.ModHelperFields.blocksAndItemsRegistered)
        {
            Item waterItem = Block.FLOWING_WATER.asItem();
            Item lavaItem  = Block.FLOWING_LAVA.asItem();

            if (Config.config.moddedDispenserFluidPlacement) {
                if (null != waterItem) {
                    if (1 != waterItem.getMaxCount()) {
                        waterItem.setMaxCount(1);
                    }
                }

                if (null != lavaItem) {
                    if (1 != lavaItem.getMaxCount()) {
                        lavaItem.setMaxCount(1);
                    }
                }
            }
            else
            {
                if (null != waterItem) {
                    if (64 != waterItem.getMaxCount()) {
                        waterItem.setMaxCount(64);
                    }
                }

                if (null != lavaItem) {
                    if (64 != lavaItem.getMaxCount()) {
                        lavaItem.setMaxCount(64);
                    }
                }
            }
        }
    }

    public static class ModHelperFields {

        public static Boolean blocksAndItemsRegistered = false;

        public static Integer emptySlotAvailable = -1;
    }
}
