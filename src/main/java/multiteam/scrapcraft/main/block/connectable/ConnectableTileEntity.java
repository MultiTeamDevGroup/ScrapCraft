package multiteam.scrapcraft.main.block.connectable;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.block.connectable.seat.SeatTileEntity;
import multiteam.scrapcraft.main.event.EnableConnectPointsEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class ConnectableTileEntity extends TileEntity {


    private boolean connectPointState = false;

    public ConnectableTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public void setConnectPointState(boolean state){
        this.connectPointState = state;
    }

    public boolean getConnectPointState(){
        return this.connectPointState;
    }

    @SubscribeEvent
    public static void enableConnectionPoints(EnableConnectPointsEvent event){
        List<TileEntity>  tiles = event.level.blockEntityList;
        for (TileEntity tile : tiles){
            if(tile instanceof ConnectableTileEntity){
                ((ConnectableTileEntity) tile).setConnectPointState(event.state);
            }
        }

    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getBlockPos(), getBlockPos().offset(20, 20, 20));
    }

}
