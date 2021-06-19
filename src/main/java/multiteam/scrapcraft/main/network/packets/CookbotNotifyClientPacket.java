package multiteam.scrapcraft.main.network.packets;

import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CookbotNotifyClientPacket {
    private final RegistryKey<World> type;
    private final BlockPos pos;

    public CookbotNotifyClientPacket(PacketBuffer buf) {
        type = RegistryKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        pos = buf.readBlockPos();
    }

    public CookbotNotifyClientPacket(RegistryKey<World> type, BlockPos pos) {
        this.type = type;
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeResourceLocation(type.location());
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerWorld worldIn = ctx.get().getSender().level.getServer().getLevel(type);
            //EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(id);
            CookBotTileEntity tile = (CookBotTileEntity) worldIn.getBlockEntity(pos);
            if (tile == null) {
                throw new IllegalStateException("CookBotTileEntity was null; Its either been destroyed or went missing!");
            }else{
                tile.updateClient();
            }
            //entityType.spawn(spawnWorld, null, null, pos, SpawnReason.SPAWN_EGG, true, true);
        });
        return true;
    }
}
