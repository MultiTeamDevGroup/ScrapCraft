package multiteam.scrapcraft.main.entity;

import multiteam.scrapcraft.main.Registration;
import multiteam.scrapcraft.main.entity.tapebot.TapebotEntity;
import multiteam.scrapcraft.main.entity.tapebot.TapebotEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModEntities {

    //Enemy bots
    public static final RegistryObject<EntityType<TapebotEntity>> TAPEBOT = buildEntity(TapebotEntity::new, TapebotEntity.class, 1.0F, 3.0F);


    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(TAPEBOT.get(), TapebotEntityRenderer::new);
    }

    public static void applyAttributes(){
        GlobalEntityTypeAttributes.put(TAPEBOT.get(), TapebotEntity.createAttributes().build());
    }



    public static void register() { }

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height) {
        String name = entityClass.getSimpleName().toLowerCase();
        return Registration.ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, EntityClassification.CREATURE).sized(width, height).build(name));
    }
}
