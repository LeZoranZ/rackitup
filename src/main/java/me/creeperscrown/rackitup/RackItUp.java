package me.creeperscrown.rackitup;

import me.creeperscrown.rackitup.block.MBlocks;
import me.creeperscrown.rackitup.block.entity.MBlockEntities;
import me.creeperscrown.rackitup.item.MItems;
import me.creeperscrown.rackitup.recipe.MRecipes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod(RackItUp.MODID)
public class RackItUp {

    public static final String MODID = "rackitup";

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> RACK_IT_UP = TABS.register("rack_it_up", () -> CreativeModeTab.builder()
            .icon(()->MBlocks.OAK_DRYING_RACK.get().asItem().getDefaultInstance())
            .title(Component.translatable("itemGroup.rack_it_up"))
            .displayItems((parameters, output) -> {
                //Racks
                output.accept(MBlocks.DRYING_RACK.get());
                output.accept(MBlocks.OAK_DRYING_RACK.get());
                output.accept(MBlocks.SPRUCE_DRYING_RACK.get());
                output.accept(MBlocks.BIRCH_DRYING_RACK.get());
                output.accept(MBlocks.JUNGLE_DRYING_RACK.get());
                output.accept(MBlocks.ACACIA_DRYING_RACK.get());
                output.accept(MBlocks.DARK_OAK_DRYING_RACK.get());
                output.accept(MBlocks.MANGROVE_DRYING_RACK.get());
                output.accept(MBlocks.CHERRY_DRYING_RACK.get());
                output.accept(MBlocks.BAMBOO_DRYING_RACK.get());
                output.accept(MBlocks.CRIMSON_DRYING_RACK.get());
                output.accept(MBlocks.WARPED_DRYING_RACK.get());
                //Food
                output.accept(MItems.BEEF_JERKY.get());
                output.accept(MItems.CHICKEN_JERKY.get());
                output.accept(MItems.TROPICAL_FISH_JERKY.get());
                output.accept(MItems.COD_JERKY.get());
                output.accept(MItems.MONSTER_JERKY.get());
                output.accept(MItems.MUTTON_JERKY.get());
                output.accept(MItems.PORK_JERKY.get());
                output.accept(MItems.PUFFERFISH_JERKY.get());
                output.accept(MItems.RABBIT_JERKY.get());
                output.accept(MItems.SALMON_JERKY.get());
            }).build());

    public RackItUp() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MBlocks.registerBlocks(modEventBus);
        MItems.registerItems(modEventBus);
        MRecipes.SERIALIZERS.register(modEventBus);
        MRecipes.TYPES.register(modEventBus);
        MBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

}
