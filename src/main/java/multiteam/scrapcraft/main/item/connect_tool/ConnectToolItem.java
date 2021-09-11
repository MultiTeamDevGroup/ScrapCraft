package multiteam.scrapcraft.main.item.connect_tool;

import net.minecraft.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.ISyncable;

public class ConnectToolItem extends Item implements IAnimatable, ISyncable {

    public AnimationFactory factory = new AnimationFactory(this);

    public ConnectToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        // Not setting an animation here as that's handled below
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {

    }
}
