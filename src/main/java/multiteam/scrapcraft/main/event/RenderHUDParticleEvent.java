package multiteam.scrapcraft.main.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.MainWindow;
import net.minecraftforge.eventbus.api.Event;

public class RenderHUDParticleEvent extends Event {

    public MatrixStack stack;
    public float partialTicks;
    public MainWindow window;

    public RenderHUDParticleEvent(MatrixStack stack_, float ticks_, MainWindow window_) {
        this.stack = stack_;
        this.partialTicks = ticks_;
        this.window = window_;
    }

}
