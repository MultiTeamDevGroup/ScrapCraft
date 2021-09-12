package multiteam.scrapcraft.main.block.connectable;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.multicore_lib.setup.utilities.MathF;
import multiteam.scrapcraft.main.client.rendering.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class ConnectPointRenderer {

    public static void render(ConnectableTileEntity tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {

        if(tile.getConnectPointState()){

            IVertexBuilder builder = buffer.getBuffer(ModRenderTypes.CONNECTION_POINT);
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(new ResourceLocation("minecraft", "block/cobblestone"));

            matrixStack.pushPose();

            buildCube(builder, matrixStack, tile.connectionPointColor, MathF.BlockToFloatScaleVector3f(5,5,5), MathF.BlockToFloatScaleVector3f(5.5f,5.5f,5.5f));

            matrixStack.popPose();
        }
    }

    private static void buildCube(IVertexBuilder builder, MatrixStack matrixStack, Vector3f color, Vector3f size, Vector3f offset){

        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0));
        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0+ size.z()), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0));

        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0+size.z()));
        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0+ size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0));

        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0+size.y(), offset.z()+0));
        buildPlane(builder, matrixStack, color, new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0), new Vector3f(offset.x()+0, offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0+size.z()), new Vector3f(offset.x()+0+size.x(), offset.y()+0, offset.z()+0));

    }

    private static void buildPlane(IVertexBuilder builder, MatrixStack matrixStack, Vector3f color, Vector3f corner1, Vector3f corner2,Vector3f corner3, Vector3f corner4){
        add(builder, matrixStack, corner1.x(), corner1.y(), corner1.z(), color);
        add(builder, matrixStack, corner2.x(), corner2.y(), corner2.z(), color);
        add(builder, matrixStack, corner3.x(), corner3.y(), corner3.z(), color);
        add(builder, matrixStack, corner4.x(), corner4.y(), corner4.z(), color);
    }

    private static void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, Vector3f color) {
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

}
