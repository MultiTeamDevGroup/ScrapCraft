package multiteam.scrapcraft.main.client.rendering;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.lwjgl.opengl.GL11;

import java.util.OptionalDouble;

public class ModRenderTypes extends RenderType {

    //This class should go to multicorelib

    public ModRenderTypes(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
        super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
    }

    private static final LineState THICK_LINES = new LineState(OptionalDouble.of(3.0D));

    public static final RenderType OVERLAY_BLOCK = RenderType.create("overlay_block", DefaultVertexFormats.BLOCK, 7, 262144, true, true,  RenderType.State.builder().setShadeModelState(SMOOTH_SHADE).setLightmapState(LIGHTMAP).setTextureState(BLOCK_SHEET_MIPPED).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setOutputState(TRANSLUCENT_TARGET).setDepthTestState(NO_DEPTH_TEST).setCullState(NO_CULL).setWriteMaskState(RenderState.COLOR_WRITE).createCompositeState(true));
    public static final RenderType FULLBRIGHT_BLOCK = RenderType.create("fullbright_block", DefaultVertexFormats.BLOCK, 7, 262144, true, true,  RenderType.State.builder().setShadeModelState(SMOOTH_SHADE).setLightmapState(NO_LIGHTMAP).setTextureState(BLOCK_SHEET_MIPPED).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setOutputState(TRANSLUCENT_TARGET).createCompositeState(true));
    public static final RenderType CONNECTION_POINT = RenderType.create("connection_point", DefaultVertexFormats.POSITION_COLOR, 7, 262144, true, false, RenderType.State.builder().setLayeringState(VIEW_OFFSET_Z_LAYERING).setShadeModelState(SMOOTH_SHADE).setLightmapState(NO_LIGHTMAP).setOutputState(TRANSLUCENT_TARGET).setDepthTestState(RenderState.NO_DEPTH_TEST).setCullState(NO_CULL).setWriteMaskState(RenderState.COLOR_WRITE).createCompositeState(true));

}
