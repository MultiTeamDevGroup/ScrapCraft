package multiteam.scrapcraft.main.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public abstract class BotEntity extends CreatureEntity {

    protected BotEntity(EntityType<? extends CreatureEntity> p_i48576_1_, World p_i48576_2_) {
        super(p_i48576_1_, p_i48576_2_);
    }

    public void aiStep() {
        this.updateSwingTime();
        this.updateNoActionTime();
        super.aiStep();
    }

    protected void updateNoActionTime() {
        float f = this.getBrightness();
        if (f > 0.5F) {
            this.noActionTime += 2;
        }

    }

    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        return !this.isInvulnerableTo(p_70097_1_) && super.hurt(p_70097_1_, p_70097_2_);
    }

    public boolean isPreventingPlayerRest(PlayerEntity p_230292_1_) {
        return true;
    }

    protected boolean shouldDespawnInPeaceful() {
        return this.despawnInPeaceful();
    }

    public void registerGoals() {
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GolemEntity.class, true));
        this.registerAdditionalGoals();
        super.registerGoals();
    }

    public abstract SoundCategory getSoundSource();

    public abstract boolean despawnInPeaceful();

    public abstract void registerAdditionalGoals();

    public abstract SoundEvent getHurtSound(DamageSource source);

    public abstract SoundEvent getDeathSound();

    public abstract SoundEvent getStepSound();


    //Dont even ask why this class exists, i just wanted to have a BotEntity, cuz that sounds better than MobEntity....
}
