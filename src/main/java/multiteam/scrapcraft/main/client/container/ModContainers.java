package multiteam.scrapcraft.main.client.container;

import multiteam.scrapcraft.main.Registration;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {

    public static final RegistryObject<ContainerType<CookBotContainer>> COOKBOT_CONTAINER_TYPE = Registration.CONTAINER_TYPES.register("cookbot_menu", () -> IForgeContainerType.create(CookBotContainer::new));

    public static void register(){}
}
