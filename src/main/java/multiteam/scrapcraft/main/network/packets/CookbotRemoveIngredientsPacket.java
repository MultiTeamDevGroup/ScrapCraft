package multiteam.scrapcraft.main.network.packets;

import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;


public class CookbotRemoveIngredientsPacket {

    private final ItemStack stackToRemove;
    private final int amountToRemoveFromStack;

    public CookbotRemoveIngredientsPacket(PacketBuffer buf) {
        stackToRemove = buf.readItem();
        amountToRemoveFromStack = buf.readInt();
    }

    public CookbotRemoveIngredientsPacket( ItemStack stackToRemove, int amountToRemoveFromStack) {
        this.stackToRemove = stackToRemove;
        this.amountToRemoveFromStack = amountToRemoveFromStack;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeItemStack(stackToRemove,true);
        buf.writeInt(amountToRemoveFromStack);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerInventory inventory = Objects.requireNonNull(ctx.get().getSender()).connection.player.inventory;
            inventory.items.get(inventory.findSlotMatchingItem(stackToRemove)).shrink(amountToRemoveFromStack);
        });
        return true;
    }

}
