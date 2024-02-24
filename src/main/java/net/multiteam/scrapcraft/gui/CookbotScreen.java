package net.multiteam.scrapcraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.multiteam.scrapcraft.ScrapCraft;
import org.joml.Vector2i;
import org.joml.Vector4i;

public class CookbotScreen extends AbstractContainerScreen<CookbotMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ScrapCraft.MODID, "textures/gui/cookbot_gui.png");
    private static final Vector2i TEXTURE_SIZE = new Vector2i(512, 512);
    private static final Vector2i SCREEN_SIZE = new Vector2i(320, 298);

    private static final int COLOR_WHITE = 16777215;

    public CookbotScreen(CookbotMenu menu, Inventory playerInventory, Component titelComponent) {
        super(menu, playerInventory, titelComponent);
    }

    @Override
    protected void init() {
        super.init();

        this.imageWidth = TEXTURE_SIZE.x;
        this.imageHeight = TEXTURE_SIZE.y;

        int nowhere = 971234876;
        this.inventoryLabelX = nowhere;
        this.inventoryLabelY = nowhere;
        this.titleLabelX = nowhere;
        this.titleLabelY = nowhere;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        Vector2i pivot = new Vector2i((width/2) - (SCREEN_SIZE.x/2), (height/2) - (SCREEN_SIZE.y/2));

        //320 x 298

        renderTexturedPanel(guiGraphics, TEXTURE, pivot.x, pivot.y, new Vector4i(0, 0, SCREEN_SIZE.x, SCREEN_SIZE.y), TEXTURE_SIZE);

        guiGraphics.drawString(this.font, Component.translatable("gui.cookbot.title"), pivot.x+68, pivot.y+60, COLOR_WHITE, true);
    }

    private void renderTexturedPanel(GuiGraphics guiGraphics,ResourceLocation texture, int pivotX, int pivotY, Vector4i area, Vector2i textureSize){
        guiGraphics.blit(texture, pivotX, pivotY, area.x, area.y, area.z, area.w, textureSize.x, textureSize.y);
    }
}
