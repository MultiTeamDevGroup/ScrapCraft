package multiteam.scrapcraft.main.entity.tapebot;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

public class TapebotEntityModel extends AnimatedGeoModel<TapebotEntity> implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(TapebotEntity entity) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/entity/tapebot.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TapebotEntity entity) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/entity/bot/tapebot.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TapebotEntity entity) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/entity/tapebot.animation.json");
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void setLivingAnimations(TapebotEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
