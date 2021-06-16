package multiteam.scrapcraft.main.item;

import multiteam.scrapcraft.main.sound.ModSound;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.function.Predicate;

public abstract class SpudWeapon extends BowItem {

    public SpudWeapon(Properties properties) {
        super(properties);
    }

    public ItemStack getAmmoInInventory(PlayerEntity playerEntity){
        Item predicate = this.getAmmoItem();
        PlayerInventory playerInventory = playerEntity.inventory;
        for (int i = 0; i < playerInventory.getContainerSize(); i++) {
            ItemStack stack = playerInventory.getItem(i);
            if (predicate == stack.getItem()) {
                return stack;
            }
        }
        return playerEntity.abilities.instabuild ? new ItemStack(predicate) :ItemStack.EMPTY;
    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity livingEntity, int integerField) {
        if (livingEntity instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            boolean canShoot = getAmmoInInventory(playerEntity) != ItemStack.EMPTY || playerEntity.isCreative();
            ItemStack ammoStack = getAmmoInInventory(playerEntity);
            if(canShoot){
                if(!worldIn.isClientSide){
                    this.createProjectileEntities(worldIn, playerEntity);
                    worldIn.playSound((PlayerEntity) null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), this.getShootSound(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                    if(!playerEntity.isCreative()){
                        stack.hurtAndBreak(1, (LivingEntity)playerEntity, (p) -> { ((PlayerEntity)p).broadcastBreakEvent(playerEntity.getUsedItemHand()); });
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            playerEntity.inventory.removeItem(ammoStack);
                        }
                    }
                }
            }else{
                worldIn.playSound((PlayerEntity) null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), this.getShootFail(), SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == this.getAmmoItem();
    }

    public abstract Item getAmmoItem();

    public abstract void createProjectileEntities(World world, PlayerEntity playerEntity);

    public abstract SoundEvent getShootSound();

    public abstract SoundEvent getShootFail();
}
