package multiteam.scrapcraft.main.network.packets;

import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CookbotCommsPacket {

    private final int selected;
    private final int progress;
    private final boolean isCooking;
    private final ItemStack outputItem;
    private final RegistryKey<World> type;
    private final BlockPos pos;

    public CookbotCommsPacket(PacketBuffer buf) {
        //id = buf.readResourceLocation();
        this.selected = buf.readInt();
        this.progress = buf.readInt();
        this.isCooking = buf.readBoolean();
        this.outputItem = buf.readItem();
        this.type = RegistryKey.create(Registry.DIMENSION_REGISTRY, buf.readResourceLocation());
        this.pos = buf.readBlockPos();
    }

    public CookbotCommsPacket(int selected, int progress, boolean isCooking, ItemStack outputItem, RegistryKey<World> type, BlockPos pos) {
        //this.id = id;
        this.selected = selected;
        this.progress = progress;
        this.isCooking = isCooking;
        this.outputItem = outputItem;
        this.type = type;
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        //buf.writeResourceLocation(id);
        buf.writeInt(selected);
        buf.writeInt(progress);
        buf.writeBoolean(isCooking);
        buf.writeItemStack(outputItem, true);
        buf.writeResourceLocation(type.location());
        buf.writeBlockPos(pos);
    }

    public boolean handleComms(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerWorld worldIn = ctx.get().getSender().level.getServer().getLevel(type);
            //EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(id);
            CookBotTileEntity tile = (CookBotTileEntity) worldIn.getBlockEntity(pos);
            if (tile == null) {
                throw new IllegalStateException("CookBotTileEntity was null; Its either been destroyed or went missing!");
            }else{
                //tile.save(tile.getTileData());
                //This odes send data to the server, and the tile should recieve it but idk why it still doesnt!!! SEND HELP
                //I have figured that something here doesnt work. does it actually sets the values in the tile or not?
            }
            tile.selectedFood = selected;
            tile.cookingProgress = progress;
            tile.isCooking = isCooking;
            tile.outputItem = outputItem;
            tile.setChanged();
            System.out.println("so do i: " + tile.selectedFood + " - " + tile.cookingProgress + " - " + tile.isCooking + " - " + tile.outputItem);
            //entityType.spawn(spawnWorld, null, null, pos, SpawnReason.SPAWN_EGG, true, true);
        });
        return true;
    }

}
