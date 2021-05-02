package multiteam.scrapcraft.main.item;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

import java.util.Set;

public class ModItems {

    public static final RegistryObject<Item> HAMMER = Registration.ITEMS.register("sledge_hammer", () -> new HammerItem(ItemTier.IRON, 1, -2.8F, (new Item.Properties()).tab(ScrapCraft.SCRAPCRAFT_TOOLS)));


    public static void register(){}

}
