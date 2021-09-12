package multiteam.scrapcraft.main.block.connectable.switcc;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SwitchModel extends AnimatedGeoModel<SwitchTileEntity> implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public ResourceLocation getModelLocation(SwitchTileEntity object) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/tileentity/switch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SwitchTileEntity object) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/tileentity/parts/switch.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SwitchTileEntity animatable) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/tileentity/switch.animation.json");
    }
}