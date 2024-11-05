package com.github.telvarost.dispensertweaks.events;

import com.github.telvarost.dispensertweaks.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;

public class ItemUsedInCraftingListener {

    /**
     * Allows for consuming the bucket when crafting source blocks
     *
     * @param event Item used in crafting event which fires whenever an item is consumed in crafting or an item is crafted
     */
    @EventListener
    public void dispenserTweaks_consumeBucket(ItemUsedInCraftingEvent event) {
        if (!Config.config.moddedDispenserFluidPlacement) {
            return;
        }

        if (  (null != event.itemCrafted)
           && (null != event.itemUsed)
           )
        {
            if (Block.FLOWING_WATER.id == event.itemCrafted.itemId)
            {
                event.craftingMatrix.setStack(event.itemOrdinal, null);
            }
            else if (Block.FLOWING_LAVA.id == event.itemCrafted.itemId)
            {
                event.craftingMatrix.setStack(event.itemOrdinal, null);
            }
        }
    }
}