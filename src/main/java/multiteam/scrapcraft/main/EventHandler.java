package multiteam.scrapcraft.main;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.uiParticles.UIParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    private static final ResourceLocation COOKBOT_GUI = new ResourceLocation(ScrapCraft.MOD_ID, "textures/gui/cookbot_menu.png");

    @SubscribeEvent
    public static void renderGameUIParticle(RenderGameOverlayEvent event){
        MatrixStack matrixStack = event.getMatrixStack();
        Minecraft mc = Minecraft.getInstance();
        Objects.requireNonNull(mc).textureManager.bind(COOKBOT_GUI);
        System.out.println("partial ticks: " + event.getPartialTicks() + "; sin: " + Math.sin(event.getPartialTicks()) + "; times 2: " + (Math.sin(event.getPartialTicks()))*10 + "; to int: " + (int)(Math.sin(event.getPartialTicks()))*10);
        renderParticle(matrixStack, 0, (int)(Math.sin(event.getPartialTicks()))*10, new UIParticle(COOKBOT_GUI, 512, 512, new Vector4f(207.0F, 332.0F, 305, 17)));
        //this.font.draw(matrixStack, "WE ARE RENDERING TEXT ON SCREEEEEEN LESS GOOOO", (float)0, (float)0, 16777215); how the fuck do i write text bruh
    }

    public static void renderParticle(MatrixStack matrixStack, int x, int y, UIParticle uiParticle){
        //207.0F, 332.0F, 305, 17
        AbstractGui.blit(matrixStack, x, y, 0, uiParticle.getField().x(), uiParticle.getField().y(), (int)uiParticle.getField().z(), (int)uiParticle.getField().w(), uiParticle.getTextureWidth(), uiParticle.getTextureHeight());

        //blit(matrixStack, 39+offsetX, 216+offsetY, this.getBlitOffset(), 207.0F, 332.0F, 305, 17, 512, 512);
        //blit(matrixStack, x, y, 0, field.x, field.y, field.z, field.w, width, height);
        //renderParticle(matrixStack, 0, 0, new UIParticle(COOKBOT_GUI, 512, 512, new Vector4f(207.0F, 332.0F, 305, 17)));
        //renderParticle(matrixStack, x, y, new UIParticle(ResourceLocation, width, height, new Vector4f(field.x, field.y, field.z, field.w)));
    }

}
