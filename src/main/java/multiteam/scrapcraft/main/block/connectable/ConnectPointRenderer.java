package multiteam.scrapcraft.main.block.connectable;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.multicore_lib.setup.utilities.MathF;
import multiteam.scrapcraft.main.client.rendering.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ConnectPointRenderer {

    public static Vector3f blockCenter = new Vector3f(-MathF.BlockToFloatScale(2.5f), MathF.BlockToFloatScale(5.5f), -MathF.BlockToFloatScale(2.5f));

    public static void renderConnectPoint(ConnectableTileEntity tile, ResourceLocation tex, MatrixStack matrixStack, IRenderTypeBuffer buffer, Vector3f offset) {

        if(tile.getConnectPointState()){

            matrixStack.pushPose();

            IVertexBuilder builder = buffer.getBuffer(ModRenderTypes.CONNECTION_POINT);

            // bruh this is how you face the player matrixStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());

            buildCube(builder, matrixStack, tile.connectionPointColor, MathF.BlockToFloatScaleVector3f(5,5,5), blockCenter);

            /**ITextComponent component = new StringTextComponent("asdasd");
            FontRenderer fontrenderer = Minecraft.getInstance().font;
            float f2 = (float)(-fontrenderer.width(component) / 2);
            int i = "deadmau5".equals(component.getString()) ? -10 : 0;
            Matrix4f matrix4f = matrixStack.last().pose();
            float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int j = (int)(f1 * 255.0F) << 24;
            fontrenderer.drawInBatch(component, f2, (float)i, 553648127, false, matrix4f, buffer, true, j, 1);**/



            if(tile.connectInfo.connections != null){
                for (ConnectableTileEntity.ConnectHolder holder : tile.connectInfo.connections) {
                    Vector3f connectToPos = new Vector3f(holder.position.getX(), holder.position.getY(), holder.position.getZ());
                    Vector3f connectFromPos = new Vector3f(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ());
                    buildPlane(
                            builder,
                            matrixStack,
                            tile.connectionPointColor,
                            new Vector3f(connectToPos.x(), connectToPos.y(), connectToPos.z()),
                            new Vector3f(connectToPos.x()+1, connectToPos.y()+1, connectToPos.z()+1),
                            new Vector3f(connectFromPos.x(), connectFromPos.y(), connectFromPos.z()),
                            new Vector3f(connectFromPos.x()+1, connectFromPos.y()+1, connectFromPos.z()+1)
                    );
                }
            }


            builder = buffer.getBuffer(RenderType.entityTranslucent(tex));

            matrixStack.popPose();
        }

    }

    //Gonna go to MultiCoreLib
    public static float Vector3fDistance(Vector3f pointA, Vector3f pointB) {
        float num1 = pointA.x() - pointB.x();
        float num2 = pointA.y() - pointB.y();
        float num3 = pointA.z() - pointB.z();
        return (float) Math.sqrt((double) num1 * (double) num1 + (double) num2 * (double) num2 + (double) num3 * (double) num3);
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
