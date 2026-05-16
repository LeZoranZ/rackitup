package me.creeperscrown.rackitup.item;

import me.creeperscrown.rackitup.RackItUp;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RackItUp.MODID);

    public static final RegistryObject<Item> MONSTER_JERKY =
            ITEMS.register("monster_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(1.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> BEEF_JERKY =
            ITEMS.register("beef_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(1.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> CHICKEN_JERKY =
            ITEMS.register("chicken_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(1.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> TROPICAL_FISH_JERKY =
            ITEMS.register("tropical_fish_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(1.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> COD_JERKY =
            ITEMS.register("cod_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(1.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> MUTTON_JERKY =
            ITEMS.register("mutton_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(2f)
                                    .build())
                    ));
    public static final RegistryObject<Item> PORK_JERKY =
            ITEMS.register("pork_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(5)
                                    .saturationMod(2f)
                                    .build())
                    ));
    public static final RegistryObject<Item> PUFFERFISH_JERKY =
            ITEMS.register("pufferfish_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(1.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> RABBIT_JERKY =
            ITEMS.register("rabbit_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(2.5f)
                                    .build())
                    ));
    public static final RegistryObject<Item> SALMON_JERKY =
            ITEMS.register("salmon_jerky",
                    () -> new Item(new Item.Properties()
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(1.75f)
                                    .build())
                    ));


    public static void registerItems(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
