package net.multiteam.scrapcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Random;

public class CookbotBlockEntity extends BlockEntity implements GeoBlockEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public CookbotBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.COOKBOT_BLOCK_ENTITY.get(), blockPos, blockState);
    }

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
}
