package multiteam.scrapcraft.main.block.connectable.seat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.scrapcraft.main.client.rendering.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;
import java.util.Random;

public class SeatRenderer extends GeoBlockRenderer<SeatTileEntity> {

    public SeatRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new SeatModel());
    }

    @Override
    public RenderType getRenderType(SeatTileEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(TileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {

        if(tile instanceof SeatTileEntity){
            SeatTileEntity seatTile = (SeatTileEntity)tile;

            if(seatTile.getConnectPointState()){

                TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(new ResourceLocation("minecraft", "block/cobblestone"));
                long time = System.currentTimeMillis();
                IVertexBuilder builder = buffer.getBuffer(ModRenderTypes.OVERLAY_BLOCK);
                Random rnd = new Random(seatTile.getBlockPos().getX() * 337L + seatTile.getBlockPos().getY() * 37L + seatTile.getBlockPos().getZ() * 13L);
                double speed = 2d;
                float angle = (time / (int)speed) % 360;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);
                float scale = 1.0f + diffFunction(time,900 + rnd.nextInt(800), 0.0001f + rnd.nextFloat() * 0.001f);

                float dx1 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dx2 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dx3 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dx4 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dy1 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dy2 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dy3 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
                float dy4 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);

                matrixStack.pushPose();

                add(builder, matrixStack, 0, 0, .5f, sprite.getU0(), sprite.getV0());
                add(builder, matrixStack, 1, 0, .5f, sprite.getU1(), sprite.getV0());
                add(builder, matrixStack, 1, 1 , .5f, sprite.getU1(), sprite.getV1());
                add(builder, matrixStack, 0 , 1, .5f, sprite.getU0(), sprite.getV1());

                add(builder, matrixStack, 0, 1, .5f, sprite.getU0(), sprite.getV1());
                add(builder, matrixStack, 1, 1, .5f, sprite.getU1(), sprite.getV1());
                add(builder, matrixStack, 1, 0, .5f, sprite.getU1(), sprite.getV0());
                add(builder, matrixStack, 0, 0, .5f, sprite.getU0(), sprite.getV0());

                matrixStack.popPose();
            }


        }

    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v) {
        renderer.vertex(stack.last().pose(), x, y, z)
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .uv(u, v)
                .uv2(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    private static float diffFunction(long time, long delta, float scale) {
        long dt = time % (delta * 2);
        if (dt > delta) {
            dt = 2*delta - dt;
        }
        return dt * scale;
    }
}
