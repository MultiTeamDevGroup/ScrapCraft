package multiteam.scrapcraft.main;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.client.container.ModContainers;
import multiteam.scrapcraft.main.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ScrapCraft.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ScrapCraft.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ScrapCraft.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, ScrapCraft.MOD_ID);

    public static void register(){
        IEventBus modeEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modeEventBus);
        ITEMS.register(modeEventBus);
        TILE_ENTITY_TYPES.register(modeEventBus);
        CONTAINER_TYPES.register(modeEventBus);

        ModBlocks.register();
        ModItems.register();
        ModContainers.register();
    }
}
