package multiteam.scrapcraft;

import multiteam.multicore_lib.setup.utilities.ItemGroupTool;
import multiteam.scrapcraft.main.Registration;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.block.cookbot.CookBotRenderer;
import multiteam.scrapcraft.main.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(ScrapCraft.MOD_ID)
public class ScrapCraft
{
    public static final String MOD_ID = "scrapcraft";
    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroupTool SCRAPCRAFT_MACHINES = new ItemGroupTool("scrapcraft_machines", () -> new ItemStack(ModBlocks.COOKBOT_BLOCK.get().asItem()));
    public static final ItemGroupTool SCRAPCRAFT_TOOLS = new ItemGroupTool("scrapcraft_tools", () -> new ItemStack(ModItems.HAMMER.get()));
    public static final ItemGroupTool SCRAPCRAFT_BLOCKS = new ItemGroupTool("scrapcraft_blocks", () -> new ItemStack(ModBlocks.INSULATION.get().asItem()));
    public static final ItemGroupTool SCRAPCRAFT_ITEMS = new ItemGroupTool("scrapcraft_items", () -> new ItemStack(ModItems.GLUE.get()));

    public ScrapCraft() {

        GeckoLib.initialize();
        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(ModBlocks.COOKBOT_TILE.get(), CookBotRenderer::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.COOKBOT_BLOCK.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.SQUARE_MESH.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.PUNCHED_STEEL.get(), RenderType.cutoutMipped());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

        }
    }
}
