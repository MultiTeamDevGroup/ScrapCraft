package multiteam.scrapcraft.main.item.connect_tool;

import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ConnectToolModel extends AnimatedGeoModel<ConnectToolItem> {
    @Override
    public ResourceLocation getModelLocation(ConnectToolItem object) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "geo/item/connect_tool.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ConnectToolItem object) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "textures/item/connect_tool.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ConnectToolItem animatable) {
        return new ResourceLocation(ScrapCraft.MOD_ID, "animations/item/connect_tool.animation.json");
    }
}
