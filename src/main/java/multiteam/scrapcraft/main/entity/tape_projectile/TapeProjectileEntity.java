package multiteam.scrapcraft.main.entity.tape_projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class TapeProjectileEntity extends AbstractArrowEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public TapeProjectileEntity(EntityType<? extends AbstractArrowEntity> p_i48546_1_, World p_i48546_2_) {
        super(p_i48546_1_, p_i48546_2_);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tape.moving", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    //this.makeParticle(1);
                }
            } else {
                //this.makeParticle(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 600) { //&& !this.effects.isEmpty()
            this.level.broadcastEntityEvent(this, (byte)0);
            //this.potion = Potions.EMPTY;
            //this.effects.clear();
            //this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

}
