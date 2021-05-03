package multiteam.scrapcraft.main.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RenderSideBlock extends Block {

    public RenderSideBlock(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState state2, Direction direction) {
        return super.skipRendering(state, state2, direction);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState  blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext context) {
        return VoxelShapes.block();
    }

}
