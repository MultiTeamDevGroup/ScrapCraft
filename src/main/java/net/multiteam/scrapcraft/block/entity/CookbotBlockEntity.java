package net.multiteam.scrapcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.multiteam.scrapcraft.gui.CookbotMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.List;
import java.util.Random;

public class CookbotBlockEntity extends BlockEntity implements GeoBlockEntity, MenuProvider {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private ItemStackHandler itemHandler = new ItemStackHandler(1);
    private static final int OUTPUT_SLOT = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private int progressTicks = 0;
    private RecipeType selectedRecipeType = RecipeType.PIZZA_BURGER;
    private boolean isCooking = false;


    public enum RecipeType{
        PIZZA_BURGER(
                260,
                List.of(new ItemStack(Items.BEEF, 1), new ItemStack(Items.MILK_BUCKET,2), new ItemStack(Items.POTATO,2), new ItemStack(Items.BEETROOT,1)) //TODO replace beef with woc meat, beetroot with tomato, vanilla potato with modded potato, milk bucket with woc milk
        ),
        VEGGIE_BURGER(
                260,
                List.of(new ItemStack(Items.CARROT, 2), new ItemStack(Items.BEETROOT, 1), new ItemStack(Items.POTATO, 1), new ItemStack(Items.BAKED_POTATO, 1)) //TODO replace baked potato with tomato, vanilla potato with modded potato, milk bucket with woc milk
        ),
        REVIVAL_BAGUETTE(
                540,
                List.of(new ItemStack(Items.BEEF, 1),new ItemStack(Items.MILK_BUCKET, 2),new ItemStack(Items.BEETROOT, 1),new ItemStack(Items.BAKED_POTATO, 1)) //TODO replace baked potato with tomato, vanilla potato with modded potato, milk bucket with woc milk
        );

        private final int cookTimeInTicks;

        private final List<ItemStack> ingredients;
        RecipeType(int cookTimeInTicks, List<ItemStack> ingredients){
            this.cookTimeInTicks = cookTimeInTicks;
            this.ingredients = ingredients;
        }

        public int getCookTimeInTicks(){
            return this.cookTimeInTicks;
        }

        public List<ItemStack> getIngredientsList(){
            return this.ingredients;
        }

    }

    public CookbotBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.COOKBOT_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    //---- ANIMATION ----

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }
    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){

        if(tAnimationState.getController().getAnimationState() == AnimationController.State.STOPPED){

            int percent = new Random().nextInt(-100, 101);

            //below ---% we skip animating this time
            if(percent > 20 && percent < 50){ // 20-50 so ---% chance for "look at spoon"
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cookbot.idle_look_at_spoon", Animation.LoopType.PLAY_ONCE));
            }else if(percent > 50 && percent < 80){ // 50-80 so ---% chance for head hitting
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cookbot.idle_head_hitting", Animation.LoopType.PLAY_ONCE));
            }else if(percent > 80){ // 80-100 so ---% chance for spoon sharpen
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.cookbot.idle_spoon_sharpen", Animation.LoopType.PLAY_ONCE));
            }
            //TODO Calculate chance percentages lmfao
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }


    //---- GUI ----
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops(){
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, container);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.scrapcraft.cookbot_block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CookbotMenu(containerId, playerInventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", this.itemHandler.serializeNBT());
        tag.putInt("cookbot.progressTicks", this.progressTicks);
        tag.putInt("cookbot.selectedRecipeType", this.selectedRecipeType.ordinal());
        tag.putBoolean("cookbot.isCooking", this.isCooking);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.itemHandler.deserializeNBT(tag.getCompound("inventory"));
        this.progressTicks = tag.getInt("cookbot.progressTicks");
        this.selectedRecipeType = RecipeType.values()[tag.getInt("cookbot.selectedRecipeType")];
        this.isCooking = tag.getBoolean("cookbot.isCooking");
    }

    public void tick(Level level1, BlockPos pos, BlockState state) {
        //TODO COOKING LOGIC
    }

    public int getProgressTicks(){
        return this.progressTicks;
    }

    public RecipeType getSelectedRecipeType(){
        return this.selectedRecipeType;
    }

    public boolean isCooking(){
        return this.isCooking;
    }

}
