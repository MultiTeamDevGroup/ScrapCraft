package net.multiteam.scrapcraft.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.multiteam.scrapcraft.ScrapCraft;
import net.multiteam.scrapcraft.block.ModBlocks;

import java.rmi.registry.Registry;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ScrapCraft.MODID);

    public static final RegistryObject<BlockEntityType<CookbotBlockEntity>> COOKBOT_BLOCK_ENTITY = BLOCK_ENTITIES.register("cookbot_block_entity", () ->
            BlockEntityType.Builder.of(CookbotBlockEntity::new, ModBlocks.COOKBOT_BLOCK.get()).build(null)
    );

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
