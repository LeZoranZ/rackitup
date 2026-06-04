package me.zoranz.rackitup.compat;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.*;

public class RackWoodTypes {
    public static final List<RackWoodType> TYPES = new ArrayList<>();

    public static final RackWoodType DEFAULT =
            register("default", Blocks.OAK_PLANKS);
    public static final RackWoodType OAK =
            register("oak", Blocks.OAK_PLANKS);
    public static final RackWoodType SPRUCE =
            register("spruce", Blocks.SPRUCE_PLANKS);
    public static final RackWoodType BIRCH =
            register("birch", Blocks.BIRCH_PLANKS);
    public static final RackWoodType JUNGLE =
            register("jungle", Blocks.JUNGLE_PLANKS);
    public static final RackWoodType ACACIA =
            register("acacia", Blocks.ACACIA_PLANKS);
    public static final RackWoodType DARK_OAK =
            register("dark_oak", Blocks.DARK_OAK_PLANKS);
    public static final RackWoodType MANGROVE =
            register("mangrove", Blocks.MANGROVE_PLANKS);
    public static final RackWoodType CHERRY =
            register("cherry", Blocks.CHERRY_PLANKS);
    public static final RackWoodType BAMBOO =
            register("bamboo", Blocks.BAMBOO_PLANKS);
    public static final RackWoodType CRIMSON =
            register("crimson", Blocks.CRIMSON_PLANKS);
    public static final RackWoodType WARPED =
            register("warped", Blocks.WARPED_PLANKS);

    public static RackWoodType register(String name, Block planks) {
        RackWoodType type = new RackWoodType(name, ()->planks);
        TYPES.add(type);
        return type;
    }

}
