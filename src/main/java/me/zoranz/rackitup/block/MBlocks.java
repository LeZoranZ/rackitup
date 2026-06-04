package me.zoranz.rackitup.block;

import me.zoranz.rackitup.RackItUp;
import me.zoranz.rackitup.compat.RackWoodType;
import me.zoranz.rackitup.compat.RackWoodTypes;
import me.zoranz.rackitup.item.MItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RackItUp.MODID);
    public static final Map<RackWoodType, DeferredBlock<Block>> DRYING_RACKS = new HashMap<>();

    public static final DeferredBlock<Block> DRYING_RACK = registerBlock("drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> OAK_DRYING_RACK = registerBlock("oak_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> SPRUCE_DRYING_RACK = registerBlock("spruce_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> BIRCH_DRYING_RACK = registerBlock("birch_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> JUNGLE_DRYING_RACK = registerBlock("jungle_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> ACACIA_DRYING_RACK = registerBlock("acacia_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> DARK_OAK_DRYING_RACK = registerBlock("dark_oak_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> MANGROVE_DRYING_RACK = registerBlock("mangrove_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> CHERRY_DRYING_RACK = registerBlock("cherry_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> BAMBOO_DRYING_RACK = registerBlock("bamboo_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> CRIMSON_DRYING_RACK = registerBlock("crimson_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> WARPED_DRYING_RACK = registerBlock("warped_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));

    static {
        DRYING_RACKS.put(RackWoodTypes.DEFAULT, DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.OAK, OAK_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.SPRUCE, SPRUCE_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.BIRCH, BIRCH_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.JUNGLE, JUNGLE_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.ACACIA, ACACIA_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.DARK_OAK, DARK_OAK_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.MANGROVE, MANGROVE_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.CHERRY, CHERRY_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.BAMBOO, BAMBOO_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.CRIMSON, CRIMSON_DRYING_RACK);
        DRYING_RACKS.put(RackWoodTypes.WARPED, WARPED_DRYING_RACK);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> b){
        DeferredBlock<T> obj = BLOCKS.register(name, b);
        registerBlockItem(name, obj);
        return obj;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> b){
        MItems.ITEMS.register(name, ()->new BlockItem(b.get(), new Item.Properties()));
    }

    public static void registerBlocks(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
