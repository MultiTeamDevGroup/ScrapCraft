package multiteam.scrapcraft.main.entity.tape_projectile;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TapeProjectileModel extends AnimatedGeoModel<TapeProjectileEntity> implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(TapeProjectileEntity entity) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/entity/tape_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TapeProjectileEntity entity) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/entity/projectile/tape_projectile.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TapeProjectileEntity entity) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/entity/tape_projectile.animation.json");
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
