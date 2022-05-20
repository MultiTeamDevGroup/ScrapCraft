package multiteam.scrapcraft.main.client.container;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;


@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CookBotContainer extends Container {
    private final PlayerInventory playerInventory;
    public final CookBotTileEntity cookbot;
    private final IWorldPosCallable canInteractWithCallable;

    public CookBotContainer(final int windowid, final PlayerInventory playerInventory, final CookBotTileEntity tileE) {
        super(ModContainers.COOKBOT_CONTAINER_TYPE.get(), windowid);
        this.playerInventory = playerInventory;
        this.cookbot = tileE;
        this.canInteractWithCallable = IWorldPosCallable.create(Objects.requireNonNull(tileE.getLevel()), tileE.getBlockPos());

    }

    public CookBotContainer(final int windowid, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowid, playerInventory, getTileEntity(playerInventory, data));
    }

    private static CookBotTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player Inventory cannot be null");
        Objects.requireNonNull(data, "Packet Buffer cannot be null");
        final TileEntity tileE = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileE instanceof CookBotTileEntity) {
            return (CookBotTileEntity) tileE;
        }
        throw new IllegalStateException("TileEntity is Incorrect");
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return stillValid(canInteractWithCallable, playerEntity, ModBlocks.COOKBOT_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity p_82846_1_, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < CookBotTileEntity.slots && !this.moveItemStackTo(stack1, CookBotTileEntity.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, this.slots.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }

    @SuppressWarnings("unused")
    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }
}
