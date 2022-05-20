package multiteam.scrapcraft.main.item;

import mcp.MethodsReturnNonnullByDefault;
import multiteam.scrapcraft.main.block.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WeaponProjectile extends SnowballEntity {
    public WeaponProjectile(World worldIn, LivingEntity owner) {
        super(worldIn, owner);
    }

    public int bulletDamage;

    @Override
    protected void onHitEntity(EntityRayTraceResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        this.entityHit(entityHitResult);
    }

    public void entityHit(EntityRayTraceResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), this.bulletDamage);
    }

    @Override
    protected void onHit(RayTraceResult hitResult) {
        super.onHit(hitResult);
        this.collision(hitResult);
    }

    public void collision(RayTraceResult hitResult) {
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte) 3);
            if (this.level.getBlockState(this.blockPosition()).getBlock() != ModBlocks.CANISTER_BLOCK_SMALL.get()) {
                this.remove();
            }
        }
    }
}
