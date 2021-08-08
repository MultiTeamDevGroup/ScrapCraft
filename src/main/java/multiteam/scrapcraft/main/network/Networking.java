package multiteam.scrapcraft.main.network;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.network.packets.CookbotCommsPacket;
import multiteam.scrapcraft.main.network.packets.CookbotGiveResultsPacket;
import multiteam.scrapcraft.main.network.packets.CookbotNotifyClientPacket;
import multiteam.scrapcraft.main.network.packets.CookbotRemoveIngredientsPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
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
        INSTANCE.messageBuilder(CookbotRemoveIngredientsPacket.class, nextID()).encoder(CookbotRemoveIngredientsPacket::toBytes).decoder(CookbotRemoveIngredientsPacket::new).consumer(CookbotRemoveIngredientsPacket::handle).add();
        INSTANCE.messageBuilder(CookbotNotifyClientPacket.class, nextID()).encoder(CookbotNotifyClientPacket::toBytes).decoder(CookbotNotifyClientPacket::new).consumer(CookbotNotifyClientPacket::handle).add();

    }

    public static void sendToAllClients(Object packet) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
