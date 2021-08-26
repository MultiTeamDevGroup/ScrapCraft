package multiteam.scrapcraft.main.uiParticles;

import com.mojang.blaze3d.matrix.MatrixStack;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.event.RenderHUDParticleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class UIParticle {

    @SubscribeEvent
    public static void renderParticle(RenderHUDParticleEvent event){

    }

    /**public static void renderParticle(MatrixStack matrixStack, int x, int y, UIParticle uiParticle){

        AbstractGui.blit(matrixStack, x, y, 0, uiParticle.getField().x(), uiParticle.getField().y(), (int)uiParticle.getField().z(), (int)uiParticle.getField().w(), uiParticle.getTextureWidth(), uiParticle.getTextureHeight());

        blit(matrixStack, 39+offsetX, 216+offsetY, this.getBlitOffset(), 207.0F, 332.0F, 305, 17, 512, 512);
        blit(matrixStack, x, y, 0, field.x, field.y, field.z, field.w, width, height);
        renderParticle(matrixStack, 0, 0, new UIParticle(COOKBOT_GUI, 512, 512, new Vector4f(207.0F, 332.0F, 305, 17)));
        renderParticle(matrixStack, x, y, new UIParticle(ResourceLocation, width, height, new Vector4f(field.x, field.y, field.z, field.w)));
    }**/
}
