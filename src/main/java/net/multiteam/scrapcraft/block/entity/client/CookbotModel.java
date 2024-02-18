package net.multiteam.scrapcraft.block.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.multiteam.scrapcraft.ScrapCraft;
import net.multiteam.scrapcraft.block.entity.CookbotBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class CookbotModel extends GeoModel<CookbotBlockEntity> {

    @Override
    public ResourceLocation getModelResource(CookbotBlockEntity animatable) {
        return new ResourceLocation(ScrapCraft.MODID, "geo/cookbot_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CookbotBlockEntity animatable) {
        return new ResourceLocation(ScrapCraft.MODID, "textures/block/cookbot_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CookbotBlockEntity animatable) {
        return new ResourceLocation(ScrapCraft.MODID, "animations/cookbot_block.animation.json");
    }
}
