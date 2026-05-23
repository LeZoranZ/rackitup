package me.creeperscrown.client.gui;

import me.creeperscrown.rackitup.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;

public class ConfigScreen extends Screen {
    private final Screen parent;

    private boolean campfireRequired;
    private boolean campfireBoosting;
    private boolean weatherInteraction;

    private EditBox boostAmount;

    private boolean dirty = false;

    public ConfigScreen(Screen parent) {
        super(Component.translatable("config.rackitup.title"));
        this.parent=parent;
    }

    @Override
    protected void init() {
        campfireRequired = Config.CAMPFIRE_REQUIRED.get();
        campfireBoosting = Config.BOOSTER_CAMPFIRES.get();
        weatherInteraction = Config.WEATHER_INTERACTIONS.get();

        int centerX = this.width/2;
        int buttonWidth=200;
        int buttonHeight=20;

        this.addRenderableWidget(
                Button.builder(Component.translatable("config.rackitup.boolean", Component.translatable("config.rackitup.requireCampfire"),
                                        campfireRequired ? Component.translatable("options.on").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)) : Component.translatable("options.off").withStyle(Style.EMPTY.withColor(ChatFormatting.RED))),
                                btn -> {campfireRequired = !campfireRequired; Config.CAMPFIRE_REQUIRED.set(campfireRequired);
                                    btn.setMessage(Component.translatable("config.rackitup.boolean", Component.translatable("config.rackitup.requireCampfire"),
                                            campfireRequired ? Component.translatable("options.on").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)) : Component.translatable("options.off").withStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                                }
                        ).bounds(centerX-100, 40, buttonWidth, buttonHeight)
                        .tooltip(Tooltip.create(Component.translatable("tooltip.rackitup.requireCampfire").withStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW)))))
                        .build());

        this.addRenderableWidget(
                Button.builder(Component.translatable("config.rackitup.boolean", Component.translatable("config.rackitup.campfireBoosting"),
                                        campfireBoosting ? Component.translatable("options.on").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)) : Component.translatable("options.off").withStyle(Style.EMPTY.withColor(ChatFormatting.RED))),
                                btn -> {campfireBoosting = !campfireBoosting; Config.BOOSTER_CAMPFIRES.set(campfireBoosting);
                                    btn.setMessage(Component.translatable("config.rackitup.boolean", Component.translatable("config.rackitup.campfireBoosting"),
                                            campfireBoosting ? Component.translatable("options.on").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)) : Component.translatable("options.off").withStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                                }
                        ).bounds(centerX-100, 60, buttonWidth, buttonHeight)
                        .tooltip(Tooltip.create(Component.translatable("tooltip.rackitup.campfireBoosting").withStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW)))))
                        .build());

        this.addRenderableWidget(
                Button.builder(Component.translatable("config.rackitup.boostAmount"),
                                btn -> {}
                        ).bounds(centerX-100, 80, buttonWidth-(buttonWidth/4)-5, buttonHeight)
                        .tooltip(Tooltip.create(Component.translatable("tooltip.rackitup.boostAmount").withStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW)))))
                        .build());

        boostAmount = new EditBox(this.font, centerX+(buttonWidth/4)-1, 80, buttonWidth/4, buttonHeight, Component.translatable("config.rackitup.boostAmount"));
        boostAmount.setValue(String.valueOf(Config.CAMPFIRE_BOOST.get()));
        boostAmount.setFilter(s -> s.matches("\\d+(\\.\\d*)?"));
        boostAmount.setResponder(v->dirty=true);
        this.addRenderableWidget(boostAmount).setTooltip(Tooltip.create(Component.translatable("tooltip.rackitup.boostAmount").withStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW)))));

        this.addRenderableWidget(
                Button.builder(Component.translatable("config.rackitup.boolean", Component.translatable("config.rackitup.weatherInteractions"),
                                        weatherInteraction ? Component.translatable("options.on").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)) : Component.translatable("options.off").withStyle(Style.EMPTY.withColor(ChatFormatting.RED))),
                                btn -> {weatherInteraction = !weatherInteraction; Config.WEATHER_INTERACTIONS.set(weatherInteraction);
                                    btn.setMessage(Component.translatable("config.rackitup.boolean", Component.translatable("config.rackitup.weatherInteractions"),
                                            weatherInteraction ? Component.translatable("options.on").withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)) : Component.translatable("options.off").withStyle(Style.EMPTY.withColor(ChatFormatting.RED))));
                                }
                        ).bounds(centerX-100, 100, buttonWidth, buttonHeight)
                        .tooltip(Tooltip.create(Component.translatable("tooltip.rackitup.weatherInteractions").withStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.YELLOW)))))
                        .build());

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.done"),
                btn -> this.onClose()
        ).bounds(centerX-100, this.height-20, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g);
        g.drawCenteredString(this.font, this.title, this.width/2, 20, 0xFFFFFF);
        super.render(g, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        try{
            dirty=false;
            double boost = Double.parseDouble(boostAmount.getValue());
            boost = Math.max(1, boost);
            if(boost!=Config.CAMPFIRE_BOOST.get()){Config.CAMPFIRE_BOOST.set(boost); dirty=true;}
        }catch(NumberFormatException ex){}
        if(dirty){
            Config.SPEC.save();
        }
        this.minecraft.setScreen(parent);
    }

}
