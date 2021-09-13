package multiteam.scrapcraft.main.block.connectable.seat;

import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.block.connectable.ConnectableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SeatTileEntity extends ConnectableTileEntity implements IAnimatable {



    private final AnimationFactory factory = new AnimationFactory(this);

    public SeatTileEntity(TileEntityType<SeatTileEntity> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public Vector3f defineConnectPointColor() {
        return new Vector3f(255/255.0f, 140/255.0f, 0/255.0f);
    }

    @Override
    public ConnectMethod defineConnectMethod() {
        return ConnectMethod.BIDIRECTIONAL;
    }

    @Override
    public ConnectType defineConnectType() {
        return ConnectType.SEAT;
    }

    @Override
    public int defineConnectAmountLimit() {
        return 100;
    }

    public SeatTileEntity() {
        this(ModBlocks.SEAT_TILE.get());
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

}
