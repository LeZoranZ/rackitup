package me.creeperscrown.rackitup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RackItUp.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue CAMPFIRE_REQUIRED = BUILDER
            .comment("Whether drying racks require a campfire to make any progress")
            .comment("Default: false")
            .define("campfire_required", false);
    public static final ForgeConfigSpec.BooleanValue BOOSTER_CAMPFIRES = BUILDER
            .comment("Whether campfires should speed up the drying process")
            .comment("Default: true")
            .define("booster_campfires", true);
    public static final ForgeConfigSpec.IntValue CAMPFIRE_BOOST = BUILDER
            .comment("Campfire drying speed multiplier")
            .comment("Default: 2")
            .defineInRange("campfire_boost", 2, 1, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.BooleanValue WEATHER_INTERACTIONS = BUILDER
            .comment("Should direct rainfall make drying racks lose progress?")
            .comment("Default: false")
            .define("weather_interactions", false);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

}
