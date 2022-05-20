package multiteam.scrapcraft.main.event;

import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.Event;

public class EnableConnectPointsEvent extends Event {

    public boolean state;
    public World level;

    public EnableConnectPointsEvent(boolean state, World level) {
        this.state = state;
        this.level = level;
    }

}
