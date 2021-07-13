package multiteam.scrapcraft.main.block.botcapsules;

import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BotCapsuleTileEntity extends TileEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public BotCapsuleTileEntity(TileEntityType<BotCapsuleTileEntity> type) {
        super(type);
    }

    public BotCapsuleTileEntity() {
        this(ModBlocks.TAPEBOT_CAPSULE_TILE.get());
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //event.getController().setAnimation(new AnimationBuilder().addAnimation("there.is.no.animations.yet!!", false));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(-2,-2,-2,4,4,4);
    }


}
