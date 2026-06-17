package net.greenjab.jabsfixedmobsandblocks.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.GameRuleRegistry;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin<T extends Mob> extends LivingEntity {

    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "convertTo(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/ConversionParams;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/ConversionParams$AfterConversion;)Lnet/minecraft/world/entity/Mob;", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/ConversionType;convert(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/world/entity/Mob;Lnet/minecraft/world/entity/ConversionParams;)V"
    ))
    private void removeIronGolemTagOnConversion(EntityType<T> entityType, ConversionParams params, EntitySpawnReason spawnReason,
                                                ConversionParams.AfterConversion<T> afterConversion, CallbackInfoReturnable<T> cir){
        Mob ME = (Mob)(Object)this;
        ME.removeTag("iron_golem");
    }

    @ModifyExpressionValue(method = "aiStep", at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/level/gamerules/GameRules;MOB_GRIEFING:Lnet/minecraft/world/level/gamerules/GameRule;",
            opcode = Opcodes.GETSTATIC
    ))
    public GameRule<Boolean> passiveMobGriefing(GameRule<Boolean> original) {
        Mob mob = (Mob)(Object)this;
        if (mob.getType().isAllowedInPeaceful())
            return GameRuleRegistry.PEACEFUL_MOB_GRIEFING;
        return original;
    }

    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;burnUndead()V"))
    private void dontBurnPhantom(Mob entity, Operation<Void> original) {
        if (!(entity instanceof Phantom || entity instanceof ZombieHorse))original.call(entity);
    }

}
