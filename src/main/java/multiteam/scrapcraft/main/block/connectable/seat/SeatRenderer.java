package multiteam.scrapcraft.main.block.connectable.seat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import multiteam.scrapcraft.main.block.connectable.ConnectPointRenderer;
import multiteam.scrapcraft.main.block.connectable.ConnectableTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
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
    public void renderLate(SeatTileEntity animatable, MatrixStack stackIn, float ticks, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {

        if(animatable instanceof ConnectableTileEntity){
            ConnectPointRenderer.renderConnectPoint(animatable, getTextureLocation(animatable), stackIn, renderTypeBuffer, new Vector3f(5.5f,5.5f,5.5f));
        }
    }

}
