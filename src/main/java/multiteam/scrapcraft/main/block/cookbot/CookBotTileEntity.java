package multiteam.scrapcraft.main.block.cookbot;

import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class CookBotTileEntity extends TileEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("anim.name", true));
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
    public void deserializeNBT(CompoundNBT nbt) {

    }

    @Override
    public void deserializeNBT(BlockState state, CompoundNBT nbt) {

    }

    /**@Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return null;
    }**/
}
