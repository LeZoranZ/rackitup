package me.zoranz.rackitup;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public final ModConfigSpec.BooleanValue campfireRequired;
    public final ModConfigSpec.BooleanValue boosterCampfires;
    public final ModConfigSpec.DoubleValue campfireBoost;
    public final ModConfigSpec.BooleanValue weatherInteractions;

    Config(ModConfigSpec.Builder builder){

        campfireRequired = builder
                .translation("config.rackitup.requireCampfire")
                .comment("Whether drying racks require a campfire to make any progress")
                .comment("Default: false")
                .define("campfire_required", false);
        boosterCampfires = builder
                .translation("config.rackitup.campfireBoosting")
                .comment("Whether campfires should speed up the drying process")
                .comment("Default: true")
                .define("booster_campfires", true);
        campfireBoost = builder
                .translation("config.rackitup.boostAmount")
                .comment("Campfire drying speed multiplier")
                .comment("Default: 2.0")
                .defineInRange("campfire_boost", 2.0, 1.0, Double.MAX_VALUE);
        weatherInteractions = builder
                .comment("Should direct rainfall make drying racks lose progress?")
                .translation("config.rackitup.weatherInteractions")
                .comment("Default: false")
                .define("weather_interactions", false);

    }

    public static final Config CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    static {
        Pair<Config, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(Config::new);

        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

}
