package me.zoranz.rackitup.item;

import me.zoranz.rackitup.RackItUp;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RackItUp.MODID);

    public static final DeferredItem<Item> MONSTER_JERKY =
            ITEMS.register("monster_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> BEEF_JERKY =
            ITEMS.register("beef_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> CHICKEN_JERKY =
            ITEMS.register("chicken_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> TROPICAL_FISH_JERKY =
            ITEMS.register("tropical_fish_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationModifier(1.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> COD_JERKY =
            ITEMS.register("cod_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> MUTTON_JERKY =
            ITEMS.register("mutton_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(2f)
                                    .build())
                    ));
    public static final DeferredItem<Item> PORK_JERKY =
            ITEMS.register("pork_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationModifier(2f)
                                    .build())
                    ));
    public static final DeferredItem<Item> PUFFERFISH_JERKY =
            ITEMS.register("pufferfish_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationModifier(1.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> RABBIT_JERKY =
            ITEMS.register("rabbit_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationModifier(2.5f)
                                    .build())
                    ));
    public static final DeferredItem<Item> SALMON_JERKY =
            ITEMS.register("salmon_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1.75f)
                                    .build())
                    ));

    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
