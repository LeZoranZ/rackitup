package me.creeperscrown.rackitup;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MTags {
    public static final TagKey<Item> VALID_CAMPFIRES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(RackItUp.MODID, "valid_campfires"));
    public static final TagKey<Block> VALID_BOOSTERS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(RackItUp.MODID, "valid_boosters"));
}
