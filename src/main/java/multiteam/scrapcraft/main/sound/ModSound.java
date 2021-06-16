package multiteam.scrapcraft.main.sound;

import multiteam.scrapcraft.ScrapCraft;
import multiteam.scrapcraft.main.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

public class ModSound {
    //LAZY
    public static final Lazy<SoundEvent> SPUDGUN_SHOOT_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ScrapCraft.MOD_ID, "item.scrapcraft.spudgun.shoot")));
    public static final Lazy<SoundEvent> SPUDGUN_SHOOT_FAIL_LAZY = Lazy.of(()-> new SoundEvent(new ResourceLocation(ScrapCraft.MOD_ID, "item.scrapcraft.spudgun.shoot_fail")));

    //SOUNDS
    public static final RegistryObject<SoundEvent> PUDGUN_SHOOT = Registration.SOUND_EVENTS.register("scrapcraft.item.spudgun.shoot", SPUDGUN_SHOOT_LAZY);
    public static final RegistryObject<SoundEvent> PUDGUN_SHOOT_FAIL = Registration.SOUND_EVENTS.register("scrapcraft.item.spudgun.shoot_fail", SPUDGUN_SHOOT_FAIL_LAZY);

    public static void register() {}
}
