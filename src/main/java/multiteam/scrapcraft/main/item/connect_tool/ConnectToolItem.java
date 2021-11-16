package multiteam.scrapcraft.main.item.connect_tool;

import multiteam.scrapcraft.main.block.connectable.ConnectableTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.ISyncable;

import java.util.ArrayList;

public class ConnectToolItem extends Item implements IAnimatable, ISyncable {

    public AnimationFactory factory = new AnimationFactory(this);
    private boolean isConnecting = false;
    private ConnectableTileEntity.ConnectHolder connectFrom;
    private ConnectableTileEntity.ConnectHolder connectTo;

    public ConnectToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        // Not setting an animation here as that's handled below
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {

    }

    @Override
    public ActionResultType useOn(ItemUseContext useContext) {
        World worldIn = useContext.getLevel();
        BlockPos clickedPos = useContext.getClickedPos();
        BlockState clickedBlock = worldIn.getBlockState(clickedPos);
        TileEntity tile = worldIn.getBlockEntity(clickedPos);

        if(tile != null && !worldIn.isClientSide){
            if(tile instanceof ConnectableTileEntity){
                ConnectableTileEntity connectTile = (ConnectableTileEntity)tile;

                if(!this.isConnecting){
                    this.isConnecting = true;
                    this.connectFrom = new ConnectableTileEntity.ConnectHolder();
                    this.connectFrom.connectInfo = connectTile.connectInfo;
                    this.connectFrom.position = connectTile.getBlockPos();
                    System.out.println("started connecting " + worldIn.getBlockState(this.connectFrom.position) + " at " + this.connectFrom.position + " with connect info of: " + this.connectFrom.connectInfo.connectType + " - " + this.connectFrom.connectInfo.connectMethod);
                }else{

                    this.isConnecting = false;
                    this.connectTo = new ConnectableTileEntity.ConnectHolder();
                    this.connectTo.connectInfo = connectTile.connectInfo;
                    this.connectTo.position = connectTile.getBlockPos();

                    if(this.connectTo.connectInfo.connections == null){
                        this.connectTo.connectInfo.connections = new ArrayList<>();;
                    }

                    if(this.connectTo.connectInfo.connections.size() <= this.connectTo.connectInfo.connectAmountLimit){
                        if(this.connectTo.connectInfo.connections.contains(this.connectFrom)){
                            this.connectTo.connectInfo.connections.remove(this.connectFrom);
                            System.out.println("disconnected " + worldIn.getBlockState(this.connectFrom.position) + " at " + this.connectFrom.position + " from " + worldIn.getBlockState(this.connectTo.position) + " at " + this.connectTo.position);
                        }else{
                            if(connectTile.CheckConnectability(this.connectFrom.connectInfo.connectType, this.connectTo.connectInfo.connectType)){
                                connectTile.connectInfo.connections.add(this.connectFrom);
                                System.out.println("connected " + worldIn.getBlockState(this.connectFrom.position) + " at " + this.connectFrom.position + " to " + worldIn.getBlockState(this.connectTo.position) + " at " + this.connectTo.position);
                            }
                        }

                    }


                }

            }else{
                this.isConnecting = false;
            }
        }else if(!worldIn.isClientSide){
            this.isConnecting = false;
        }

        return super.useOn(useContext);
    }

    @Override
    public void onUseTick(World p_219972_1_, LivingEntity p_219972_2_, ItemStack p_219972_3_, int p_219972_4_) {
        super.onUseTick(p_219972_1_, p_219972_2_, p_219972_3_, p_219972_4_);
    }
}
