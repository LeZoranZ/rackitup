package me.creeperscrown.rackitup.block;

import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.item.MItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RackItUp.MODID);

    public static final RegistryObject<Block> DRYING_RACK = registerBlock("drying_rack",
            ()->new DryingRackBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOD)
                    .mapColor(MapColor.WOOD)
                    .strength(1.0f)
                    .noOcclusion()
            ));

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
