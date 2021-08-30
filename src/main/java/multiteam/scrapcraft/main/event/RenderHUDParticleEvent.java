package multiteam.scrapcraft.main.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.eventbus.api.Event;

public class RenderHUDParticleEvent extends Event {

    public MatrixStack stack;

    public RenderHUDParticleEvent(MatrixStack stack_){
        this.stack = stack_;
    }

}
