package multiteam.scrapcraft.main.uiParticles;

import com.mojang.blaze3d.matrix.MatrixStack;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.event.RenderHUDParticleEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@SuppressWarnings("AccessStaticViaInstance")
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class UIParticle {

    private static final ResourceLocation SOUL_PARTICLE = new ResourceLocation("minecraft", "textures/particle/soul_9.png");

    public static float prevTicks = 0;

    public static class UIP {
        public static int gravityStrength = 1;
        public static int initialX;
        public static int initialY;
        public static int x;
        public static int y;
        public static int lifeTime;

        public UIP(int initX, int initY,int x_, int y_, int grav_, int lifeTime_){
            this.initialX = initX;
            this.initialY = initY;
            this.x = x_;
            this.y = y_;
            this.gravityStrength = grav_;
            this.lifeTime = lifeTime_;
        }
    }

    public static UIP particle = new UIP(20, 20, 20, 20, 1, 50);
    //public static List<UIP> particle; later use...

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event){
        if(!event.getMap().location().equals(AtlasTexture.LOCATION_PARTICLES)){
            return;
        }
        //event.addSprite(SOUL_PARTICLE); no need to stitch this, because it already exists in minecraft
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void renderHudParticles(RenderHUDParticleEvent event){
        event.stack.pushPose();
        event.stack.scale(2f, 2f, 2f);

        prevTicks += event.partialTicks;

        if (shouldSimulate()){
            if(prevTicks > 20){
                prevTicks = 0;
                particle = simulateParticlePhysics(particle);
            }
        }

        renderParticle(event.stack, particle.x, particle.y, SOUL_PARTICLE, 16, 16);

        event.stack.popPose();
    }

    @OnlyIn(Dist.CLIENT)
    public static void renderParticle(MatrixStack matrixStack, int x, int y, ResourceLocation sprite, int spriteWidth, int spriteHeight){
        Minecraft.getInstance().textureManager.bind(sprite);
        AbstractGui.blit(matrixStack, x, y, 0, 0, spriteWidth, spriteHeight, spriteWidth, spriteHeight);
    }

    @OnlyIn(Dist.CLIENT)
    public static UIP simulateParticlePhysics(UIP particle_){
        int nextX = particle_.x;
        int nextY = particle_.y;
        if(hasGravity()){
            nextY = particle_.y + particle_.gravityStrength;
        }

        if(nextY > 500){
            nextY = -10;
        }

        if(particle_.lifeTime <= 0){
            if(respawnParticleAfterLifetimeEnds()){
                nextY = particle_.initialY;
            }else{
                nextY = -1000;
            }
        }

        return new UIP(particle_.initialX, particle_.initialY, nextX, nextY, particle_.gravityStrength, particle_.lifeTime--);
    }

    public static boolean hasGravity(){
        return true;
    }

    public static boolean shouldSimulate(){
        return true;
    }

    public static boolean respawnParticleAfterLifetimeEnds(){
        return true;
    }
}


