package multiteam.scrapcraft.main.block.connectable.seat;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeatModel extends AnimatedGeoModel<SeatTileEntity> implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public ResourceLocation getModelLocation(SeatTileEntity object) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/tileentity/this_does_not_exist.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SeatTileEntity object) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/block/neither_does_this.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SeatTileEntity animatable) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/tileentity/cookbot.animation.json");
    }
}
