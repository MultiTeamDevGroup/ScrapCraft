package multiteam.scrapcraft.main.item.spudweapons;

import multiteam.scrapcraft.main.item.WeaponProjectile;
import multiteam.scrapcraft.main.sound.ModSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SpudGun extends SpudWeapon{

    public SpudGun(Properties properties) {
        super(properties);
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public Item getAmmoItem() {
        return Items.POTATO;
    }

    @Override
    public void createProjectileEntities(World world, PlayerEntity playerEntity) {
        WeaponProjectile weaponProjectile = new WeaponProjectile(world, playerEntity);
        weaponProjectile.bulletDamage = 7;
        weaponProjectile.setItem(new ItemStack(this.getAmmoItem()));
        weaponProjectile.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot, 0.0F, 2.0F, 1.0F);
        world.addFreshEntity(weaponProjectile);
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
