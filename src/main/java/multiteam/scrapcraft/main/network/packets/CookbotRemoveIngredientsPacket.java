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

    private final RegistryKey<World> type;
    private final BlockPos pos;
    private final ItemStack stackToRemove;
    private final int amountToRemoveFromStack;

    public CookbotRemoveIngredientsPacket(PacketBuffer buf) {
        type = RegistryKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        pos = buf.readBlockPos();
        stackToRemove = buf.readItem();
        amountToRemoveFromStack = buf.readInt();
    }

    public CookbotRemoveIngredientsPacket(RegistryKey<World> type, BlockPos pos, ItemStack stackToRemove, int amountToRemoveFromStack) {
        this.type = type;
        this.pos = pos;
        this.stackToRemove = stackToRemove;
        this.amountToRemoveFromStack = amountToRemoveFromStack;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeResourceLocation(type.location());
        buf.writeBlockPos(pos);
        buf.writeItemStack(stackToRemove,true);
        buf.writeInt(amountToRemoveFromStack);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerWorld worldIn = Objects.requireNonNull(ctx.get().getSender()).level.getServer().getLevel(type);
            CookBotTileEntity tile = (CookBotTileEntity) worldIn.getBlockEntity(pos);
            if (tile == null) {
                throw new IllegalStateException("CookBotTileEntity was null; Its either been destroyed or went missing!");
            }else{
                PlayerInventory inventory = Objects.requireNonNull(ctx.get().getSender()).connection.player.inventory;
                inventory.items.get(inventory.findSlotMatchingItem(stackToRemove)).shrink(amountToRemoveFromStack);
            }
        });
        return true;
    }

}
