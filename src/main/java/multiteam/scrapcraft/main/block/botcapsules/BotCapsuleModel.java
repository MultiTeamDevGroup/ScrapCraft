package multiteam.scrapcraft.main.block.botcapsules;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BotCapsuleModel extends AnimatedGeoModel<BotCapsuleTileEntity> implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(BotCapsuleTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/tileentity/tapebot_capsule.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BotCapsuleTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/tileentity/tapebot_capsule.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BotCapsuleTileEntity block) {
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
