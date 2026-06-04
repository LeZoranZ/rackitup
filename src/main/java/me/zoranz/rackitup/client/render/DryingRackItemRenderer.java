package me.zoranz.rackitup.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import me.zoranz.rackitup.block.DryingRackBlock;
import me.zoranz.rackitup.block.entity.DryingRackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.AttachFace;

public class DryingRackItemRenderer implements BlockEntityRenderer<DryingRackBlockEntity> {

    public DryingRackItemRenderer(BlockEntityRendererProvider.Context ctx){}

    @Override
    public void render(DryingRackBlockEntity rack, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ItemStack stack = rack.getRenderStack();
        if(stack.isEmpty()) return;

        Direction facing = rack.getBlockState().getValue(DryingRackBlock.FACING);
        AttachFace face = rack.getBlockState().getValue(DryingRackBlock.FACE);

        boolean block = stack.getItem() instanceof BlockItem;
        poseStack.pushPose();

        if(block) renderBlock(poseStack, facing);
        else renderItem(poseStack, facing, face);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                        stack,
                        ItemDisplayContext.FIXED,
                        packedLight,
                        packedOverlay,
                        poseStack,
                        buffer,
                        rack.getLevel(),
                        0
                );

        poseStack.popPose();
    }

    private void renderBlock(PoseStack ps, Direction facing){
        ps.translate(0.5, 0.5, 0.5);
        switch(facing){
            case NORTH -> ps.translate(0,0,0.375);
            case SOUTH -> {ps.translate(0,0,-0.375); ps.mulPose(Axis.YP.rotationDegrees(180));}
            case EAST -> {ps.translate(-0.375,0,0); ps.mulPose(Axis.YP.rotationDegrees(-90));}
            case WEST -> {ps.translate(0.375,0,0); ps.mulPose(Axis.YP.rotationDegrees(90));}
        }
        ps.scale(1f, 1f, 1f);
    }

    private void renderItem(PoseStack ps, Direction facing, AttachFace face){
        ps.translate(0.5, 0.25, 0.5);

        if(face==AttachFace.CEILING) ps.translate(0,0.15,0);
        else if(face==AttachFace.FLOOR) ps.translate(0,-0.6,0);

        switch(facing){
            case NORTH -> {
                if(face==AttachFace.WALL) ps.translate(0,0.15,0.375);
                else ps.mulPose(Axis.YP.rotationDegrees(180));
            }
            case SOUTH -> {
                if(face==AttachFace.WALL){
                    ps.translate(0,0.15,-0.375);
                    ps.mulPose(Axis.YP.rotationDegrees(180));
                }
            }
            case EAST -> {
                if(face==AttachFace.WALL){
                    ps.translate(-0.375,0.15,0);
                    ps.mulPose(Axis.YP.rotationDegrees(-90));
                } else {
                    ps.mulPose(Axis.YP.rotationDegrees(90));
                }
            }
            case WEST -> {
                if(face==AttachFace.WALL){
                    ps.translate(0.375,0.15,0);
                    ps.mulPose(Axis.YP.rotationDegrees(90));
                } else {
                    ps.mulPose(Axis.YP.rotationDegrees(-90));
                }
            }
        }
        ps.scale(0.875f,0.875f,0.875f);
    }

}
