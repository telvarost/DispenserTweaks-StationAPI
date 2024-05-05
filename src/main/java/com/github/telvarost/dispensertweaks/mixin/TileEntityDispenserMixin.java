package com.github.telvarost.dispensertweaks.mixin;

import com.github.telvarost.dispensertweaks.Config;
import com.github.telvarost.dispensertweaks.ModHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

@Mixin(DispenserBlockEntity.class)
abstract class TileEntityDispenserMixin extends BlockEntity implements Inventory {

    @Shadow private ItemStack[] inventory;

    @Shadow private Random random;

    public TileEntityDispenserMixin() {
    }


    @Inject(method = "getItemToDispense", at = @At("HEAD"), cancellable = true)
    private void dispense(CallbackInfoReturnable<ItemStack> cir) {
        if (  (!Config.ConfigFields.moddedDispenserFluidPlacement)
           && (!Config.ConfigFields.modernDispenserFluidPlacement)
           )
        {
            return;
        }

        ModHelper.ModHelperFields.emptySlotAvailable = -1;

        for(int var3 = 0; var3 < this.inventory.length; ++var3) {
            if (this.inventory[var3] == null) {
                ModHelper.ModHelperFields.emptySlotAvailable = var3;
            }
        }
    }
}
