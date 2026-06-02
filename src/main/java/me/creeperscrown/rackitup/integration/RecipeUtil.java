package me.creeperscrown.rackitup.integration;

import me.creeperscrown.rackitup.recipe.DryingRecipe;
import me.creeperscrown.rackitup.recipe.MRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class RecipeUtil {

    public static List<DryingRecipe> getDryingRecipes(){
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        return manager.getAllRecipesFor(MRecipes.DRYING.get());
    }

}
