package multiteam.scrapcraft.main.block.cookbot;

import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.concurrent.ThreadLocalRandom;


public class CookBotTileEntity extends TileEntity implements IAnimatable {

    private boolean isCooking = false;
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //event.getController().transitionLengthTicks = 0;
        if(event.getController().getAnimationState() == AnimationState.Stopped){
            int randomChance = ThreadLocalRandom.current().nextInt(0, 100 + 1);
            if(!this.isCooking){
                if(randomChance < 33){
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_1", false));
                }else if(randomChance >= 33 && randomChance < 66){
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_2", false));
                }else{
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cookbot.idle_3", false));
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

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        nbt.putBoolean("isCooking", this.isCooking);

        return nbt;
    }

    /**@Override
    public AxisAlignedBB getRenderBoundingBox() {
        return null;
    }**/
}
