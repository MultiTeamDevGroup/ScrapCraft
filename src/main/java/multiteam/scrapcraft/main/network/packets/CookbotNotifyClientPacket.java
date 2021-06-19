package multiteam.scrapcraft.main.network.packets;

import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class CookbotNotifyClientPacket {
    private final RegistryKey<World> type;
    private final BlockPos pos;
    private final int selected;
    private final int progress;
    private final boolean isCooking;
    private final ItemStack outputItem;

    public CookbotNotifyClientPacket(PacketBuffer buf) {
        //id = buf.readResourceLocation();
        this.selected = buf.readInt();
        this.progress = buf.readInt();
        this.isCooking = buf.readBoolean();
        this.outputItem = buf.readItem();
        this.type = RegistryKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        this.pos = buf.readBlockPos();
    }

    public CookbotNotifyClientPacket(RegistryKey<World> type, BlockPos pos, int selected, int progress, boolean isCooking, ItemStack outputItem) {
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

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            ClientWorld clientLevel = mc.level;
            if (clientLevel != null && type.location() != clientLevel.dimension().location()) {
                return;
            }

            //EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(id);
            CookBotTileEntity tile = (CookBotTileEntity) Objects.requireNonNull(clientLevel).getBlockEntity(pos);
            if (tile == null) {
                throw new IllegalStateException("CookBotTileEntity was null; Its either been destroyed or went missing!");
            }else{
                tile.setValues(selected, progress, isCooking, outputItem);
            }
            //entityType.spawn(spawnWorld, null, null, pos, SpawnReason.SPAWN_EGG, true, true);
        });
        return true;
    }
}
