package multiteam.scrapcraft.main.network;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.network.packets.CookbotCommsPacket;
import multiteam.scrapcraft.main.network.packets.CookbotGiveResultsPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {

    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ScrapCraft.MOD_ID, "scrapcraft"), () -> "1.0", s -> true, s -> true);

        INSTANCE.messageBuilder(CookbotCommsPacket.class, nextID()).encoder(CookbotCommsPacket::toBytes).decoder(CookbotCommsPacket::new).consumer(CookbotCommsPacket::handleComms).add();

        INSTANCE.messageBuilder(CookbotGiveResultsPacket.class, nextID()).encoder(CookbotGiveResultsPacket::toBytes).decoder(CookbotGiveResultsPacket::new).consumer(CookbotGiveResultsPacket::handle).add();

        //INSTANCE.messageBuilder(PacketSpawn.class, nextID()).encoder(PacketSpawn::toBytes).decoder(PacketSpawn::new).consumer(PacketSpawn::handle).add();
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
