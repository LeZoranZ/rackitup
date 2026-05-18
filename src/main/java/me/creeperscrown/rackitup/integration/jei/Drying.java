package me.creeperscrown.rackitup.integration.jei;

import me.creeperscrown.rackitup.Config;
import me.creeperscrown.rackitup.MTags;
import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.block.MBlocks;
import me.creeperscrown.rackitup.recipe.DryingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

public class Drying implements IRecipeCategory<DryingRecipe> {
    public static final ResourceLocation ID = new ResourceLocation(RackItUp.MODID, "drying");
    public static final RecipeType<DryingRecipe> DRYING = new RecipeType<>(ID, DryingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable sprite;
    private final IDrawable campfire;
    private final IDrawableAnimated arrow;
    private static final ResourceLocation SPRITE = new ResourceLocation(RackItUp.MODID, "textures/gui/drying_rack_sprite.png");
    private static final ResourceLocation CAMPFIRE = ResourceLocation.withDefaultNamespace("textures/item/campfire.png");

    public Drying(IGuiHelper helper){
        this.background = helper.createBlankDrawable(100, 40);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, MBlocks.OAK_DRYING_RACK.get().asItem().getDefaultInstance());
        this.sprite = helper.drawableBuilder(SPRITE, 0, 0, 16, 4).setTextureSize(16, 4).build();
        this.campfire = helper.drawableBuilder(CAMPFIRE, 0, 0, 16, 16).setTextureSize(16, 16).build();
        this.arrow = helper.createAnimatedRecipeArrow(200);
    }

    @Override
    public void draw(DryingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int time = recipe.getDryingTime();
        String text = convertTime(time);

        arrow.draw(guiGraphics, 37, 10);

        sprite.draw(guiGraphics, 15, 8);
        sprite.draw(guiGraphics, 65, 8);

        if(Config.BOOSTER_CAMPFIRES.get()){
            int boost = Math.max(1, Config.CAMPFIRE_BOOST.get());
            int spedTime = Math.round((float) time/boost);
            guiGraphics.drawString(Minecraft.getInstance().font, convertTime(spedTime), 33, 30, 0xFF5555, false);
        }

        guiGraphics.drawString(Minecraft.getInstance().font, text, 65, 30, 0x7E7E7E, false);
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
    public void setRecipe(IRecipeLayoutBuilder builder, DryingRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 15, 10).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 65, 10).addItemStack(recipe.getResultItem(null));

        boolean campfireRequired = Config.CAMPFIRE_REQUIRED.get();
        boolean boosting = Config.BOOSTER_CAMPFIRES.get();

        if(!campfireRequired && !boosting) return;

        IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.CATALYST, 15, 26).addIngredients(Ingredient.of(MTags.VALID_CAMPFIRES));

        if(boosting){
            slot.addRichTooltipCallback((view, tooltip) -> {tooltip.add(Component.translatable("jei.rackitup.drying_speed_mult", Config.CAMPFIRE_BOOST.get()).withStyle(ChatFormatting.GOLD));});
        }

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
