package multiteam.scrapcraft.main.block.connectable.seat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.multicore_lib.setup.utilities.MathF;
import multiteam.scrapcraft.main.client.rendering.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

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

                IVertexBuilder builder = buffer.getBuffer(ModRenderTypes.CONNECTION_POINT);
                TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(new ResourceLocation("minecraft", "block/cobblestone"));

                //makes the quad rotate and rise/get lowered in space and expand/shrink somewhat like a dropped item is animated
                /**long time = System.currentTimeMillis();
                double speed = 2d;
                float angle = (time / (int)speed) % 360;
                Quaternion rotation = Vector3f.YP.rotationDegrees(angle);
                Random rnd = new Random(seatTile.getBlockPos().getX() * 337L + seatTile.getBlockPos().getY() * 37L + seatTile.getBlockPos().getZ() * 13L);
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

                matrixStack.popPose();**/


                matrixStack.pushPose();

                buildCube(builder, matrixStack, seatTile.connectionPointColor, MathF.BlockToFloatScaleVector3f(6,6,6), MathF.BlockToFloatScaleVector3f(5,16,5));
                buildCube(builder, matrixStack, seatTile.connectionPointColor, MathF.BlockToFloatScaleVector3f(5,5,5), MathF.BlockToFloatScaleVector3f(5.5f,5.5f,5.5f));

                matrixStack.popPose();
            }


        }

    }

    private void buildCube(IVertexBuilder builder, MatrixStack matrixStack, Vector3f color, Vector3f size, Vector3f offset){

        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0));
        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0+ size.z()), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0));

        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0+size.z()));
        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0+ size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0));

        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0));
        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0));

    }

    private void buildPlane(IVertexBuilder builder, MatrixStack matrixStack, Vector3f color, Vector3f corner1, Vector3f corner2,Vector3f corner3, Vector3f corner4){
        add(builder, matrixStack, corner1.x(), corner1.y(), corner1.z(), color);
        add(builder, matrixStack, corner2.x(), corner2.y(), corner2.z(), color);
        add(builder, matrixStack, corner3.x(), corner3.y(), corner3.z(), color);
        add(builder, matrixStack, corner4.x(), corner4.y(), corner4.z(), color);
    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, Vector3f color) {
        renderer.vertex(stack.last().pose(), x, y, z)
                .color(color.x(), color.y(), color.z(), 1.0f)
                .endVertex();
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
