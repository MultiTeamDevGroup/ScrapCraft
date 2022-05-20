package multiteam.scrapcraft.main.entity.tapebot;

import multiteam.scrapcraft.main.entity.BotEntity;
import multiteam.scrapcraft.main.entity.ModEntities;
import multiteam.scrapcraft.main.entity.tape_projectile.TapeProjectileEntity;
import multiteam.scrapcraft.main.item.WeaponProjectile;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;

public class TapebotEntity extends BotEntity implements IAnimatable, IRangedAttackMob {
    private final AnimationFactory factory = new AnimationFactory(this);

    public TapebotEntity(EntityType<? extends BotEntity> p_i48576_1_, World p_i48576_2_) {
        super(p_i48576_1_, p_i48576_2_);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isPushable() {
        return true;
    }

    @Override
    public SoundCategory getSoundSource() {
        return SoundCategory.HOSTILE;
    }

    @Override
    public boolean despawnInPeaceful() {
        return true;
    }

    @Override
    public void registerAdditionalGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0.0D, 30, 15.0F));
    }

    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
        AbstractArrowEntity abstractarrowentity = this.getArrow(itemstack, p_82196_2_);
        if (this.getMainHandItem().getItem() instanceof net.minecraft.item.BowItem)
            abstractarrowentity = ((net.minecraft.item.BowItem) this.getMainHandItem().getItem()).customArrow(abstractarrowentity);
        double d0 = p_82196_1_.getX() - this.getX();
        double d1 = p_82196_1_.getY(0.3333333333333333D) - abstractarrowentity.getY();
        double d2 = p_82196_1_.getZ() - this.getZ();
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        abstractarrowentity.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F, (float) (14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrowentity);
    }

    protected AbstractArrowEntity getArrow(ItemStack p_213624_1_, float p_213624_2_) {
        return ProjectileHelper.getMobArrow(this, p_213624_1_, p_213624_2_);
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.NETHERITE_BLOCK_BREAK;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.NETHERITE_BLOCK_PLACE;
    }

    @Override
    public SoundEvent getStepSound() {
        return SoundEvents.NETHERITE_BLOCK_STEP;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("tapebot.model.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public class RangedAttackGoal<T extends MobEntity & IRangedAttackMob> extends Goal {
        private final T mob;
        private final double speedModifier;
        private int attackIntervalMin;
        private final float attackRadiusSqr;
        private int attackTime = -1;
        private int seeTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public RangedAttackGoal(T mob, double speedModifier, int attackInterval, float attackRadius) {
            this.mob = mob;
            this.speedModifier = speedModifier;
            this.attackIntervalMin = attackInterval;
            this.attackRadiusSqr = attackRadius * attackRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public void setMinAttackInterval(int p_189428_1_) {
            this.attackIntervalMin = p_189428_1_;
        }

        public boolean canUse() {
            return this.mob.getTarget() != null && this.isHoldingBow();
        }

        protected boolean isHoldingBow() {
            return true;
        }

        public boolean canContinueToUse() {
            return (this.canUse() || !this.mob.getNavigation().isDone()) && this.isHoldingBow();
        }

        public void start() {
            super.start();
            this.mob.setAggressive(true);
        }

        public void stop() {
            super.stop();
            this.mob.setAggressive(false);
            this.seeTime = 0;
            this.attackTime = -1;
            this.strafingTime = -1;
            this.mob.stopUsingItem();
        }

        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null) {
                double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                boolean flag = this.mob.getSensing().canSee(livingentity);
                boolean flag1 = this.seeTime > 0;
                if (flag != flag1) {
                    this.seeTime = 0;
                }

                if (flag) {
                    ++this.seeTime;
                } else {
                    --this.seeTime;
                }

                if (!(d0 > (double) this.attackRadiusSqr) && this.seeTime >= 20) {
                    this.mob.getNavigation().stop();
                    ++this.strafingTime;
                } else {
                    this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20) {
                    if ((double) this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingClockwise = !this.strafingClockwise;
                    }

                    if ((double) this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingBackwards = !this.strafingBackwards;
                    }

                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1) {
                    if (d0 > (double) (this.attackRadiusSqr * 0.75F)) {
                        this.strafingBackwards = false;
                    } else if (d0 < (double) (this.attackRadiusSqr * 0.25F)) {
                        this.strafingBackwards = true;
                    }

                    this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                    this.mob.lookAt(livingentity, 30.0F, 30.0F);
                } else {
                    this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                }

                if (--this.attackTime <= 0 && this.seeTime >= -60) {
                    createProjectileEntities(this.mob.level, this.mob);
                    this.attackTime = this.attackIntervalMin;
                }

            }
        }

        public void createProjectile(World world, LivingEntity entity) {
            TapeProjectileEntity projectileEntity = new TapeProjectileEntity(ModEntities.TAPE_PROJECTILE.get(), world);
            world.addFreshEntity(projectileEntity);
            projectileEntity.setPos(entity.getX(), entity.getY(), entity.getZ());
            projectileEntity.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, 2.0F, 1.0F);
        }

        public void createProjectileEntities(World world, LivingEntity entity) {
            WeaponProjectile weaponProjectile = new WeaponProjectile(world, entity);
            weaponProjectile.bulletDamage = 11;
            weaponProjectile.setItem(new ItemStack(Blocks.YELLOW_WOOL.asItem())); //this is just a placehold still
            weaponProjectile.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, 2.0F, 1.0F);
            world.addFreshEntity(weaponProjectile);
        }
    }
}
