package me.zoranz.rackitup.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class DryingRecipe implements Recipe<SingleRecipeInput> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int time;

    public DryingRecipe(Ingredient ingredient, ItemStack result, int time) {
        this.ingredient = ingredient;
        this.result = result;
        this.time = time;
    }

    public Ingredient getIngredient(){
        return ingredient;
    }
    public ItemStack getResult(){return result;}

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result.copy();
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

}