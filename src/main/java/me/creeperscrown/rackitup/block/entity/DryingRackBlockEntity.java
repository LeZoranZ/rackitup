package me.creeperscrown.rackitup.block.entity;

import me.creeperscrown.rackitup.Config;
import me.creeperscrown.rackitup.recipe.DryingRecipe;
import me.creeperscrown.rackitup.recipe.MRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DryingRackBlockEntity extends BlockEntity {

    private double progress = 0;
    private double maxProgress = 0;

    private final ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private final LazyOptional<RangedWrapper> inputHandler = LazyOptional.of(()->new RangedWrapper(inventory, 0, 1));
    private final LazyOptional<RangedWrapper> outputHandler = LazyOptional.of(()->new RangedWrapper(inventory, 1, 2));

    public DryingRackBlockEntity(BlockPos pos, BlockState state){
        super(MBlockEntities.DRYING_RACK.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DryingRackBlockEntity rack) {
        if(level.isClientSide) return;
        ItemStack input = rack.inventory.getStackInSlot(0);
        if(input.isEmpty()){
            rack.progress=0;
            return;
        }

        if(!rack.inventory.getStackInSlot(1).isEmpty()) return;

        SimpleContainer container = new SimpleContainer(input);
        Optional<DryingRecipe> match = level.getRecipeManager().getRecipeFor(MRecipes.DRYING.get(), container, level);

        if(match.isEmpty()){
            rack.progress = 0;
            return;
            // if no recipe act as an item rack
        }

        BlockState below = level.getBlockState(pos.below());
        boolean campfireRequired = Config.CAMPFIRE_REQUIRED.get();
        boolean weatherInteraction = Config.WEATHER_INTERACTIONS.get();
        boolean boosting = Config.BOOSTER_CAMPFIRES.get();

        boolean isCampfire = below.getBlock() instanceof CampfireBlock;
        boolean isLit = CampfireBlock.isLitCampfire(level.getBlockState(pos.below()));
        boolean isRaining = level.isRainingAt(rack.getBlockPos()) && level.canSeeSky(rack.getBlockPos());

        if(campfireRequired){
            if(!isCampfire) return;
            if(!isLit) return;
        }

        DryingRecipe recipe = match.get();
        rack.maxProgress = recipe.getDryingTime();

        if(weatherInteraction && isRaining){
            rack.progress = Math.max(0, rack.progress-1);
        } else {
            double boost = (boosting && isCampfire && isLit) ? Math.max(1, Config.CAMPFIRE_BOOST.get()) : 1;
            rack.progress += boost;
        }

        rack.progress = Math.min(rack.progress, rack.maxProgress);
        // 'just in case' ahh
        if(rack.progress >= rack.maxProgress){
            ItemStack result = recipe.assemble(container, level.registryAccess());
            rack.inventory.extractItem(0, 1, false);
            rack.inventory.setStackInSlot(1, result);
            rack.progress = 0;

            rack.setChanged();
        }

    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inventory", inventory.serializeNBT());
        tag.putDouble("progress", progress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("inventory"));
        progress = tag.getInt("progress");
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("inventory", inventory.serializeNBT());
        tag.putDouble("progress", progress);
        return tag;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side){
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == Direction.DOWN) return outputHandler.cast();

            return inputHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inputHandler.invalidate();
        outputHandler.invalidate();
    }

    public ItemStackHandler getInventory(){return inventory;}

    public ItemStack getRenderStack(){
        ItemStack output = inventory.getStackInSlot(1);
        if(!output.isEmpty()) return output;
        return inventory.getStackInSlot(0);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public void setChanged(){
        super.setChanged();
        if(level!=null && !level.isClientSide){level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);}
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition).inflate(1.0);
    }

}
