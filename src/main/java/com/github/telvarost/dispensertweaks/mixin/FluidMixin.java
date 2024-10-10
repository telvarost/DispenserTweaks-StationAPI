package com.github.telvarost.dispensertweaks.mixin;

import com.github.telvarost.dispensertweaks.Config;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LiquidBlock.class)
abstract class FluidMixin extends Block {

    public FluidMixin(int i, net.minecraft.block.material.Material arg) {
        super(i, (arg == Material.LAVA ? 14 : 12) * 16 + 13, arg);
        float var3 = 0.0F;
        float var4 = 0.0F;
        this.setBoundingBox(0.0F + var4, 0.0F + var3, 0.0F + var4, 1.0F + var4, 1.0F + var3, 1.0F + var4);
        this.setTickRandomly(true);
    }

    @Inject(method = "onPlaced", at = @At("TAIL"), cancellable = true)
    public void dispenserTweaks_onBlockPlaced(World arg, int i, int j, int k, CallbackInfo ci) {
        if (!Config.ConfigFields.moddedDispenserFluidPlacement) {
            return;
        }

        if (arg.isAir(i, j, k) || !arg.getMaterial(i, j, k).isSolid()) {
            int blockId = arg.getBlockId(i, j, k);
            if (arg.dimension.field_2176 && blockId == Block.FLOWING_WATER.id) {
                PlayerEntity player = PlayerHelper.getPlayerFromGame();
                if (null != player) {
                    float var4 = 1.0F;
                    double var7 = player.prevX + (player.x - player.prevX) * (double) var4;
                    double var9 = player.prevY + (player.y - player.prevY) * (double) var4 + 1.62 - (double) player.standingEyeHeight;
                    double var11 = player.prevZ + (player.z - player.prevZ) * (double) var4;
                    arg.playSound(var7 + 0.5, var9 + 0.5, var11 + 0.5, "random.fizz", 0.5F, 2.6F + (arg.random.nextFloat() - arg.random.nextFloat()) * 0.8F);
                }

                for (int var28 = 0; var28 < 8; ++var28) {
                    arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
                }
                arg.setBlock(i, j, k, 0, 0);
            }
        }
    }
}
