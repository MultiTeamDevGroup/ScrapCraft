package multiteam.scrapcraft.main;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
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
        AbstractGui.blit(matrixStack, 39, 216, 0, 207.0F, 332.0F, 305, 17, 512, 512);
        //this.font.draw(matrixStack, "WE ARE RENDERING TEXT ON SCREEEEEEN LESS GOOOO", (float)0, (float)0, 16777215);
    }

}
