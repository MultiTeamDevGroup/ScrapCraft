package multiteam.scrapcraft.main.uiParticles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector4f;

public class UIParticle {
    private final ResourceLocation texture;
    private final Vector4f field;
    private final int textureWidth;
    private final int textureHeight;

    public UIParticle(ResourceLocation texture, int textureWidth, int textureHeight, Vector4f field){
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
        this.field = field;
    }

    public int getTextureWidth(){
        return this.textureWidth;
    }

    public int getTextureHeight(){
        return this.textureHeight;
    }

    public ResourceLocation getTexture(){
        return this.texture;
    }

    public Vector4f getField(){
        return this.field;
    }
}
