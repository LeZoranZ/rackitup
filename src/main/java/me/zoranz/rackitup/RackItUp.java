package me.zoranz.rackitup;

import me.zoranz.rackitup.block.MBlocks;
import me.zoranz.rackitup.block.entity.MBlockEntities;
import me.zoranz.rackitup.item.MItems;
import me.zoranz.rackitup.recipe.MRecipes;
import me.zoranz.rackitup.tab.MTabs;
import net.minecraft.core.Direction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(RackItUp.MODID)
public class RackItUp {
    public static final String MODID = "rackitup";
    public RackItUp(IEventBus modEventBus, ModContainer modContainer) {
        MTabs.registerTabs(modEventBus);
        MBlocks.registerBlocks(modEventBus);
        MItems.registerItems(modEventBus);
        MRecipes.SERIALIZERS.register(modEventBus);
        MRecipes.TYPES.register(modEventBus);
        MBlockEntities.registerBlockEntities(modEventBus);

        //NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::registerCapabilities);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent e){
        e.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                MBlockEntities.DRYING_RACK.get(),
                (rack, side) -> {
                    if (side == Direction.DOWN) {
                        return rack.getOutputHandler();
                    }
                    return rack.getInputHandler();
                }
        );
    }

}
