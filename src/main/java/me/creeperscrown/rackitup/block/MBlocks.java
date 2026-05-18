package me.creeperscrown.rackitup.block;

import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.compat.RackWoodType;
import me.creeperscrown.rackitup.compat.RackWoodTypes;
import me.creeperscrown.rackitup.item.MItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RackItUp.MODID);
    public static final Map<RackWoodType, RegistryObject<Block>> DRYING_RACKS = new HashMap<>();

    public static final RegistryObject<Block> DRYING_RACK = registerBlock("drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> OAK_DRYING_RACK = registerBlock("oak_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> SPRUCE_DRYING_RACK = registerBlock("spruce_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> BIRCH_DRYING_RACK = registerBlock("birch_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> JUNGLE_DRYING_RACK = registerBlock("jungle_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> ACACIA_DRYING_RACK = registerBlock("acacia_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> DARK_OAK_DRYING_RACK = registerBlock("dark_oak_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> MANGROVE_DRYING_RACK = registerBlock("mangrove_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> CHERRY_DRYING_RACK = registerBlock("cherry_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> BAMBOO_DRYING_RACK = registerBlock("bamboo_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> CRIMSON_DRYING_RACK = registerBlock("crimson_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_PLANKS)
                    .strength(1.0f)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> WARPED_DRYING_RACK = registerBlock("warped_drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)
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

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> obj = BLOCKS.register(name, block);
        registerBlockItem(name, obj);
        return obj;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        MItems.ITEMS.register(name, ()->new BlockItem(block.get(), new Item.Properties()));
    }

    public static void registerBlocks(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
