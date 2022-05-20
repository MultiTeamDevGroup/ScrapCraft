package multiteam.scrapcraft.main.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    private static final ResourceLocation COOKBOT_GUI = new ResourceLocation(ScrapCraft.MOD_ID, "textures/gui/cookbot_menu.png");
    private static final ResourceLocation SOUL_PARTICLE = new ResourceLocation("minecraft", "textures/particle/soul_9.png");

    @SubscribeEvent
    public static void renderGameOverlayEvent(RenderGameOverlayEvent.Post event) {
        MatrixStack matrixStack = event.getMatrixStack();
        Minecraft mc = Minecraft.getInstance();


        MinecraftForge.EVENT_BUS.post(new RenderHUDParticleEvent(matrixStack, event.getPartialTicks(), event.getWindow()));
        //Objects.requireNonNull(mc).textureManager.bind(COOKBOT_GUI);
        //System.out.println("partial ticks: " + event.getPartialTicks() + "; sin: " + Math.sin(event.getPartialTicks()) + "; times 2: " + (Math.sin(event.getPartialTicks()))*10 + "; to int: " + (int)Math.round((Math.sin(event.getPartialTicks()))*10));
        //UIParticle collectButton = new UIParticle(COOKBOT_GUI, 512, 512, new Vector4f(207.0F, 332.0F, 305, 17));
        //renderParticle(matrixStack, 0, (int)Math.round((Math.sin(event.getPartialTicks()))*10), collectButton);

        RenderSystem.pushMatrix();
        //mc.font.drawShadow(matrixStack, "debugPage: ", 12f, 16f, 0xffaa00); //swithcing to RenderGameOverlayEvent.Post solved rendering text making the screen go black
        RenderSystem.popMatrix();

        /**RenderSystem.pushMatrix();
         FontRenderer fontrenderer = mc.gui.getFont();
         mc.getProfiler().push("randomguitext");
         RenderSystem.enableBlend();
         RenderSystem.enableAlphaTest();
         fontrenderer.draw(matrixStack, "This text is rendered by some magic. but why is the game world not visible???", (float)10, (float)40, 16777215); //this makes the screen go black
         RenderSystem.defaultBlendFunc();
         mc.getProfiler().pop();
         RenderSystem.popMatrix();**/

        /**for (int i = 0; i == Registration.UIPARTICLES.getEntries().size(); i++){
         matrixStack.pushPose();

         UIParticle particleToRender = Registry.ITEM.;
         Objects.requireNonNull(mc).textureManager.bind(particleToRender.getTexture());

         matrixStack.scale(2f, 2f, 2f);
         renderParticle(matrixStack, ((event.getWindow().getGuiScaledWidth()/2)-32)/2, ((event.getWindow().getGuiScaledHeight()/2)-32)/2, particleToRender);

         matrixStack.popPose();
         }**/
    }

    @SubscribeEvent
    public static void PlayerRenderTickEvent(TickEvent.PlayerTickEvent event) {

        if (event.player.getMainHandItem().getItem() == ModItems.CONNECT_TOOL.get()) {

            MinecraftForge.EVENT_BUS.post(new EnableConnectPointsEvent(true, event.player.level));
        } else {
            MinecraftForge.EVENT_BUS.post(new EnableConnectPointsEvent(false, event.player.level));
        }

    }

}
