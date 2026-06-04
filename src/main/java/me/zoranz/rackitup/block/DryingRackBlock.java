package me.zoranz.rackitup.block;

import com.mojang.serialization.MapCodec;
import me.zoranz.rackitup.block.entity.DryingRackBlockEntity;
import me.zoranz.rackitup.block.entity.MBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;

public class DryingRackBlock extends BaseEntityBlock {

    public static final MapCodec<DryingRackBlock> CODEC = simpleCodec(DryingRackBlock::new);

    private static final VoxelShape CEILING_Z =
            Block.box(0, 12, 6, 16, 16, 10);
    private static final VoxelShape CEILING_X =
            Block.box(6, 12, 0, 10, 16, 16);
    private static final VoxelShape FLOOR_Z =
            Block.box(0, 0, 6, 16, 4, 10);
    private static final VoxelShape FLOOR_X =
            Block.box(6, 0, 0, 10, 4, 16);

    private static final VoxelShape NORTH_SHAPE =
            Block.box(0, 12, 12, 16, 16, 16);
    private static final VoxelShape SOUTH_SHAPE =
            Block.box(0, 12, 0, 16, 16, 4);
    private static final VoxelShape EAST_SHAPE =
            Block.box(0, 12, 0, 4, 16, 16);
    private static final VoxelShape WEST_SHAPE =
            Block.box(12, 12, 0, 16, 16, 16);

    public static final DirectionProperty FACING =
            BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;

    public DryingRackBlock(Properties props) {
        super(props);

        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL));
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);

            if (be instanceof DryingRackBlockEntity rack) {
                ItemStackHandler inv = rack.getInventory();
                ItemStack stack = inv.getStackInSlot(1);
                if(stack.isEmpty()) stack = inv.getStackInSlot(0);
                if(!stack.isEmpty()){
                    Containers.dropItemStack(level, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, stack.copy());
                }
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DryingRackBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, MBlockEntities.DRYING_RACK.get(), DryingRackBlockEntity::tick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if(level.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if(!(be instanceof DryingRackBlockEntity rack)) return InteractionResult.PASS;

        ItemStackHandler inv = rack.getInventory();
        ItemStack input = inv.getStackInSlot(0);
        ItemStack output = inv.getStackInSlot(1);
        if(!output.isEmpty()){
            ItemStack out = inv.extractItem(1, output.getCount(), false);
            player.addItem(out);
            return InteractionResult.CONSUME;
        }
        if(!input.isEmpty()){
            ItemStack out = inv.extractItem(0, input.getCount(), false);
            player.addItem(out);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(level.isClientSide) return ItemInteractionResult.SUCCESS;

        BlockEntity be = level.getBlockEntity(pos);
        if(!(be instanceof DryingRackBlockEntity rack)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        ItemStackHandler inv = rack.getInventory();

        ItemStack output = inv.getStackInSlot(1);
        ItemStack input = inv.getStackInSlot(0);

        if(!output.isEmpty()){
            player.addItem(inv.extractItem(1, output.getCount(), false));
            return ItemInteractionResult.SUCCESS;
        }
        if(stack.isEmpty()){
            if (!input.isEmpty()) {
                player.addItem(inv.extractItem(0, 1, false));
                return ItemInteractionResult.SUCCESS;
            }

            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if(!input.isEmpty()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // Insertion
        ItemStack in = stack.copy();
        in.setCount(1);

        ItemStack remainder = inv.insertItem(0, in, false);

        if (remainder.isEmpty()) {
            stack.shrink(1);
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){builder.add(FACING, FACE);}

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction clicked = ctx.getClickedFace();

        if(clicked==Direction.UP){
            return defaultBlockState()
                    .setValue(FACING, ctx.getHorizontalDirection())
                    .setValue(FACE, AttachFace.FLOOR);
        }
        if(clicked==Direction.DOWN){
            return defaultBlockState()
                    .setValue(FACING, ctx.getHorizontalDirection())
                    .setValue(FACE, AttachFace.CEILING);
        }

        return defaultBlockState()
                .setValue(FACING, clicked)
                .setValue(FACE, AttachFace.WALL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context){
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(FACE);
        if(face==AttachFace.FLOOR){
            return facing.getAxis()==Direction.Axis.X ? FLOOR_X : FLOOR_Z;
        }
        if(face==AttachFace.CEILING){
            return facing.getAxis()==Direction.Axis.X ? CEILING_X : CEILING_Z;
        }
        return switch(facing) {
            case NORTH -> NORTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> SOUTH_SHAPE;
        };
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
