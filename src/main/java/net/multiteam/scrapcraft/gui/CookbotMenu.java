package net.multiteam.scrapcraft.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.multiteam.scrapcraft.block.ModBlocks;
import net.multiteam.scrapcraft.block.entity.CookbotBlockEntity;

public class CookbotMenu extends AbstractContainerMenu {

    public final CookbotBlockEntity cookbotBlockEntity;
    private final Level level;

    private int progressTicks;
    private CookbotBlockEntity.RecipeType selectedRecipeType;
    private boolean isCooking;

    public CookbotMenu (int containerId, Inventory inv, FriendlyByteBuf extraData){
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CookbotMenu(int containerId, Inventory inv, BlockEntity entity){
        super(ModMenuTypes.COOKBOT_MENU.get(), containerId);
        checkContainerSize(inv, 2);
        cookbotBlockEntity = ((CookbotBlockEntity) entity);
        this.level = inv.player.level();
        this.cookbotBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 0, 0));
        });
        this.progressTicks = cookbotBlockEntity.getProgressTicks();
        this.selectedRecipeType = cookbotBlockEntity.getSelectedRecipeType();
        this.isCooking = cookbotBlockEntity.isCooking();

    }

    @Override
    public ItemStack quickMoveStack(Player player, int xx) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, cookbotBlockEntity.getBlockPos()),player, ModBlocks.COOKBOT_BLOCK.get());
    }
}
