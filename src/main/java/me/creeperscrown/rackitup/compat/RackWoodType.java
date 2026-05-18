package me.creeperscrown.rackitup.compat;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class RackWoodType{
    public final String name;
    public final Supplier<Block> planks;

    public RackWoodType(String name, Supplier<Block> planks){
        this.name = name;
        this.planks = planks;
    }

}
