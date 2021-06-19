package multiteam.scrapcraft.main.item;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("unused")
public class ModItems {

    //Tools
    public static final RegistryObject<Item> HAMMER = Registration.ITEMS.register("sledge_hammer", () -> new HammerItem(ItemTier.IRON, 1, -2.8F, (new Item.Properties()).tab(ScrapCraft.SCRAPCRAFT_TOOLS)));
    public static final RegistryObject<Item> SPUDGUN = Registration.ITEMS.register("spud_gun", () -> new SpudGun(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_TOOLS).durability(200)));

    //Items
    public static final RegistryObject<Item> GLUE = Registration.ITEMS.register("glue", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));
    public static final RegistryObject<Item> WOC_MEAT = Registration.ITEMS.register("woc_meat", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));

    //Food
    public static final RegistryObject<Item> TOMATO = Registration.ITEMS.register("tomato", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));
    public static final RegistryObject<Item> PIZZA_BURGER = Registration.ITEMS.register("pizza_burger", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));
    public static final RegistryObject<Item> VEGGIE_BURGER = Registration.ITEMS.register("veggie_burger", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));
    public static final RegistryObject<Item> REVIVAL_BAGUETTE = Registration.ITEMS.register("revival_baguette", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));

    public static void register(){}

}
