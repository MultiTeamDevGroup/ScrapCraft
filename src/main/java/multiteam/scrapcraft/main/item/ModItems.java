package multiteam.scrapcraft.main.item;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import multiteam.scrapcraft.main.item.connect_tool.ConnectToolItem;
import multiteam.scrapcraft.main.item.connect_tool.ConnectToolRenderer;
import multiteam.scrapcraft.main.item.spudweapons.SpudGun;
import multiteam.scrapcraft.main.item.spudweapons.SpudShotgun;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

@SuppressWarnings("unused")
public class ModItems {

    //Tools
    public static final RegistryObject<Item> HAMMER = Registration.ITEMS.register("sledge_hammer", () -> new HammerItem(ItemTier.IRON, 1, -2.8F, (new Item.Properties()).tab(ScrapCraft.SCRAPCRAFT_TOOLS)));
    public static final RegistryObject<Item> SPUDGUN = Registration.ITEMS.register("spud_gun", () -> new SpudGun(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_TOOLS).durability(200)));
    public static final RegistryObject<Item> SPUDSHOTGUN = Registration.ITEMS.register("spud_shotgun", () -> new SpudShotgun(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_TOOLS).durability(200)));
    //public static final RegistryObject<Item> CONNECT_TOOL = Registration.ITEMS.register("connect_tool", () -> new ConnectToolItem(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_TOOLS)));
    public static final RegistryObject<ConnectToolItem> CONNECT_TOOL = Registration.ITEMS.register("connect_tool", () -> new ConnectToolItem(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_TOOLS).setISTER(() -> ConnectToolRenderer::new)));


    //Items
    public static final RegistryObject<Item> GLUE = Registration.ITEMS.register("glue", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));
    public static final RegistryObject<Item> WOC_MEAT = Registration.ITEMS.register("woc_meat", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS)));

    //Food
    public static final Food COOKBOT_FOOD = (new Food.Builder().nutrition(4).saturationMod(1.2F).effect(new EffectInstance(Effects.REGENERATION, 50, 1), 1.0F).build());
    public static final RegistryObject<Item> TOMATO = Registration.ITEMS.register("tomato", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(Foods.APPLE)));
    public static final RegistryObject<Item> BANANA = Registration.ITEMS.register("banana", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(Foods.APPLE)));
    public static final RegistryObject<Item> BLUEBERRY = Registration.ITEMS.register("blueberry", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(Foods.APPLE)));
    public static final RegistryObject<Item> BROCCOLI = Registration.ITEMS.register("broccoli", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(Foods.APPLE)));
    public static final RegistryObject<Item> ORANGE = Registration.ITEMS.register("orange", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(Foods.APPLE)));
    public static final RegistryObject<Item> PINEAPPLE = Registration.ITEMS.register("pineapple", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(Foods.APPLE)));
    public static final RegistryObject<Item> PIZZA_BURGER = Registration.ITEMS.register("pizza_burger", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(COOKBOT_FOOD)));
    public static final RegistryObject<Item> VEGGIE_BURGER = Registration.ITEMS.register("veggie_burger", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(COOKBOT_FOOD)));
    public static final RegistryObject<Item> REVIVAL_BAGUETTE = Registration.ITEMS.register("revival_baguette", () -> new Item(new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_ITEMS).food(COOKBOT_FOOD)));

    public static void register() {
    }

}
