package multiteam.scrapcraft.main.container;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static final RegistryObject<ContainerType<CookBotContainer>> COOKBOT_CONTAINER_TYPE = Registration.CONTAINER_TYPES.register("cookbot_menu", () -> IForgeContainerType.create(CookBotContainer::new));

    public static void register(){}
}
