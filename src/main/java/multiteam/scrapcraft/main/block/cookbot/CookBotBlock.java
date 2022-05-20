package multiteam.scrapcraft.main.block.cookbot;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CookBotBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    private static final VoxelShape SHAPE_FEET = Block.box(1.0D, 0.0D, 5.0D, 12.0D, 4.0D, 14.0D);
    private static final VoxelShape SHAPE_BODY = Block.box(0.0D, 4.0D, 4.0D, 13.0D, 16.0D, 14.0D);
    private static final VoxelShape SHAPE = VoxelShapes.or(SHAPE_BODY, SHAPE_FEET);


    public CookBotBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModBlocks.COOKBOT_TILE.get().create();
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirr) {
        return state.rotate(mirr.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hitResult) {
        if (!worldIn.isClientSide) {
            TileEntity cookbot = worldIn.getBlockEntity(pos);
            if (cookbot instanceof CookBotTileEntity) {
                NetworkHooks.openGui(((ServerPlayerEntity) playerEntity), (CookBotTileEntity) cookbot, pos);
            }
        }
        return super.use(state, worldIn, pos, playerEntity, hand, hitResult);
    }
}
