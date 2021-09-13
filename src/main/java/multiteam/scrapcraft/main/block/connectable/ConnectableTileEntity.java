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
import software.bernie.geckolib3.core.IAnimatable;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = ScrapCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public abstract class ConnectableTileEntity extends TileEntity implements IAnimatable {

    private boolean connectPointState = false;
    public Vector3f connectionPointColor;
    public ConnectInfo connectInfo;

    public final List<ConnectRule> connectRules = Arrays.asList(
            new ConnectRule(ConnectType.LOGIC, ConnectType.LOGIC, true),
            new ConnectRule(ConnectType.LOGIC, ConnectType.INPUT, false),
            new ConnectRule(ConnectType.LOGIC, ConnectType.OUTPUT, true),
            new ConnectRule(ConnectType.LOGIC, ConnectType.MECHANICAL, false),
            new ConnectRule(ConnectType.LOGIC, ConnectType.SEAT, false),
            new ConnectRule(ConnectType.INPUT, ConnectType.LOGIC, true),
            new ConnectRule(ConnectType.INPUT, ConnectType.INPUT, false),
            new ConnectRule(ConnectType.INPUT, ConnectType.OUTPUT, true),
            new ConnectRule(ConnectType.INPUT, ConnectType.MECHANICAL, true),
            new ConnectRule(ConnectType.INPUT, ConnectType.SEAT, true),
            new ConnectRule(ConnectType.OUTPUT, ConnectType.LOGIC, false),
            new ConnectRule(ConnectType.OUTPUT, ConnectType.INPUT, false),
            new ConnectRule(ConnectType.OUTPUT, ConnectType.OUTPUT, false),
            new ConnectRule(ConnectType.OUTPUT, ConnectType.MECHANICAL, false),
            new ConnectRule(ConnectType.OUTPUT, ConnectType.SEAT, false),
            new ConnectRule(ConnectType.MECHANICAL, ConnectType.LOGIC, false),
            new ConnectRule(ConnectType.MECHANICAL, ConnectType.INPUT, true),
            new ConnectRule(ConnectType.MECHANICAL, ConnectType.OUTPUT, false),
            new ConnectRule(ConnectType.MECHANICAL, ConnectType.MECHANICAL, false),
            new ConnectRule(ConnectType.MECHANICAL, ConnectType.SEAT, true),
            new ConnectRule(ConnectType.SEAT, ConnectType.LOGIC, false),
            new ConnectRule(ConnectType.SEAT, ConnectType.INPUT, true),
            new ConnectRule(ConnectType.SEAT, ConnectType.OUTPUT, false),
            new ConnectRule(ConnectType.SEAT, ConnectType.MECHANICAL, true),
            new ConnectRule(ConnectType.SEAT, ConnectType.SEAT, false)
    );

    public static class ConnectInfo{
        public ConnectMethod connectMethod;
        public ConnectType connectType;
        public int connectAmountLimit;
        public List<ConnectHolder> connections;
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
    public static class ConnectHolder{
        public ConnectableTileEntity.ConnectInfo connectInfo;
        public BlockPos position;
    }
    public class ConnectRule{
        public ConnectType typeA;
        public ConnectType typeB;
        public boolean canConnect;

        public ConnectRule(ConnectType typeA, ConnectType typeB, boolean rule){
            this.typeA = typeA;
            this.typeB = typeB;
            this.canConnect = rule;
        }
    }

    public boolean CheckConnectability(ConnectType typeA, ConnectType typeB){
        boolean state = false;
        for (int i = 0; i < connectRules.size(); i++){
            if(typeA == connectRules.get(i).typeA && typeB == connectRules.get(i).typeB){
                state = connectRules.get(i).canConnect;
            }
        }
        return state;
    }

    public ConnectableTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
        this.connectionPointColor = defineConnectPointColor();
        this.connectInfo = new ConnectInfo();
        this.connectInfo.connectMethod = defineConnectMethod();
        this.connectInfo.connectType = defineConnectType();
        this.connectInfo.connectAmountLimit = defineConnectAmountLimit();
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
    public abstract int defineConnectAmountLimit();

}
