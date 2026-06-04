package me.zoranz.rackitup.block.entity;

import me.zoranz.rackitup.Config;
import me.zoranz.rackitup.recipe.DryingRecipe;
import me.zoranz.rackitup.recipe.MRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
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

    private final RangedWrapper inputHandler = new RangedWrapper(inventory, 0, 1);
    private final RangedWrapper outputHandler = new RangedWrapper(inventory, 1, 2);

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

        SingleRecipeInput container = new SingleRecipeInput(input);
        Optional<RecipeHolder<DryingRecipe>> match = level.getRecipeManager().getRecipeFor(MRecipes.DRYING.get(), container, level);

        if(match.isEmpty()){
            rack.progress = 0;
            return;
            // if no recipe act as an item rack
        }

        BlockState below = level.getBlockState(pos.below());
        boolean campfireRequired = Config.CONFIG.campfireRequired.get();
        boolean weatherInteraction = Config.CONFIG.weatherInteractions.get();
        boolean boosting = Config.CONFIG.boosterCampfires.get();
        double boostMult = Config.CONFIG.campfireBoost.get();

        boolean isCampfire = below.getBlock() instanceof CampfireBlock;
        boolean isLit = CampfireBlock.isLitCampfire(level.getBlockState(pos.below()));
        boolean isRaining = level.isRainingAt(rack.getBlockPos()) && level.canSeeSky(rack.getBlockPos());

        if(campfireRequired){
            if(!isCampfire) return;
            if(!isLit) return;
        }

        DryingRecipe recipe = match.get().value();
        rack.maxProgress = recipe.getDryingTime();

        if(weatherInteraction && isRaining){
            rack.progress = Math.max(0, rack.progress-1);
        } else {
            double boost = (boosting && isCampfire && isLit) ? Math.max(1, boostMult) : 1;
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
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putDouble("progress", progress);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        inventory.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getDouble("progress");
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        tag.put("inventory", inventory.serializeNBT(registries));
        tag.putDouble("progress", progress);
        return tag;
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
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        loadAdditional(tag, registries);
    }

    @Override
    public void setChanged(){
        super.setChanged();
        if(level!=null && !level.isClientSide){level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);}
    }

    public RangedWrapper getInputHandler(){
        return inputHandler;
    }
    public RangedWrapper getOutputHandler(){
        return outputHandler;
    }

}
