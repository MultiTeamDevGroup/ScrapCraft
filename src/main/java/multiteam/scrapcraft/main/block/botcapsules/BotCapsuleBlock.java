package multiteam.scrapcraft.main.block.botcapsules;

import multiteam.scrapcraft.main.block.ModBlocks;
import multiteam.scrapcraft.main.entity.ModEntities;
import multiteam.scrapcraft.main.entity.tapebot.TapebotEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BotCapsuleBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.1D, 0.0D, 16.0D, 16.0D, 16.0D);


    public BotCapsuleBlock(Properties properties) {
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
        return ModBlocks.TAPEBOT_CAPSULE_TILE.get().create();
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

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hitResult) {
        return ActionResultType.FAIL;
    }

    @Override
    public void onRemove(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
        super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
    }

    @Override
    public void playerDestroy(World worldIn, PlayerEntity playerEntity, BlockPos pos, BlockState state, @Nullable TileEntity tile, ItemStack stack) {
        Entity entity = null;
        if(state == ModBlocks.TAPEBOT_CAPSULE_BLOCK.get().defaultBlockState()){
            entity = new TapebotEntity(ModEntities.TAPEBOT.get(), worldIn);
            summonEntity(worldIn, pos, entity);
        }
    }

    public void summonEntity(World worldin, BlockPos pos, Entity entity){
        worldin.addFreshEntity(entity);
        entity.setPos(pos.getX(), pos.getY(), pos.getZ());
    }
}
