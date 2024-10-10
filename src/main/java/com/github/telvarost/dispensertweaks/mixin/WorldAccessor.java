package com.github.telvarost.dispensertweaks.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(World.class)
public interface WorldAccessor {
    @Invoker("neighborUpdate")
    public void invokeUpdateBlock(int x, int t, int z, int l);
}
