package multiteam.scrapcraft.main.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.scrapcraft.ScrapCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.swing.plaf.basic.BasicListUI;
import java.util.Vector;

@OnlyIn(Dist.CLIENT)
public class CookBotScreen extends ContainerScreen<CookBotContainer> {

    private static final int WIDTH = 287;
    private static final int HEIGHT = 239;
    private static final ResourceLocation COOKBOT_GUI = new ResourceLocation(ScrapCraft.MOD_ID, "textures/gui/cookbot_menu.png");
    public static final TranslationTextComponent COOKBOT_LABEL = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot");
    public static final Vector4f FIELD_1 = new Vector4f(7.0f,85.0f,60.0f,116.0f);
    public static int selectedFood = 1;

    public CookBotScreen(CookBotContainer container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, title);
        this.imageWidth = WIDTH;
        this.imageHeight = HEIGHT;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.leftPos = 0;
        this.topPos = 0;
    }

    /**@Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        addButton(new Button(relX + 10, relY + 118, 160, 20, new StringTextComponent("Chicken"), button -> codeThatsRunByButtonPress("minecraft:chicken")));

    }**/

    private void codeThatsRunByButtonPress(String id) {
        System.out.println(id);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.renderLabels(matrixStack, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {

    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        Vector2f mousePos = new Vector2f(mouseX, mouseY);
        int offsetX = (this.width - WIDTH) / 2;
        int offsetY = (this.height - HEIGHT) / 2;

        //Background Tint
        RenderSystem.color4f(1f, 1f, 1f, 1f);

        //GUI Background Image
        this.minecraft.textureManager.bind(COOKBOT_GUI);
        this.blit(matrixStack, offsetX, offsetY, 0, 0, WIDTH, HEIGHT, 512, 512); //This last two ints are responsible for texture resolution

        //Texts
        this.font.draw(matrixStack, COOKBOT_LABEL, (float)65+offsetX, (float)49+offsetY, 16777215);

        //Live stuff
        this.minecraft.textureManager.bind(COOKBOT_GUI);
        switch (selectedFood){
            case 1 :
                this.blit(matrixStack, 7+offsetX, 60+offsetY, this.getBlitOffset(), 2.0F, 245.0F, 80, 57, 512, 512);
                break;
            case 2 :
                this.blit(matrixStack, 90+offsetX, 60+offsetY, this.getBlitOffset(), 85.0F, 245.0F, 163, 57, 512, 512);
                break;
            case 3 :
                this.blit(matrixStack, 7+offsetX, 119+offsetY, this.getBlitOffset(), 2.0F, 304.0F, 163, 57, 512, 512);
                break;
        }

        if(isMouseInsideBox(mousePos, FIELD_1, offsetX, offsetY)){

        }

        System.out.println(mouseClicked(0,0,0));
        System.out.println(mouseClicked(1,1,1));
        System.out.println(mouseClicked(2,2,2));
        System.out.println(mouseClicked(3,3,3));


    }

    public boolean isMouseInsideBox(int mouseX, int mouseY, int minX, int maxX, int minY, int maxY){
        if(mouseX > minX && mouseX < maxX){
            if(mouseY > minY && mouseY < maxY){
                return true;
            }
        }
        return false;
    }

    //This should go into MultiCoreLib...
    public boolean isMouseInsideBox(Vector2f mousPos, Vector4f box, int offsetX, int offsetY){
        if(mousPos.x > (offsetX+box.x()) && mousPos.x < (offsetX+box.y())){
            if(mousPos.y > (offsetY+box.z()) && mousPos.y < (offsetY+box.w()) ){
                return true;
            }
        }
        return false;
    }
}
