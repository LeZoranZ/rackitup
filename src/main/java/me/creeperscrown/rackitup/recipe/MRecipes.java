package me.creeperscrown.rackitup.recipe;

import me.creeperscrown.rackitup.RackItUp;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RackItUp.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, RackItUp.MODID);

    public static final RegistryObject<RecipeSerializer<DryingRecipe>> DRYING_SERIALISER =
            SERIALIZERS.register("drying", DryingRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<DryingRecipe>> DRYING =
            TYPES.register("drying", ()->new RecipeType<>() {
                @Override
                public String toString() {
                    return RackItUp.MODID+":drying";
                }
            });

}
