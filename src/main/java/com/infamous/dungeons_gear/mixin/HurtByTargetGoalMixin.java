package com.infamous.dungeons_gear.mixin;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(HurtByTargetGoal.class)
public abstract class HurtByTargetGoalMixin extends TargetGoal {

    @Shadow
    private Class<?>[] reinforcementTypes;

    public HurtByTargetGoalMixin(MobEntity mobIn, boolean checkSight) {
        super(mobIn, checkSight);
    }

    /**
     * Prevents the NPE caused by !mobentity.isOnSameTeam(this.goalOwner.getRevengeTarget()) when getRevengeTarget() returns null
     * Also prevents ClassCastException when checking if current iterated mob, as a TameableEntity, has an owner
     * @author the_infamous_1
     */
    @Overwrite
    protected void alertOthers(){
        double d0 = this.getTargetDistance();
        AxisAlignedBB axisalignedbb = AxisAlignedBB.fromVector(this.goalOwner.getPositionVec()).grow(d0, 10.0D, d0);
        List<MobEntity> list = this.goalOwner.world.getLoadedEntitiesWithinAABB(this.goalOwner.getClass(), axisalignedbb);
        Iterator iterator = list.iterator();

        while(true) {
            MobEntity mobentity;
            while(true) {
                if (!iterator.hasNext()) {
                    return;
                }

                mobentity = (MobEntity)iterator.next();
                if (this.goalOwner != mobentity
                        && mobentity.getAttackTarget() == null
                        && (!(this.goalOwner instanceof TameableEntity) ||
                        (mobentity instanceof TameableEntity && ((TameableEntity)this.goalOwner).getOwner() == ((TameableEntity)mobentity).getOwner()))
                        && (this.goalOwner.getRevengeTarget() != null && !mobentity.isOnSameTeam(this.goalOwner.getRevengeTarget()))) {
                    if (this.reinforcementTypes == null) {
                        break;
                    }

                    boolean flag = false;

                    for(Class<?> oclass : this.reinforcementTypes) {
                        if (mobentity.getClass() == oclass) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        break;
                    }
                }
            }

            this.setAttackTarget(mobentity, this.goalOwner.getRevengeTarget());
        }
    }

    protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
        mobIn.setAttackTarget(targetIn);
    }


}
