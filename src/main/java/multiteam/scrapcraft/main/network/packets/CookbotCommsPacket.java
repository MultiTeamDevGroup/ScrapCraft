package multiteam.scrapcraft.main.network.packets;

import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import multiteam.scrapcraft.main.network.Networking;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class CookbotCommsPacket {
    private final int selected;
    private final int progress;
    private final boolean isCooking;
    private final ItemStack outputItem;
    private final RegistryKey<World> type;
    private final BlockPos pos;

    public CookbotCommsPacket(PacketBuffer buf) {
        this.selected = buf.readInt();
        this.progress = buf.readInt();
        this.isCooking = buf.readBoolean();
        this.outputItem = buf.readItem();
        this.type = RegistryKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        this.pos = buf.readBlockPos();
    }

    public CookbotCommsPacket(int selected, int progress, boolean isCooking, ItemStack outputItem, RegistryKey<World> type, BlockPos pos) {
        this.selected = selected;
        this.progress = progress;
        this.isCooking = isCooking;
        this.outputItem = outputItem;
        this.type = type;
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(selected);
        buf.writeInt(progress);
        buf.writeBoolean(isCooking);
        buf.writeItemStack(outputItem, true);
        buf.writeResourceLocation(type.location());
        buf.writeBlockPos(pos);
    }

    public boolean handleComms(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            ServerWorld worldIn = Objects.requireNonNull(Objects.requireNonNull(player).getServer()).getLevel(type);
            TileEntity tileEntity = Objects.requireNonNull(worldIn).getBlockEntity(pos);
            if (tileEntity == null) {
                throw new IllegalStateException("CookBotTileEntity was null; Its either been destroyed or went missing!");
            }
            if(tileEntity instanceof CookBotTileEntity){
                CookBotTileEntity tile = (CookBotTileEntity) tileEntity;
                tile.setValues(this.selected, this.progress, this.isCooking, this.outputItem);
                Networking.sendToAllClients(new CookbotNotifyClientPacket(type, pos, selected, progress, isCooking, outputItem));
            }else{
                throw new IllegalStateException("This tile is not a cookbot!");
            }
        });
        return true;
    }

}
