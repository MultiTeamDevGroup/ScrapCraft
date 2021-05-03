package multiteam.scrapcraft.data.client;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }
}
