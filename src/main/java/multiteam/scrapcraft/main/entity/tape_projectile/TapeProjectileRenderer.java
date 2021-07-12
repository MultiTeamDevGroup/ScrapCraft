package multiteam.scrapcraft.main.entity.tape_projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class TapeProjectileRenderer extends GeoProjectilesRenderer<TapeProjectileEntity> {

    public TapeProjectileRenderer(EntityRendererManager renderManager) {
        super(renderManager, new TapeProjectileModel());
    }

    @Override
    public RenderType getRenderType(TapeProjectileEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

}
