package multiteam.scrapcraft.main.client.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mcp.MethodsReturnNonnullByDefault;
import multiteam.multicore_lib.setup.utilities.MathF;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.item.ModItems;
import multiteam.scrapcraft.main.network.Networking;
import multiteam.scrapcraft.main.network.packets.CookbotCommsPacket;
import multiteam.scrapcraft.main.network.packets.CookbotGiveResultsPacket;
import multiteam.scrapcraft.main.network.packets.CookbotRemoveIngredientsPacket;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@OnlyIn(Dist.CLIENT)
public class CookBotScreen extends ContainerScreen<CookBotContainer> {

    private static final int WIDTH = 287;
    private static final int HEIGHT = 239;
    private static final ResourceLocation COOKBOT_GUI = new ResourceLocation(ScrapCraft.MOD_ID, "textures/gui/cookbot_menu.png");
    public static final TranslationTextComponent COOKBOT_LABEL_GUI = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot");
    public static final TranslationTextComponent COOKBOT_LABEL_PIZZABURGER = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.pizza_burger_name");
    public static final TranslationTextComponent COOKBOT_LABEL_VEGGIEBURGER = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.veggie_burger_name");
    public static final TranslationTextComponent COOKBOT_LABEL_REVIVALBAGUETTE = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.revival_baguette_name");
    public static final TranslationTextComponent COOKBOT_LABEL_DESCRIPTION_LABEL = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.description_label");
    public static final TranslationTextComponent COOKBOT_LABEL_INGREDIENTS_LABEL = new TranslationTextComponent("container."+ ScrapCraft.MOD_ID +".cookbot.ingredients_label");
    public static final Vector4f FIELD_1 = new Vector4f(7.0f,85.0f,60.0f,116.0f);
    public static final Vector4f FIELD_2 = new Vector4f(90.0f,168.0f,60.0f,116.0f);
    public static final Vector4f FIELD_3 = new Vector4f(7.0f,168.0f,119.0f,175.0f);
    public static final Vector4f FIELD_CRAFT = new Vector4f(185.0f,283.0f,215.0f,231.0f);
    public static final Vector4f FIELD_COLLECT = new Vector4f(39.0f,137.0f,216.0f,232.0f);
    public int pizzaBurgerTime = 260; //In ticks; real time: 00:13
    public int veggieBurgerTime = 260; //In ticks; real time: 00:13
    public int revivalBaguetteTime = 540; //In ticks; real time: 00: uhh i forgot what was it in seconds lmao
    public ItemStack[] pizzaBurgerRecipe = {new ItemStack(ModItems.WOC_MEAT.get()), new ItemStack(Items.MILK_BUCKET), new ItemStack(Items.POTATO), new ItemStack(ModItems.TOMATO.get())};
    public int[] pizzaBurgerRecipeCounts = {1,2,2,1};
    public ItemStack[] veggieBurgerRecipe = {new ItemStack(Items.CARROT), new ItemStack(Items.BEETROOT), new ItemStack(Items.POTATO), new ItemStack(ModItems.TOMATO.get())};
    public int[] veggieBurgerRecipeCounts = {2,1,1,1};
    public ItemStack[] revivalBaguetteRecipe = {new ItemStack(ModItems.WOC_MEAT.get()), new ItemStack(Items.MILK_BUCKET), new ItemStack(Items.BEETROOT), new ItemStack(ModItems.TOMATO.get())};
    public int[] revivalBaguetteRecipeCounts = {1,2,1,1};
    @SuppressWarnings("unused")
    public ItemStack[] selectedFoodRecipe;
    public int selectedFood;
    @SuppressWarnings("unused")
    public ItemStack foodOutput;
    public Vector2f mousePos;
    public CookBotContainer botContainer;
    public boolean canCollect = false;

    public CookBotScreen(CookBotContainer container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, title);
        this.botContainer = container;

        int a = botContainer.cookbot.selectedFood;
        if(a == 1 || a == 2 || a == 3){
            this.selectedFood = a;
        }else{
            this.selectedFood = 1;
            Networking.sendToServer(new CookbotCommsPacket(1, this.botContainer.cookbot.cookingProgress, false, ItemStack.EMPTY, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
        }

        this.imageWidth = WIDTH;
        this.imageHeight = HEIGHT;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.leftPos = 0;
        this.topPos = 0;
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
        Objects.requireNonNull(this.minecraft).textureManager.bind(COOKBOT_GUI);
        blit(matrixStack, offsetX, offsetY, 0, 0, WIDTH, HEIGHT, 512, 512); //This last two ints are responsible for texture resolution


        //Live stuff
        this.minecraft.textureManager.bind(COOKBOT_GUI);
        switch (this.selectedFood){
            case 1 :
                //Pizza Burger
                selectField(this.selectedFood, matrixStack, offsetX, offsetY);
                renderIngredients(pizzaBurgerRecipe, pizzaBurgerRecipeCounts, offsetX, offsetY, matrixStack);
                break;
            case 2 :
                //Veggie Burger
                selectField(this.selectedFood, matrixStack, offsetX, offsetY);
                renderIngredients(veggieBurgerRecipe, veggieBurgerRecipeCounts, offsetX, offsetY, matrixStack);
                break;
            case 3 :
                //revival Baguette
                selectField(this.selectedFood, matrixStack, offsetX, offsetY);
                renderIngredients(revivalBaguetteRecipe, revivalBaguetteRecipeCounts, offsetX, offsetY, matrixStack);
                break;
        }


        //Render Cookbot model - needs rework.
        //this.minecraft.getItemRenderer().renderGuiItem(new ItemStack(ModBlocks.COOKBOT_BLOCK.get().asItem()), offsetX, offsetY);
        //this.minecraft.getEntityRenderDispatcher().render();
        //this.minecraft.getBlockRenderer().renderBlock(ModBlocks.COOKBOT_BLOCK.get().defaultBlockState(), matrixStack, ,offsetX, offsetY, ); //where rendertypebuffer?; where IModelData?
        //this.minecraft.getBlockRenderer().getBlockModel();
        this.minecraft.getBlockRenderer().getBlockModel(ModBlocks.ARROW_LIGHT_BLOCK.get().defaultBlockState());

        //Field-Hover and click detection for selecting
        if(isMouseInsideBox(this.mousePos, FIELD_1, offsetX, offsetY)){
            //Pizza Burger
            selectField(1, matrixStack, offsetX, offsetY);
        }
        if(isMouseInsideBox(this.mousePos, FIELD_2, offsetX, offsetY)){
            //Veggie Burger
            selectField(2, matrixStack, offsetX, offsetY);
        }
        if(isMouseInsideBox(this.mousePos, FIELD_3, offsetX, offsetY)){
            //revival Baguette
            selectField(3, matrixStack, offsetX, offsetY);
        }
        if(isMouseInsideBox(this.mousePos, FIELD_CRAFT, offsetX, offsetY)){
            selectField(4, matrixStack, offsetX, offsetY);
        }

        if(this.botContainer.cookbot.isCooking){
            this.font.draw(matrixStack, getDrawnCookingTime(this.botContainer.cookbot.cookingProgress), (float)77+offsetX, (float)216+offsetY, 16777215);
            this.minecraft.getItemRenderer().renderGuiItem(this.botContainer.cookbot.getOutputItem(this.botContainer.cookbot.selectedFoodToProgress), 80+offsetX, 196+offsetY);
            this.minecraft.textureManager.bind(COOKBOT_GUI);
            blit(matrixStack, 66+offsetX, 231+offsetY, this.getBlitOffset(), 304.0F, 247.0F, 49, 4, 512, 512);
            if(this.botContainer.cookbot.selectedFoodToProgress == 1 || this.botContainer.cookbot.selectedFoodToProgress == 2){
                blit(matrixStack, 66+offsetX, 231+offsetY, this.getBlitOffset(), 304.0F, 252.0F, ((int) MathF.rescaleValues(0, 260, 0, 49, (260-this.botContainer.cookbot.cookingProgress))), 4, 512, 512);
            }else{
                blit(matrixStack, 66+offsetX, 231+offsetY, this.getBlitOffset(), 304.0F, 252.0F, ((int) MathF.rescaleValues(0, 540, 0, 49, (540-this.botContainer.cookbot.cookingProgress))), 4, 512, 512);
            }
        }else{
            if(this.botContainer.cookbot.outputItem != ItemStack.EMPTY){
                canCollect = true;
                this.minecraft.textureManager.bind(COOKBOT_GUI);
                blit(matrixStack, offsetX, 178+offsetY, this.getBlitOffset(), 168.0F, 267.0F, 343, 62, 512, 512);

                if(isMouseInsideBox(this.mousePos, FIELD_COLLECT, offsetX, offsetY)){
                    blit(matrixStack, 39+offsetX, 216+offsetY, this.getBlitOffset(), 207.0F, 332.0F, 305, 17, 512, 512);
                }
                this.minecraft.getItemRenderer().renderGuiItem(this.botContainer.cookbot.outputItem, 81+offsetX, 196+offsetY);

            }
        }


        //Texts
        //16777215 is white
        this.font.draw(matrixStack, COOKBOT_LABEL_GUI, (float)56+offsetX, (float)49+offsetY, 16777215);
        this.font.draw(matrixStack, COOKBOT_LABEL_PIZZABURGER, (float)13+offsetX, (float)99+offsetY, 16747607);
        this.font.draw(matrixStack, COOKBOT_LABEL_VEGGIEBURGER, (float)93+offsetX, (float)99+offsetY, 16777151);
        this.font.draw(matrixStack, COOKBOT_LABEL_REVIVALBAGUETTE, (float)42+offsetX, (float)160+offsetY, 12648350);
        this.font.draw(matrixStack, COOKBOT_LABEL_DESCRIPTION_LABEL, (float)185+offsetX, (float)86+offsetY, 16777215);
        this.font.draw(matrixStack, COOKBOT_LABEL_INGREDIENTS_LABEL, (float)185+offsetX, (float)172+offsetY, 16777215);
        this.font.draw(matrixStack, getDrawnCookingTimeForCurrent(), (float)258+offsetX, (float)172+offsetY, 16766025); //FFD449 - gold

    }


    //This should go into MultiCoreLib...
    public boolean isMouseInsideBox(Vector2f mousePos, Vector4f box, int offsetX, int offsetY){
        if(mousePos.x > (offsetX+box.x()) && mousePos.x < (offsetX+box.y())){
            return mousePos.y > (offsetY + box.z()) && mousePos.y < (offsetY + box.w());
        }
        return false;
    }

    //This also should go into MultiCoreLib...
    public int getCountOfItemInInventory(PlayerInventory inv, Item itemToCount){
        int count = 0;
        for (int i = 0; i < inv.getContainerSize(); i++){
            ItemStack currentStack = inv.getItem(i);
            if(currentStack.getItem() == itemToCount){
                count+=currentStack.getCount();
            }
        }
        return count;
    }

    public void selectField(int fieldId, MatrixStack matrixStack, int offsetX, int offsetY){
        switch (fieldId){
            case 1 :
                //Pizza Burger
                Objects.requireNonNull(this.minecraft).textureManager.bind(COOKBOT_GUI);
                blit(matrixStack, 7+offsetX, 60+offsetY, this.getBlitOffset(), 2.0F, 245.0F, 80, 57, 512, 512);
                break;
            case 2 :
                //Veggie Burger
                Objects.requireNonNull(this.minecraft).textureManager.bind(COOKBOT_GUI);
                blit(matrixStack, 90+offsetX, 60+offsetY, this.getBlitOffset(), 85.0F, 245.0F, 80, 57, 512, 512);
                break;
            case 3 :
                //revival Baguette
                Objects.requireNonNull(this.minecraft).textureManager.bind(COOKBOT_GUI);
                blit(matrixStack, 7+offsetX, 119+offsetY, this.getBlitOffset(), 2.0F, 304.0F, 162, 57, 512, 512);
                break;
            case 4 :
                Objects.requireNonNull(this.minecraft).textureManager.bind(COOKBOT_GUI);
                blit(matrixStack, 185+offsetX, 215+offsetY, this.getBlitOffset(), 182.0F, 247.0F, 99, 17, 512, 512);
                break;
        }
    }

    @SuppressWarnings("unused")
    public void renderField(Vector4f field, float textureTopLeftX, float textureTopLeftY, int textureTopRightX, int textureHeight, MatrixStack matrixStack, int offsetX, int offsetY){
        blit(matrixStack, Float.floatToIntBits(field.x()+offsetX), Float.floatToIntBits(field.y()+offsetY), this.getBlitOffset(), textureTopLeftX, textureTopLeftY, textureTopRightX, textureHeight, 512, 512);
    }

    public void renderIngredients(ItemStack[] recipeArray, int[] recipeCounts, int offsetX, int offsetY, MatrixStack matrixStack){
        for (int i = 0; i < recipeArray.length; i++){
            ItemStack dispStack = recipeArray[i];
            this.minecraft.getItemRenderer().renderGuiItem(dispStack, 184+offsetX+(27*i), 182+offsetY);
            if(!this.inventory.player.isCreative()){
                int color = 16777215; //default white
                int a = 0;
                if(this.inventory.player.inventory.contains(dispStack)){
                    a = getCountOfItemInInventory(this.inventory.player.inventory, dispStack.getItem());
                }
                if(a >= recipeCounts[i]){
                    color = 13628459; //CFF42B
                }else {
                    color = 16001835; //F42B2B
                }
                matrixStack.pushPose();
                matrixStack.scale(0.5f,0.5f,0.5f);
                this.font.draw(matrixStack, a+"/"+recipeCounts[i], (float)(187+offsetX+(27*i))*2, (float)(200+offsetY)*2, color);
                matrixStack.popPose();
            }else{
                matrixStack.pushPose();
                matrixStack.scale(0.5f,0.5f,0.5f);
                this.font.draw(matrixStack, "*/"+recipeCounts[i], (float)(187+offsetX+(27*i))*2, (float)(200+offsetY)*2, 13628459);
                matrixStack.popPose();
            }
        }
    }

    public String getDrawnCookingTime(int cookingTime){
        int time = (cookingTime/20);
        String drawnTime = "";
        if(time < 10){
            drawnTime = "00:0"+time;
        }else{
            drawnTime = "00:"+time;
        }
        return drawnTime;
    }

    public String getDrawnCookingTimeForCurrent(){
        String drawnTime = "";
        switch (this.selectedFood){
            case 1:
                drawnTime = getDrawnCookingTime(pizzaBurgerTime);
                break;
            case 2:
                drawnTime = getDrawnCookingTime(veggieBurgerTime);
                break;
            case 3:
                drawnTime = getDrawnCookingTime(revivalBaguetteTime);
                break;
        }
        return drawnTime;
    }

    @Override
    public boolean mouseClicked(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
        if(isMouseInsideBox(this.mousePos, FIELD_1, getOffsetX(), getOffsetY())){
            //Pizza Burger
            this.selectedFood = 1;
            Networking.sendToServer(new CookbotCommsPacket(1, this.botContainer.cookbot.cookingProgress, this.botContainer.cookbot.isCooking, this.botContainer.cookbot.outputItem, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
            //this.botContainer.cookbot.selectedFood = selectedFood;
        }else if(isMouseInsideBox(this.mousePos, FIELD_2, getOffsetX(), getOffsetY())){
            //Veggie Burger
            this.selectedFood = 2;
            Networking.sendToServer(new CookbotCommsPacket(2, this.botContainer.cookbot.cookingProgress, this.botContainer.cookbot.isCooking, this.botContainer.cookbot.outputItem, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
            //this.botContainer.cookbot.selectedFood = selectedFood;
        }else if(isMouseInsideBox(this.mousePos, FIELD_3, getOffsetX(), getOffsetY())){
            //revival Baguette
            this.selectedFood = 3;
            Networking.sendToServer(new CookbotCommsPacket(3, this.botContainer.cookbot.cookingProgress, this.botContainer.cookbot.isCooking, this.botContainer.cookbot.outputItem, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
            //this.botContainer.cookbot.selectedFood = selectedFood;
        }else if(isMouseInsideBox(this.mousePos, FIELD_CRAFT, getOffsetX(), getOffsetY())){
            if(this.botContainer.cookbot.outputItem == ItemStack.EMPTY && !this.botContainer.cookbot.isCooking && canCraftFood(this.selectedFood)){
                switch (this.selectedFood){
                    case 1:
                        for(int i = 0; i < pizzaBurgerRecipe.length; i++){
                            Networking.sendToServer(new CookbotRemoveIngredientsPacket(this.botContainer.cookbot.getLevel().dimension(), this.botContainer.cookbot.getBlockPos(), pizzaBurgerRecipe[i], pizzaBurgerRecipeCounts[i]));
                        }
                        Networking.sendToServer(new CookbotCommsPacket(1, this.pizzaBurgerTime, true, this.botContainer.cookbot.outputItem, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
                        break;
                    case 2:
                        for(int i = 0; i < veggieBurgerRecipe.length; i++){
                            Networking.sendToServer(new CookbotRemoveIngredientsPacket(this.botContainer.cookbot.getLevel().dimension(), this.botContainer.cookbot.getBlockPos(), veggieBurgerRecipe[i], veggieBurgerRecipeCounts[i]));
                        }
                        Networking.sendToServer(new CookbotCommsPacket(2, this.veggieBurgerTime, true, this.botContainer.cookbot.outputItem, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
                        break;
                    case 3:
                        for(int i = 0; i < revivalBaguetteRecipe.length; i++){
                            Networking.sendToServer(new CookbotRemoveIngredientsPacket(this.botContainer.cookbot.getLevel().dimension(), this.botContainer.cookbot.getBlockPos(), revivalBaguetteRecipe[i], revivalBaguetteRecipeCounts[i]));
                        }
                        Networking.sendToServer(new CookbotCommsPacket(3, this.revivalBaguetteTime, true, this.botContainer.cookbot.outputItem, Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));
                        break;
                }
            }
        }else if(isMouseInsideBox(this.mousePos, FIELD_COLLECT, getOffsetX(), getOffsetY()) && canCollect){
            if(this.inventory.getFreeSlot() != -1){
                Networking.sendToServer(new CookbotGiveResultsPacket(Objects.requireNonNull(this.botContainer.cookbot.getLevel()).dimension(), this.botContainer.cookbot.getBlockPos()));

                canCollect = false;
                Networking.sendToServer(new CookbotCommsPacket(this.botContainer.cookbot.selectedFood, this.botContainer.cookbot.cookingProgress, false, ItemStack.EMPTY, this.botContainer.cookbot.getLevel().dimension(), this.botContainer.cookbot.getBlockPos()));

            }else{
                this.inventory.player.displayClientMessage(new TranslationTextComponent("message."+ ScrapCraft.MOD_ID+".action_bar.inventory_full").withStyle(TextFormatting.BOLD), true);
                Objects.requireNonNull(minecraft).setScreen(null);
            }
        }
        return super.mouseClicked(p_231044_1_, p_231044_3_, p_231044_5_);
    }

    public boolean canCraftFood(int foodId){
        boolean result = true;
        switch (foodId){
            case 1:
                for (int i = 0; i < pizzaBurgerRecipe.length; i ++){
                    if(this.inventory.player.inventory.items.contains(pizzaBurgerRecipe[i])){
                        int a = getCountOfItemInInventory(this.inventory.player.inventory, pizzaBurgerRecipe[i].getItem());
                        if(a < pizzaBurgerRecipeCounts[i]){
                            result = false;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < veggieBurgerRecipe.length; i ++){
                    if(!this.inventory.player.inventory.items.contains(veggieBurgerRecipe[i])){
                        int a = getCountOfItemInInventory(this.inventory.player.inventory, veggieBurgerRecipe[i].getItem());
                        if(a < veggieBurgerRecipeCounts[i]){
                            result = false;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < revivalBaguetteRecipe.length; i ++){
                    if(!this.inventory.player.inventory.items.contains(revivalBaguetteRecipe[i])){
                        int a = getCountOfItemInInventory(this.inventory.player.inventory, revivalBaguetteRecipe[i].getItem());
                        if(a < revivalBaguetteRecipeCounts[i]){
                            result = false;
                        }
                    }
                }
                break;
        }
        if(this.inventory.player.isCreative()){
            result = true;
        }
        System.out.println("Trying to craft food with the id of " + foodId + " turned out to be " + result);
        return result;
    }

    public int getOffsetX(){
        return (this.width - WIDTH) / 2;
    }

    public int getOffsetY(){
        return (this.height - HEIGHT) / 2;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }


}
