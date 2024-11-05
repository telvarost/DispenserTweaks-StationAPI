package com.github.telvarost.dispensertweaks.events.init;

import com.github.telvarost.dispensertweaks.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            if (Config.config.enableLavaBlockSmeltingRecipe) {
                /** - 1000 second fuel duration */
                FuelRegistry.addFuelItem(Block.FLOWING_LAVA.asItem(), 20000);
            }
        }

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
        {
            if (Config.config.moddedDispenserFluidPlacement)
            {
                CraftingRegistry.addShapelessRecipe(new ItemStack(Block.FLOWING_WATER, 1), Item.WATER_BUCKET);
                CraftingRegistry.addShapelessRecipe(new ItemStack(Block.FLOWING_LAVA, 1), Item.LAVA_BUCKET);
            }
        }
    }
}