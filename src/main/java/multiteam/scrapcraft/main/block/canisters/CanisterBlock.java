package multiteam.scrapcraft.main.block.canisters;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.item.WeaponProjectile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CanisterBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(3.0D, 1.0D, 3.0D, 13.0D, 9.0D, 13.0D);

    public CanisterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModBlocks.CANISTER_TILE_SMALL.get().create();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState p_149645_1_) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if (entity instanceof ArrowEntity || entity instanceof WeaponProjectile) {
            if (!worldIn.isClientSide) {
                entity.remove(false);
                worldIn.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3.0F, Explosion.Mode.DESTROY);
            }
        }
    }
}
