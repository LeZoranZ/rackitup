package me.zoranz.rackitup.client;

import me.zoranz.rackitup.client.render.DryingRackItemRenderer;
import me.zoranz.rackitup.RackItUp;
import me.zoranz.rackitup.block.MBlocks;
import me.zoranz.rackitup.block.entity.MBlockEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

@EventBusSubscriber(modid = RackItUp.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MClientStuff {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e){
        e.enqueueWork(()->{
           for(DeferredBlock<Block> block : MBlocks.DRYING_RACKS.values()){
               ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
           }
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                MBlockEntities.DRYING_RACK.get(),
                DryingRackItemRenderer::new
        );
    }

}
