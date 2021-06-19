package multiteam.scrapcraft.main.block.cookbot;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.client.container.CookBotContainer;
import multiteam.scrapcraft.main.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CookBotTileEntity extends LockableLootTileEntity implements IAnimatable, ITickableTileEntity {

    public static final int slots = 1;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
    public int selectedFood;
    public int selectedFoodToProgress;
    public ItemStack outputItem = ItemStack.EMPTY;
    public int cookingProgress;

    public boolean isCooking;
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //event.getController().transitionLengthTicks = 0;
        if(!this.isCooking){
            if(event.getController().getAnimationState() == AnimationState.Stopped){
                int randomChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                if(randomChance < 10){ //10% - head hitting
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_2", false));
                }else if(randomChance < 80){ //70% - look at spoon
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_1", false));
                }else if(randomChance < 90){ //10% - spoon sharpen
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_3", false));
                }else{ //10% - no anim
                    return PlayState.CONTINUE;
                }
            }
        }else{
            switch (selectedFoodToProgress){
                case 1:
                    //Pizza Burger - anim
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.cook_pizza_burger", false));
                    break;
                case 2:
                    //Veggie burger - anim
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.cook_veggie_burger", false));
                    break;
                case 3:
                    //Revival Baguette - anim
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.cook_revival_baguette", false));
                    break;
            }
        }

        return PlayState.CONTINUE;
    }

    public CookBotTileEntity(TileEntityType<CookBotTileEntity> type) {
        super(type);
    }

    public CookBotTileEntity() {
        this(ModBlocks.COOKBOT_TILE.get());
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);

        this.isCooking = nbt.getBoolean("isCooking");
        this.selectedFood = nbt.getInt("selectedFood");
        this.selectedFoodToProgress = nbt.getInt("selectedFoodToProgress");
        this.cookingProgress = nbt.getInt("cookingProgress");

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        nbt.putBoolean("isCooking", this.isCooking);
        nbt.putInt("selectedFood", this.selectedFood);
        nbt.putInt("selectedFoodToProgress", this.selectedFoodToProgress);
        nbt.putInt("cookingProgress", this.cookingProgress);

        return nbt;
    }



    @Override
    public ITextComponent getDefaultName() {
        return new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new CookBotContainer(id, playerInventory, this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public void tick() {

        if(!isCooking){
            selectedFoodToProgress = selectedFood;
        }

        if(isCooking && cookingProgress > 0){
            --this.cookingProgress;
        }

        if(isCooking && cookingProgress == 0){
            outputItem = getOutputItem(selectedFoodToProgress);
            isCooking = false;
        }
    }

    public ItemStack getOutputItem(int selItem){
        ItemStack returnStack = ItemStack.EMPTY;
        switch (selItem){
            case 1:
                //Pizza Burger
                returnStack = new ItemStack(ModItems.PIZZA_BURGER.get());
                break;
            case 2:
                //Veggie burger
                returnStack = new ItemStack(ModItems.VEGGIE_BURGER.get());
            break;
            case 3:
                //Revival Baguette
                returnStack = new ItemStack(ModItems.REVIVAL_BAGUETTE.get());
            break;
        }
        return returnStack;
    }

    public void giveResult(PlayerEntity player){
        if(!player.level.isClientSide){
            player.inventory.add(getOutputItem(this.selectedFoodToProgress));
        }
    }

    public void setValues(int selected, int progress, boolean isCooking, ItemStack output){
        this.selectedFood = selected;
        this.cookingProgress = progress;
        this.isCooking = isCooking;
        this.outputItem = output;
    }
}
