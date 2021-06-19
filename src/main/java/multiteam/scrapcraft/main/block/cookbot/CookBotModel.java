package multiteam.scrapcraft.main.block.cookbot;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CookBotModel extends AnimatedGeoModel<CookBotTileEntity> implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(CookBotTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/tileentity/cookbot.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CookBotTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/tileentity/cookbot.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CookBotTileEntity block) {
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
