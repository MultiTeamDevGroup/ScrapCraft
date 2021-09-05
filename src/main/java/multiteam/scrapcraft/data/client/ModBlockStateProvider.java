package multiteam.scrapcraft.data.client;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModBlockStateProvider  extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, ScrapCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.METAL_1.get());
        simpleBlock(ModBlocks.METAL_2.get());
        simpleBlock(ModBlocks.METAL_3.get());
        simpleBlock(ModBlocks.CONCRETE_1.get());
        simpleBlock(ModBlocks.CONCRETE_2.get());
        simpleBlock(ModBlocks.CONCRETE_3.get());
        simpleBlock(ModBlocks.INSULATION.get());
        simpleBlock(ModBlocks.PUNCHED_STEEL.get());
        simpleBlock(ModBlocks.SQUARE_MESH.get());
        simpleBlock(ModBlocks.BARRIER_BLOCK.get());
        simpleBlock(ModBlocks.PATH_LIGHT.get());
        simpleBlock(ModBlocks.CONCRETE_SLAB.get());
        simpleBlock(ModBlocks.RUSTED_METAL.get());
        simpleBlock(ModBlocks.NET_BLOCK.get());
        simpleBlock(ModBlocks.THICK_NET_BLOCK.get());
        simpleBlock(ModBlocks.STRIPED_NET_BLOCK.get());
        simpleBlock(ModBlocks.WORN_METAL.get());
        simpleBlock(ModBlocks.CHALLANGE_BLOCK_1.get());
        simpleBlock(ModBlocks.CHALLANGE_BLOCK_2.get());
        simpleBlock(ModBlocks.ALUMINIUM.get());
        simpleBlock(ModBlocks.ARROW_LIGHT_BLOCK.get());
        simpleBlock(ModBlocks.TILES_BLOCK.get());
    }
}
