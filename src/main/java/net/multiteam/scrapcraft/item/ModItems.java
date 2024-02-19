package net.multiteam.scrapcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.multiteam.scrapcraft.ScrapCraft;
import net.multiteam.scrapcraft.block.ModBlocks;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ScrapCraft.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScrapCraft.MODID);

    //public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build())));


    public static final RegistryObject<CreativeModeTab> SCRAPCRAFT_TAB = CREATIVE_MODE_TABS.register("scrapcraft_tab", () -> CreativeModeTab.builder()
            .icon(() -> ModBlocks.COOKBOT_BLOCK.get().asItem().getDefaultInstance())
            .title(Component.translatable("creativetab.scrapcraft"))
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.COOKBOT_BLOCK.get());
            }).build());

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
