package me.zoranz.rackitup.recipe;

import me.zoranz.rackitup.RackItUp;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, RackItUp.MODID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, RackItUp.MODID);

    public static final Supplier<RecipeSerializer<DryingRecipe>> DRYING_SERIALISER =
            SERIALIZERS.register(
                    "drying", DryingSerialiser::new);

    public static final Supplier<RecipeType<DryingRecipe>> DRYING =
            TYPES.register(
                    "drying", registryName -> new RecipeType<DryingRecipe>() {
                        @Override
                        public String toString() {
                            return registryName.toString();
                        }
                    });

}
