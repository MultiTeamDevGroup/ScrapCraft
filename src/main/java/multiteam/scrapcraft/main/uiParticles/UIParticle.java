package multiteam.scrapcraft.main.uiParticles;

import com.mojang.blaze3d.matrix.MatrixStack;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.event.RenderHUDParticleEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class UIParticle {

    private static final ResourceLocation SOUL_PARTICLE = new ResourceLocation("minecraft", "textures/particle/soul_9.png");

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void renderHudParticle(RenderHUDParticleEvent event){
        renderParticle(event.stack, 10, 10, SOUL_PARTICLE);
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderParticle(MatrixStack matrixStack, int x, int y, ResourceLocation sprite){

        Minecraft.getInstance().textureManager.bind(sprite);
        AbstractGui.blit(matrixStack, x, y, 0, 0, 16, 16, 16, 16);
        //AbstractGui.blit(matrixStack, 39, 216, 0, 207.0F, 332.0F, 305, 17, 512, 512);
        //AbstractGui.blit(matrixStack, x, y, 0, field.x, field.y, field.z, field.w, width, height);
    }
}
