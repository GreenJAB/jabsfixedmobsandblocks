package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin {

    @WrapOperation(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean lingerAddition(LivingEntity instance, MobEffectInstance newEffect, Entity source, Operation<Boolean> original) {
        if (instance instanceof SulfurCube) {
            return original.call(instance, new MobEffectInstance(newEffect.getEffect(), -1, newEffect.getAmplifier(), true, false, newEffect.showIcon()), source);
        }
        return original.call(instance, newEffect, source);
    }
}
