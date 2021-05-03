package multiteam.scrapcraft.main.block.cookbot;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.container.CookBotContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
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

import java.util.concurrent.ThreadLocalRandom;

public class CookBotTileEntity extends LockableLootTileEntity implements IAnimatable {

    public static int slots = 4;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    private boolean isCooking = false;
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //event.getController().transitionLengthTicks = 0;
        if(event.getController().getAnimationState() == AnimationState.Stopped){
            int randomChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(!this.isCooking){
                if(randomChance < 10){ //10% - head hitting
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_2", false));
                }else if(randomChance >= 10 && randomChance < 80){ //70% - look at spoon
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_1", false));
                }else if(randomChance >= 80 && randomChance < 90){ //10% - spoon sharpen
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_3", false));
                }else{ //10% - no anim
                    return PlayState.CONTINUE;
                }
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
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);

        this.isCooking = nbt.getBoolean("isCooking");
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ItemStackHelper.loadAllItems(nbt, this.items);
        }

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        nbt.putBoolean("isCooking", this.isCooking);
        if(!this.trySaveLootTable(nbt)){
            ItemStackHelper.saveAllItems(nbt, this.items);
        }

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
        return this.slots;
    }

    /**@Override
    public AxisAlignedBB getRenderBoundingBox() {
        return null;
    }**/
}
