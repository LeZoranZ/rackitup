package me.creeperscrown.rackitup.integration.jei;

import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.block.MBlocks;
import me.creeperscrown.rackitup.recipe.DryingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class Drying implements IRecipeCategory<DryingRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(RackItUp.MODID, "drying");
    public static final RecipeType<DryingRecipe> DRYING = new RecipeType<>(ID, DryingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public Drying(IGuiHelper helper){
        this.background = helper.createBlankDrawable(100, 40);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, MBlocks.DRYING_RACK.get().asItem().getDefaultInstance());

        this.arrow = helper.createAnimatedRecipeArrow(200);
    }

    @Override
    public void draw(DryingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int time = recipe.getDryingTime();
        String text = convertTime(time);

        guiGraphics.drawString(Minecraft.getInstance().font, text, 37, 30, 0x7E7E7E, false);
        arrow.draw(guiGraphics, 37, 10);
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 40;
    }

    @Override
    public RecipeType<DryingRecipe> getRecipeType() {
        return DRYING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipeType.drying");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, DryingRecipe dryingRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 15, 10).addIngredients(dryingRecipe.getIngredient());
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 65, 10).addItemStack(dryingRecipe.getResultItem(null));
    }

    private String convertTime(int ticks){
        int totalSeconds = ticks/20;
        int hours = totalSeconds/3600;
        int minutes = (totalSeconds%3600)/60;
        int seconds = totalSeconds%60;

        StringBuilder sb = new StringBuilder();
        if(hours>0){
            sb.append(Component.translatable("jei.rackitup.drying_time.hours", hours).getString());
        }
        if(minutes>0){
            sb.append(Component.translatable("jei.rackitup.drying_time.minutes", minutes).getString());
        }
        if(seconds>0 || sb.length()==0){
            sb.append(Component.translatable("jei.rackitup.drying_time.seconds", seconds).getString());
        }

        return sb.toString().trim();
    }

}
