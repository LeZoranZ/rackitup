package me.creeperscrown.rackitup;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MTags {
    public static final TagKey<Item> VALID_CAMPFIRES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(RackItUp.MODID, "valid_campfires"));
}
