package multiteam.scrapcraft.main.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import multiteam.multicore_lib.setup.utilities.MathF;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CookBotScreen extends ContainerScreen<CookBotContainer> {

    private static final int WIDTH = 287;
    private static final int HEIGHT = 239;
    private static final ResourceLocation COOKBOT_GUI = new ResourceLocation(ScrapCraft.MOD_ID, "textures/gui/cookbot_menu.png");
    public static final TranslationTextComponent COOKBOT_LABEL_GUI = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot");
    public static final TranslationTextComponent COOKBOT_LABEL_PIZZABURGER = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.pizza_burger_name");
    public static final TranslationTextComponent COOKBOT_LABEL_VEGGIEBURGER = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.veggie_burger_name");
    public static final TranslationTextComponent COOKBOT_LABEL_REVIVALBAGUETTE = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.revival_baguette_name");
    public static final Vector4f FIELD_1 = new Vector4f(7.0f,85.0f,60.0f,116.0f);
    public static final Vector4f FIELD_2 = new Vector4f(90.0f,168.0f,60.0f,116.0f);
    public static final Vector4f FIELD_3 = new Vector4f(7.0f,168.0f,119.0f,175.0f);
    public static final Vector4f FIELD_CRAFT = new Vector4f(185.0f,283.0f,215.0f,231.0f);
    public static final Vector4f FIELD_COLLECT = new Vector4f(39.0f,137.0f,216.0f,232.0f);
    public int pizzaBurgerTime = 260; //In ticks; real time: 00:13
    public int veggieBurgerTime = 260; //In ticks; real time: 00:13
    public int revivalBaguetteTime = 540; //In ticks; real time: 00:13
    public int selectedFood;
    public ItemStack foodOutput;
    public Vector2f mousePos;
    public CookBotContainer botContainer;
    public boolean canCollect = false;

    public CookBotScreen(CookBotContainer container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, title);
        this.botContainer = container;
        int a = botContainer.cookbot.selectedFood;
        if(a != 0){
            this.selectedFood = a;
        }else{
            this.selectedFood = 1;
        }

        this.imageWidth = WIDTH;
        this.imageHeight = HEIGHT;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.leftPos = 0;
        this.topPos = 0;
    }

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
    public void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        mousePos = new Vector2f(mouseX, mouseY);
        int offsetX = getOffsetX();
        int offsetY = getOffsetY();

        //Background Tint
        RenderSystem.color4f(1f, 1f, 1f, 1f);

        //GUI Background Image
        this.minecraft.textureManager.bind(COOKBOT_GUI);
        this.blit(matrixStack, offsetX, offsetY, 0, 0, WIDTH, HEIGHT, 512, 512); //This last two ints are responsible for texture resolution


        //Texts
        this.font.draw(matrixStack, COOKBOT_LABEL_GUI, (float)65+offsetX, (float)49+offsetY, 16777215);
        //TODO the colors and positions


        //Live stuff
        this.minecraft.textureManager.bind(COOKBOT_GUI);
        switch (this.selectedFood){
            case 1 :
                //Pizza Burger
                selectFied(this.selectedFood, matrixStack, offsetX, offsetY);
                break;
            case 2 :
                //Veggie Burger
                selectFied(this.selectedFood, matrixStack, offsetX, offsetY);
                break;
            case 3 :
                //revival Baguette
                selectFied(this.selectedFood, matrixStack, offsetX, offsetY);
                break;
        }


        //Render Cookbot model - needs rework.
        this.minecraft.getItemRenderer().renderGuiItem(new ItemStack(ModBlocks.COOKBOT_BLOCK.get().asItem()), offsetX, offsetY);


        //Field-Hover and click detection for selecting
        if(isMouseInsideBox(this.mousePos, FIELD_1, offsetX, offsetY)){
            //Pizza Burger
            selectFied(1, matrixStack, offsetX, offsetY);
        }
        if(isMouseInsideBox(this.mousePos, FIELD_2, offsetX, offsetY)){
            //Veggie Burger
            selectFied(2, matrixStack, offsetX, offsetY);
        }
        if(isMouseInsideBox(this.mousePos, FIELD_3, offsetX, offsetY)){
            //revival Baguette
            selectFied(3, matrixStack, offsetX, offsetY);
        }
        if(isMouseInsideBox(this.mousePos, FIELD_CRAFT, offsetX, offsetY)){
            selectFied(4, matrixStack, offsetX, offsetY);
        }

        if(this.botContainer.cookbot.isCooking){
            this.font.draw(matrixStack, "00:"+(this.botContainer.cookbot.cookingProgress/20), (float)79+offsetX, (float)216+offsetY, 16777215);
            this.minecraft.getItemRenderer().renderGuiItem(this.botContainer.cookbot.getOutputItem(this.botContainer.cookbot.selectedFoodToProgress), 80+offsetX, 196+offsetY);
            this.minecraft.textureManager.bind(COOKBOT_GUI);
            this.blit(matrixStack, 66+offsetX, 231+offsetY, this.getBlitOffset(), 304.0F, 247.0F, 49, 4, 512, 512);
            if(this.botContainer.cookbot.selectedFoodToProgress == 1 || this.botContainer.cookbot.selectedFoodToProgress == 2){
                this.blit(matrixStack, 66+offsetX, 231+offsetY, this.getBlitOffset(), 304.0F, 252.0F, ((int) MathF.rescaleValues(0, 260, 0, 49, (260-this.botContainer.cookbot.cookingProgress))), 4, 512, 512);
            }else{
                this.blit(matrixStack, 66+offsetX, 231+offsetY, this.getBlitOffset(), 304.0F, 252.0F, ((int) MathF.rescaleValues(0, 540, 0, 49, (540-this.botContainer.cookbot.cookingProgress))), 4, 512, 512);
            }
        }else{
            if(this.botContainer.cookbot.outputItem != ItemStack.EMPTY){
                canCollect = true;
                this.minecraft.textureManager.bind(COOKBOT_GUI);
                this.blit(matrixStack, 0+offsetX, 178+offsetY, this.getBlitOffset(), 168.0F, 267.0F, 343, 62, 512, 512);

                if(isMouseInsideBox(this.mousePos, FIELD_COLLECT, offsetX, offsetY)){
                    this.blit(matrixStack, 39+offsetX, 216+offsetY, this.getBlitOffset(), 207.0F, 332.0F, 305, 17, 512, 512);
                }
                this.minecraft.getItemRenderer().renderGuiItem(this.botContainer.cookbot.outputItem, 81+offsetX, 196+offsetY);
            }
        }



        this.font.draw(matrixStack, COOKBOT_LABEL_PIZZABURGER, (float)13+offsetX, (float)99+offsetY, 16747607);
        this.font.draw(matrixStack, COOKBOT_LABEL_VEGGIEBURGER, (float)93+offsetX, (float)99+offsetY, 16777151);
        this.font.draw(matrixStack, COOKBOT_LABEL_REVIVALBAGUETTE, (float)42+offsetX, (float)160+offsetY, 12648350);
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

    public void selectFied(int fieldId, MatrixStack matrixStack, int offsetX, int offsetY){
        switch (fieldId){
            case 1 :
                //Pizza Burger
                this.minecraft.textureManager.bind(COOKBOT_GUI);
                this.blit(matrixStack, 7+offsetX, 60+offsetY, this.getBlitOffset(), 2.0F, 245.0F, 80, 57, 512, 512);
                break;
            case 2 :
                //Veggie Burger
                this.minecraft.textureManager.bind(COOKBOT_GUI);
                this.blit(matrixStack, 90+offsetX, 60+offsetY, this.getBlitOffset(), 85.0F, 245.0F, 80, 57, 512, 512);
                break;
            case 3 :
                //revival Baguette
                this.minecraft.textureManager.bind(COOKBOT_GUI);
                this.blit(matrixStack, 7+offsetX, 119+offsetY, this.getBlitOffset(), 2.0F, 304.0F, 162, 57, 512, 512);
                break;
            case 4 :
                this.minecraft.textureManager.bind(COOKBOT_GUI);
                this.blit(matrixStack, 185+offsetX, 215+offsetY, this.getBlitOffset(), 182.0F, 247.0F, 99, 17, 512, 512);
                break;
        }
    }

    public void renderField(Vector4f field, float textureTopLeftX, float textureTopLeftY, int textureTopRightX, int textureHeight, MatrixStack matrixStack, int offsetX, int offsetY){
        this.blit(matrixStack, Float.floatToIntBits(field.x()+offsetX), Float.floatToIntBits(field.y()+offsetY), this.getBlitOffset(), textureTopLeftX, textureTopLeftY, textureTopRightX, textureHeight, 512, 512);
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if(isMouseInsideBox(this.mousePos, FIELD_1, getOffsetX(), getOffsetY())){
            //Pizza Burger
            this.selectedFood = 1;
            this.botContainer.cookbot.selectedFood = selectedFood;
        }else if(isMouseInsideBox(this.mousePos, FIELD_2, getOffsetX(), getOffsetY())){
            //Veggie Burger
            this.selectedFood = 2;
            this.botContainer.cookbot.selectedFood = selectedFood;
        }else if(isMouseInsideBox(this.mousePos, FIELD_3, getOffsetX(), getOffsetY())){
            //revival Baguette
            this.selectedFood = 3;
            this.botContainer.cookbot.selectedFood = selectedFood;
        }else if(isMouseInsideBox(this.mousePos, FIELD_CRAFT, getOffsetX(), getOffsetY())){
            if(this.botContainer.cookbot.outputItem == ItemStack.EMPTY && !this.botContainer.cookbot.isCooking){
                this.botContainer.cookbot.selectedFood = selectedFood;
                switch (this.botContainer.cookbot.selectedFood){
                    case 1:
                        this.botContainer.cookbot.cookingProgress = pizzaBurgerTime;
                        break;
                    case 2:
                        this.botContainer.cookbot.cookingProgress = veggieBurgerTime;
                        break;
                    case 3:
                        this.botContainer.cookbot.cookingProgress = revivalBaguetteTime;
                        break;
                }
                this.botContainer.cookbot.isCooking = true;
            }
        }else if(isMouseInsideBox(this.mousePos, FIELD_COLLECT, getOffsetX(), getOffsetY()) && canCollect){
            this.inventory.add(this.botContainer.cookbot.outputItem);
            canCollect = false;
            this.botContainer.cookbot.outputItem = ItemStack.EMPTY;
            this.botContainer.cookbot.isCooking = false;
        }
        return super.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    public int getOffsetX(){
        return (this.width - WIDTH) / 2;
    }

    public int getOffsetY(){
        return (this.height - HEIGHT) / 2;
    }
}
