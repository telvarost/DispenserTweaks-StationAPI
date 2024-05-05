package com.github.telvarost.dispensertweaks.events;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.dispenser.DispenseEvent;
import net.modificationstation.stationapi.api.dispenser.ItemDispenseContext;

public class DispenserListener {
    @EventListener
    public static void changeDispenseBehavior(DispenseEvent event) {
        ItemDispenseContext context = event.context;
        World world = context.dispenser.world;

        if (context.itemStack != null) {
//            // Make arrows drop as an item instead of shoot as an entity
//            if (context.itemStack.itemId == Item.ARROW.id) {
//                context.skipSpecial = true;
//            }

            BlockPos facing = context.getFacingBlockPos();

            // Make buckets pickup liquids
            if (context.itemStack.itemId == Item.BUCKET.id) {
                if (world.method_1779(facing.x, facing.y, facing.z) == Material.WATER && world.getBlockMeta(facing.x, facing.y, facing.z) == 0) {
                    world.setBlock(facing.x, facing.y, facing.z, 0);
                    context.dispenser.setStack(context.slot, new ItemStack(Item.WATER_BUCKET));
                    event.cancel();
                }

                if (world.method_1779(facing.x, facing.y, facing.z) == Material.LAVA && world.getBlockMeta(facing.x, facing.y, facing.z) == 0) {
                    world.setBlock(facing.x, facing.y, facing.z, 0);
                    context.dispenser.setStack(context.slot, new ItemStack(Item.LAVA_BUCKET));
                    event.cancel();
                }
            }

            // Make water buckets place water
            if (context.itemStack.itemId == Item.WATER_BUCKET.id) {
                if (world.method_1779(facing.x, facing.y, facing.z) == Material.AIR) {
                    world.setBlockStateWithNotify(facing.x, facing.y, facing.z, Block.WATER.getDefaultState());
                    world.method_244(context.dispenser.x, context.dispenser.y, context.dispenser.z, Block.WATER.id);
                    context.dispenser.setStack(context.slot, new ItemStack(Item.BUCKET));
                    event.cancel();
                }
            }

            // Make lava buckets place lava
            if (context.itemStack.itemId == Item.LAVA_BUCKET.id) {
                if (world.method_1779(facing.x, facing.y, facing.z) == Material.AIR) {
                    world.setBlockStateWithNotify(facing.x, facing.y, facing.z, Block.LAVA.getDefaultState());
                    world.method_244(context.dispenser.x, context.dispenser.y, context.dispenser.z, Block.LAVA.id);
                    context.dispenser.setStack(context.slot, new ItemStack(Item.BUCKET));
                    event.cancel();
                }
            }

            // Special L
            // else if (Config.ConfigFields.moddedDispenserFluidPlacement && var12.itemId == BlockBase.FLOWING_WATER.id) {
            //                if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid()) {
            //                    if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
            //                       && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
            //                       && ( (BlockBase.FLOWING_WATER.id == blockIdInFrontOfDispenser)
            //                          || (BlockBase.STILL_WATER.id == blockIdInFrontOfDispenser)
            //                          )
            //                    ) {
            //                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
            //                        arg.playLevelEvent(1000, i, j, k, 0);
            //                        var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_WATER, 1));
            //                        var11.setInventoryItem(ModHelper.ModHelperFields.emptySlotAvailable, new ItemInstance(BlockBase.FLOWING_WATER, 1));
            //                    } else {
            //                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, FLOWING_WATER.id, 0);
            //                        arg.playSound(i, j, k, "liquid.splash", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            //                    }
            //                } else {
            //                    var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_WATER, 1));
            //                    arg.playSound(i, j, k, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            //                    for (int var28 = 0; var28 < 8; ++var28) {
            //                        arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
            //                    }
            //                }
            //            } else if (Config.ConfigFields.moddedDispenserFluidPlacement && var12.itemId == BlockBase.FLOWING_LAVA.id) {
            //                if (0 == blockIdInFrontOfDispenser || !arg.getMaterial(i + x_axis, j, k + z_axis).isSolid()) {
            //                    if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
            //                       && (0 == arg.getTileMeta(i + x_axis, j, k + z_axis))
            //                       && (  (BlockBase.FLOWING_LAVA.id == blockIdInFrontOfDispenser)
            //                          || (BlockBase.STILL_LAVA.id == blockIdInFrontOfDispenser)
            //                          )
            //                    ) {
            //                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, 0, 0);
            //                        arg.playLevelEvent(1000, i, j, k, 0);
            //                        var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
            //                        var11.setInventoryItem(ModHelper.ModHelperFields.emptySlotAvailable, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
            //                    } else {
            //                        arg.placeBlockWithMetaData(i + x_axis, j, k + z_axis, FLOWING_LAVA.id, 0);
            //                        arg.playLevelEvent(1002, i, j, k, 0);
            //                    }
            //                } else {
            //                    var11.setInventoryItem(ModHelper.ModHelperFields.lastSlotDispensed, new ItemInstance(BlockBase.FLOWING_LAVA, 1));
            //                    arg.playSound(i + 0.5, j + 0.5, k + 0.5, "random.fizz", 0.5F, 2.6F + (arg.rand.nextFloat() - arg.rand.nextFloat()) * 0.8F);
            //                    for (int var28 = 0; var28 < 8; ++var28) {
            //                        arg.addParticle("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
            //                    }
            //                }
            //            }
        }
    }
}
