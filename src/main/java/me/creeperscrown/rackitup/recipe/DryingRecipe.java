package me.creeperscrown.rackitup.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class DryingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int time;

    public DryingRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int time) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.time = time;
    }

    public Ingredient getIngredient(){
        return ingredient;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess access) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MRecipes.DRYING_SERIALISER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MRecipes.DRYING.get();
    }

    public int getDryingTime() {
        return time;
    }

    public static class Serializer implements RecipeSerializer<DryingRecipe> {
        @Override
        public DryingRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            ItemStack result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
            int time = json.get("time").getAsInt();
            return new DryingRecipe(id, ingredient, result, time);
        }
        @Override
        public DryingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            int time = buf.readInt();
            return new DryingRecipe(id, ingredient, result, time);
        }
        @Override
        public void toNetwork(FriendlyByteBuf buf, DryingRecipe recipe) {
            recipe.ingredient.toNetwork(buf);
            buf.writeItem(recipe.result);
            buf.writeInt(recipe.time);
        }
    }

}
