package me.zoranz.rackitup.integration;

import me.zoranz.rackitup.recipe.DryingRecipe;
import me.zoranz.rackitup.recipe.MRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class RecipeUtil {

    public static List<DryingRecipe> getDryingRecipes(){
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        return manager.getAllRecipesFor(MRecipes.DRYING.get())
                .stream()
                .map(RecipeHolder::value)
                .toList();
    }

}
