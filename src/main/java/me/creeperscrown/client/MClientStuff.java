package me.creeperscrown.client;

import me.creeperscrown.client.gui.ConfigScreen;
import me.creeperscrown.client.render.DryingRackItemRenderer;
import me.creeperscrown.rackitup.RackItUp;
import me.creeperscrown.rackitup.block.MBlocks;
import me.creeperscrown.rackitup.block.entity.MBlockEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = RackItUp.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MClientStuff {

    @SubscribeEvent public static void clientSetup(FMLClientSetupEvent e){
        e.enqueueWork(()->{
           for(RegistryObject<Block> block : MBlocks.DRYING_RACKS.values()){
               ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
           }
        });

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                ()-> new ConfigScreenHandler.ConfigScreenFactory(
                        (mc, parent) -> new ConfigScreen(parent)
                )
        );
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                MBlockEntities.DRYING_RACK.get(),
                DryingRackItemRenderer::new
        );
    }

}
