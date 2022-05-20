package multiteam.scrapcraft.main.item.spudweapons;

import multiteam.scrapcraft.main.item.WeaponProjectile;
import multiteam.scrapcraft.main.sound.ModSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SpudShotgun extends SpudWeapon {

    public SpudShotgun(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 25;
    }

    @Override
    public Item getAmmoItem() {
        return Items.POTATO;
    }

    @Override
    public void createProjectileEntities(World world, PlayerEntity playerEntity) {
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                WeaponProjectile projectile = new WeaponProjectile(world, playerEntity);
                projectile.bulletDamage = 3;
                projectile.setItem(new ItemStack(this.getAmmoItem()));
                projectile.shootFromRotation(playerEntity, playerEntity.xRot + (x * 8), playerEntity.yRot + (y * 8), 0.0F, 1.0F, 1.0F);

                world.addFreshEntity(projectile);
            }
        }
    }

    @Override
    public SoundEvent getShootSound() {
        return ModSound.PUDGUN_SHOOT.get();
    }

    @Override
    public SoundEvent getShootFail() {
        return ModSound.PUDGUN_SHOOT_FAIL.get();
    }

}