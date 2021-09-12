package multiteam.scrapcraft.main.block.connectable.switcc;

import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class SwitchBlock extends Block {

    public SwitchBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModBlocks.SWITCH_TILE.get().create();
    }
}
