package multiteam.scrapcraft.main.block;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import multiteam.scrapcraft.main.block.cookbot.CookBotBlock;
import multiteam.scrapcraft.main.block.cookbot.CookBotTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {


    public static final RegistryObject<Block> COOKBOT_BLOCK = registerWithItem("cookbot_block", () -> new CookBotBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_YELLOW).strength(0,0).harvestLevel(0).sound(SoundType.ANVIL)), new Item.Properties().tab(ScrapCraft.SCRAPCRAFT_MACHINES));
    public static final RegistryObject<TileEntityType<CookBotTileEntity>> COOKBOT_TILE = Registration.TILE_ENTITY_TYPES.register("cookbot_tile", () -> TileEntityType.Builder.of(CookBotTileEntity::new, COOKBOT_BLOCK.get()).build(null));

    public static void register(){}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block){
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block, Item.Properties itemProperties){
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), itemProperties));
        return ret;
    }
}
