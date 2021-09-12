package multiteam.scrapcraft.main.block.connectable;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.event.EnableConnectPointsEvent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class ConnectableTileEntity extends TileEntity {

    private boolean connectPointState = false;
    public Vector3f connectionPointColor;
    public ConnectInfo connectInfo;

    public class ConnectInfo{
        public ConnectMethod connectMethod;
        public ConnectType connectType;
        public List<Map<ConnectInfo, BlockPos>> connections;
    }
    public enum ConnectMethod{
        MASTER,
        SLAVE,
        BIDIRECTIONAL
    }
    public enum ConnectType{
        LOGIC, //Logic gates, timers
        INPUT, //buttons, switches, sensors
        OUTPUT, //spudgun, lamps, horns, toteblocks
        MECHANICAL, //pistons, bearings
        SEAT //S E A T
    }

    public ConnectableTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
        this.connectionPointColor = defineConnectPointColor();
        this.connectInfo = new ConnectInfo();
        this.connectInfo.connectMethod = defineConnectMethod();
        this.connectInfo.connectType = defineConnectType();
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

    public abstract Vector3f defineConnectPointColor();
    public abstract ConnectMethod defineConnectMethod();
    public abstract ConnectType defineConnectType();

}
