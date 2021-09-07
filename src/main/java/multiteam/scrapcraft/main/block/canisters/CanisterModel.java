package multiteam.scrapcraft.main.block.canisters;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class CanisterModel extends AnimatedGeoModel<CanisterTileEntity> implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ResourceLocation getModelLocation(CanisterTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/tileentity/canister_block_small.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CanisterTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/block/small_canister.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CanisterTileEntity block) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/tileentity/canister_block_small.animation.json");
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
