package multiteam.scrapcraft.main.block.cookbot;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CookBotModel extends AnimatedGeoModel implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(Object block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/tileentity/cookbot.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/tileentity/cookbot.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/tileentity/cookbot.animation.json");
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
