package net.multiteam.scrapcraft.block.entity.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.multiteam.scrapcraft.block.entity.CookbotBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CookbotRenderer extends GeoBlockRenderer<CookbotBlockEntity> {

    public CookbotRenderer(BlockEntityRendererProvider.Context context){
        super(new CookbotModel());
    }
}
