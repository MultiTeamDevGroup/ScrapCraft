package multiteam.scrapcraft.main.client.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BearingButton extends AbstractButton {
    public static final BearingButton.ITooltip NO_TOOLTIP = (p_238488_0_, p_238488_1_, p_238488_2_, p_238488_3_) -> {
    };
    protected final BearingButton.IPressable onPress;
    protected final BearingButton.ITooltip onTooltip;

    public BearingButton(int posX, int posY, int width, int height, ITextComponent label, BearingButton.IPressable function) {
        this(posX, posY, width, height, label, function, NO_TOOLTIP);
    }

    public BearingButton(int posX, int posY, int width, int height, ITextComponent label, BearingButton.IPressable function, BearingButton.ITooltip tooltip) {
        super(posX, posY, width, height, label);
        this.onPress = function;
        this.onTooltip = tooltip;
    }

    public void onPress() {
        this.onPress.onPress(this);
    }

    public void renderButton(MatrixStack matrixStack, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
        super.renderButton(matrixStack, p_230431_2_, p_230431_3_, p_230431_4_);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, p_230431_2_, p_230431_3_);
        }

    }

    public void renderToolTip(MatrixStack matrixStack, int posX, int posY) {
        this.onTooltip.onTooltip(this, matrixStack, posX, posY);
    }

    @OnlyIn(Dist.CLIENT)
    public interface IPressable {
        void onPress(BearingButton bearingButton);
    }

    @OnlyIn(Dist.CLIENT)
    public interface ITooltip {
        void onTooltip(BearingButton bearingButton, MatrixStack matrixStack, int posX, int posY);
    }
}