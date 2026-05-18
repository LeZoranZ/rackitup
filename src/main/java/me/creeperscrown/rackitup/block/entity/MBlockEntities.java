package me.creeperscrown.rackitup.block.entity;

import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.block.MBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, RackItUp.MODID
    );

    public static final RegistryObject<BlockEntityType<DryingRackBlockEntity>> DRYING_RACK =
            BLOCK_ENTITIES.register("drying_rack", ()->BlockEntityType.Builder.of(
                            DryingRackBlockEntity::new,
                            MBlocks.DRYING_RACKS.values()
                                    .stream()
                                    .map(RegistryObject::get)
                                    .toArray(Block[]::new)

                    ).build(null)
            );
}
