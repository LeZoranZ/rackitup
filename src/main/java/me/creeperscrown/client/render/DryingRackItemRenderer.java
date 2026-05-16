package me.creeperscrown.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import me.creeperscrown.rackitup.block.DryingRackBlock;
import me.creeperscrown.rackitup.block.entity.DryingRackBlockEntity;
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
        poseStack.pushPose();

        if(stack.getItem() instanceof BlockItem) {
            poseStack.translate(0.5, 0.5, 0.5);
            // i am too lazy to actually make this normal rn
            switch(facing) {
                case NORTH -> {
                    if(face==AttachFace.CEILING){poseStack.translate(0, 0, 0.375);}
                    else if(face==AttachFace.FLOOR){poseStack.translate(0, 0, 0.375);}
                    else poseStack.translate(0, 0, 0.375);
                }
                case SOUTH -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(0, 0, -0.375);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(0, 0, -0.375);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                    else {
                        poseStack.translate(0, 0, -0.375);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                }
                case EAST -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(-0.375, 0, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(-0.375, 0, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                    }
                    else {
                        poseStack.translate(-0.375, 0, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                    }
                }
                case WEST -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(0.375, 0, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(0.375, 0, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    }
                    else {
                        poseStack.translate(0.375, 0, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    }
                }
            }

            poseStack.scale(1f, 1f, 1f);
        }
        else {
            poseStack.translate(0.5, 0.25, 0.5);

            switch(facing) {
                case NORTH -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(0, 0.15, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(0, -0.6, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                    else poseStack.translate(0, 0.15, 0.375);
                }
                case SOUTH -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(0, 0.15, 0);
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(0, -0.6, 0);
                    }
                    else {
                        poseStack.translate(0, 0.15, -0.375);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                }
                case EAST -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(0, 0.15, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(0, -0.6, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    }
                    else {
                        poseStack.translate(-0.375, 0.15, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                    }
                }
                case WEST -> {
                    if(face==AttachFace.CEILING){
                        poseStack.translate(0, 0.15, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                    }
                    else if(face==AttachFace.FLOOR){
                        poseStack.translate(0, -0.6, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(-90));
                    }
                    else {
                        poseStack.translate(0.375, 0.15, 0);
                        poseStack.mulPose(Axis.YP.rotationDegrees(90));
                    }
                }
            }

            poseStack.scale(0.875f, 0.875f, 0.875f);
        }

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

}
