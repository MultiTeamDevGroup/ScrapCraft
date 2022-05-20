package multiteam.scrapcraft.main.block.botcapsules;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.entity.ModEntities;
import multiteam.scrapcraft.main.entity.tapebot.TapebotEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class BotCapsuleRenderer extends GeoBlockRenderer<BotCapsuleTileEntity> {

    public BotCapsuleRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new BotCapsuleModel());
    }

    @Override
    public RenderType getRenderType(BotCapsuleTileEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(TileEntity tile, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Entity entity = null;
        World worldin = tile.getLevel();
        BlockState state = worldin.getBlockState(tile.getBlockPos());
        if (state == ModBlocks.TAPEBOT_CAPSULE_BLOCK.get().defaultBlockState()) {
            entity = new TapebotEntity(ModEntities.TAPEBOT.get(), tile.getLevel());
        }
        if (entity != null) {
            float f = 1F;
            matrixStackIn.pushPose();
            matrixStackIn.scale(f, f, f);
            matrixStackIn.translate(0.5D, 0.0D, 0.5D);
            Minecraft.getInstance().getEntityRenderDispatcher().render(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
            matrixStackIn.popPose();
        }
        super.render(tile, partialTicks, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }


}
