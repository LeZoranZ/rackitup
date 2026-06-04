package me.zoranz.rackitup.block.entity;

import me.zoranz.rackitup.RackItUp;
import me.zoranz.rackitup.block.MBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, RackItUp.MODID
    );

    public static final Supplier<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK =
            BLOCK_ENTITIES.register("drying_rack", ()->BlockEntityType.Builder.of(
                            DryingRackBlockEntity::new,
                            MBlocks.DRYING_RACKS.values()
                                    .stream()
                                    .map(Supplier::get)
                                    .toArray(Block[]::new)

                    ).build(null)
            );

    public static void registerBlockEntities(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }

}
