package me.creeperscrown.rackitup.compat;

import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.block.DryingRackBlock;
import me.creeperscrown.rackitup.block.MBlocks;
import me.creeperscrown.rackitup.block.entity.MBlockEntities;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class RackItUpCompatModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> dryingRack;

    public RackItUpCompatModule(String modId, String shortId) {
        super(modId, shortId, EveryCompat.MOD_ID);

        dryingRack = SimpleEntrySet.builder(
                        WoodType.class,
                        "drying_rack",
                        MBlocks.OAK_DRYING_RACK,
                        () -> VanillaWoodTypes.OAK,
                        woodType -> new DryingRackBlock(
                                Utils.copyPropertySafe(woodType.planks)
                                        .strength(1.0F)
                                        .noOcclusion()
                        )
                )
                .addTile(MBlockEntities.DRYING_RACK)
                .defaultRecipe()
                .addTag(ResourceLocation.withDefaultNamespace("mineable/axe"), Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath(RackItUp.MODID, "drying_racks"), Registries.BLOCK)
                .setTabKey(RackItUp.RACK_IT_UP.getKey())
                .build();

        this.addEntry(dryingRack);
    }

}
