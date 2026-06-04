package me.zoranz.rackitup.tab;

import me.zoranz.rackitup.RackItUp;
import me.zoranz.rackitup.block.MBlocks;
import me.zoranz.rackitup.item.MItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RackItUp.MODID);

    public static final Supplier<CreativeModeTab> RACK_IT_UP = TABS.register("rack_it_up", ()->CreativeModeTab
            .builder()
            .title(Component.translatable("tab.rackitup.name"))
            .icon(() -> MBlocks.OAK_DRYING_RACK.get().asItem().getDefaultInstance())
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

    public static void registerTabs(IEventBus bus){
        TABS.register(bus);
    }
}
