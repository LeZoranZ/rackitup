package me.creeperscrown.rackitup.integration.jei;

import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.recipe.MRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RackItUp.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new Drying(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var level = Minecraft.getInstance().level;
        if(level==null) return;
        var recipes = level.getRecipeManager().getAllRecipesFor(MRecipes.DRYING.get());
        registration.addRecipes(Drying.DRYING, recipes);
    }
}
