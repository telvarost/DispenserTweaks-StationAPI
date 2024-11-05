package com.github.telvarost.dispensertweaks.events;

import com.github.telvarost.dispensertweaks.Config;
import com.github.telvarost.dispensertweaks.DispenserSoundEnum;
import com.github.telvarost.dispensertweaks.ModHelper;
import com.github.telvarost.dispensertweaks.mixin.WorldAccessor;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
            BlockPos facing = context.getFacingBlockPos();

            // Make buckets pickup liquids
            if (Config.config.modernDispenserFluidPlacement && context.itemStack.itemId == Item.BUCKET.id) {
                if (world.getMaterial(facing.x, facing.y, facing.z) == Material.WATER && world.getBlockMeta(facing.x, facing.y, facing.z) == 0) {
                    world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, 0, 0);
                    world.notifyNeighbors(facing.x, facing.y, facing.z, Block.WATER.id);
                    context.dispenser.setStack(context.slot, new ItemStack(Item.WATER_BUCKET));
                    if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                        world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                    }
                    event.cancel();
                }

                if (world.getMaterial(facing.x, facing.y, facing.z) == Material.LAVA && world.getBlockMeta(facing.x, facing.y, facing.z) == 0) {
                    world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, 0, 0);
                    world.notifyNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id);
                    context.dispenser.setStack(context.slot, new ItemStack(Item.LAVA_BUCKET));
                    if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                        world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                    }
                    event.cancel();
                }
            }

            // Make water buckets place water
            if (Config.config.modernDispenserFluidPlacement && context.itemStack.itemId == Item.WATER_BUCKET.id) {
                if (world.getMaterial(facing.x, facing.y, facing.z) == Material.AIR || !world.getMaterial(facing.x, facing.y, facing.z).isSolid()) {
                    if (world.getMaterial(facing.x, facing.y, facing.z) != Material.AIR) {
                        Block.BLOCKS[world.getBlockId(facing.x, facing.y, facing.z)].dropStacks(world, facing.x, facing.y, facing.z, world.getBlockMeta(facing.x, facing.y, facing.z));
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.WATER.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.WATER.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.WATER.id);
                        context.dispenser.setStack(context.slot, new ItemStack(Item.BUCKET));
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.playSound(context.dispenser.x, context.dispenser.y, context.dispenser.z, "liquid.splash", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    } else {
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.WATER.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.WATER.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.WATER.id);
                        context.dispenser.setStack(context.slot, new ItemStack(Item.BUCKET));
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.playSound(context.dispenser.x, context.dispenser.y, context.dispenser.z, "liquid.splash", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    }
                }
            }

            // Make lava buckets place lava
            if (Config.config.modernDispenserFluidPlacement && context.itemStack.itemId == Item.LAVA_BUCKET.id) {
                if (world.getMaterial(facing.x, facing.y, facing.z) == Material.AIR || !world.getMaterial(facing.x, facing.y, facing.z).isSolid()) {
                    if (world.getMaterial(facing.x, facing.y, facing.z) != Material.AIR) {
                        Block.BLOCKS[world.getBlockId(facing.x, facing.y, facing.z)].dropStacks(world, facing.x, facing.y, facing.z, world.getBlockMeta(facing.x, facing.y, facing.z));
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.LAVA.id);
                        context.dispenser.setStack(context.slot, new ItemStack(Item.BUCKET));
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.worldEvent(1002, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    } else {
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.LAVA.id);
                        context.dispenser.setStack(context.slot, new ItemStack(Item.BUCKET));
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.worldEvent(1002, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    }
                }
            }

            // Special Modded Place/Pick Up Water Tiles
            if (Config.config.moddedDispenserFluidPlacement && context.itemStack.itemId == Block.FLOWING_WATER.id) {
                if (world.getMaterial(facing.x, facing.y, facing.z) == Material.AIR || !world.getMaterial(facing.x, facing.y, facing.z).isSolid()) {
                    if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
                       && (0 == world.getBlockMeta(facing.x, facing.y, facing.z))
                       && (  (Block.FLOWING_WATER.id == world.getBlockId(facing.x, facing.y, facing.z))
                          || (Block.WATER.id == world.getBlockId(facing.x, facing.y, facing.z))
                          )
                    ) {
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, 0, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.WATER.id);
                        context.dispenser.setStack(context.slot, new ItemStack(Block.FLOWING_WATER));
                        context.dispenser.setStack(ModHelper.ModHelperFields.emptySlotAvailable, new ItemStack(Block.FLOWING_WATER));
                        if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    } else if (world.getMaterial(facing.x, facing.y, facing.z) != Material.AIR) {
                        Block.BLOCKS[world.getBlockId(facing.x, facing.y, facing.z)].dropStacks(world, facing.x, facing.y, facing.z, world.getBlockMeta(facing.x, facing.y, facing.z));
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.WATER.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.WATER.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.WATER.id);
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.playSound(context.dispenser.x, context.dispenser.y, context.dispenser.z, "liquid.splash", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    } else {
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.WATER.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.WATER.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.WATER.id);
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.playSound(context.dispenser.x, context.dispenser.y, context.dispenser.z, "liquid.splash", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    }
                } else {
                    context.dispenser.setStack(context.slot, new ItemStack(Block.FLOWING_WATER.asItem()));
                    world.playSound(context.dispenser.x, context.dispenser.y, context.dispenser.z, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                    for (int var28 = 0; var28 < 8; ++var28) {
                        world.addParticle("largesmoke", (double) context.dispenser.x + Math.random(), (double) context.dispenser.y + Math.random(), (double) context.dispenser.z + Math.random(), 0.0, 0.0, 0.0);
                    }
                    event.cancel();
                }
            }

            // Special Modded Place/Pick Up Lava Tiles
            if (Config.config.moddedDispenserFluidPlacement &&context.itemStack.itemId == Block.FLOWING_LAVA.id) {
                if (world.getMaterial(facing.x, facing.y, facing.z) == Material.AIR || !world.getMaterial(facing.x, facing.y, facing.z).isSolid()) {
                    if (  (-1 < ModHelper.ModHelperFields.emptySlotAvailable)
                       && (0 == world.getBlockMeta(facing.x, facing.y, facing.z))
                       && (  (Block.FLOWING_LAVA.id == world.getBlockId(facing.x, facing.y, facing.z))
                          || (Block.LAVA.id == world.getBlockId(facing.x, facing.y, facing.z))
                       )
                    ) {
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, 0, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id);
                        context.dispenser.setStack(context.slot, new ItemStack(Block.FLOWING_LAVA));
                        context.dispenser.setStack(ModHelper.ModHelperFields.emptySlotAvailable, new ItemStack(Block.FLOWING_LAVA));
                        if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    } else if (world.getMaterial(facing.x, facing.y, facing.z) != Material.AIR) {
                        Block.BLOCKS[world.getBlockId(facing.x, facing.y, facing.z)].dropStacks(world, facing.x, facing.y, facing.z, world.getBlockMeta(facing.x, facing.y, facing.z));
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.LAVA.id);
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.worldEvent(1002, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    } else {
                        world.setBlockWithoutNotifyingNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id, 0);
                        world.notifyNeighbors(facing.x, facing.y, facing.z, Block.LAVA.id);
                        ((WorldAccessor)world).invokeUpdateBlock(facing.x, facing.y, facing.z, Block.LAVA.id);
                        if (DispenserSoundEnum.FLUID_SOUNDS == Config.config.dispenserSoundEnum) {
                            world.worldEvent(1002, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        } else if (DispenserSoundEnum.NO_SOUND != Config.config.dispenserSoundEnum) {
                            world.worldEvent(1000, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                        }
                        event.cancel();
                    }
                } else {
                    context.dispenser.setStack(context.slot, new ItemStack(Block.FLOWING_WATER.asItem()));
                    world.playSound(context.dispenser.x, context.dispenser.y, context.dispenser.z, "random.fizz", 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                    for (int var28 = 0; var28 < 8; ++var28) {
                        world.addParticle("largesmoke", (double) context.dispenser.x + Math.random(), (double) context.dispenser.y + Math.random(), (double) context.dispenser.z + Math.random(), 0.0, 0.0, 0.0);
                    }
                    event.cancel();
                }
            }
        }
    }
}
