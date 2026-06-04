package me.zoranz.rackitup.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class DryingSerialiser implements RecipeSerializer<DryingRecipe>{

    public static final MapCodec<DryingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(DryingRecipe::getIngredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(DryingRecipe::getResult),
                    Codec.INT.fieldOf("time").forGetter(DryingRecipe::getDryingTime)
            ).apply(instance, DryingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DryingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, DryingRecipe::getIngredient,
                    ItemStack.STREAM_CODEC, DryingRecipe::getResult,
                    ByteBufCodecs.INT, DryingRecipe::getDryingTime,
                    DryingRecipe::new
                );

    @Override
    public MapCodec<DryingRecipe> codec() {
            return CODEC;
        }
        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DryingRecipe> streamCodec() {
            return STREAM_CODEC;
        }


}
