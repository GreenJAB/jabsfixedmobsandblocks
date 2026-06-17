package net.greenjab.jabsfixedmobsandblocks.mixin.mobs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.GameRuleRegistry;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.gamerules.GameRule;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Piglin.class)
public abstract class PiglinMixin {

    @ModifyExpressionValue(method = "wantsToPickUp", at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/level/gamerules/GameRules;MOB_GRIEFING:Lnet/minecraft/world/level/gamerules/GameRule;",
            opcode = Opcodes.GETSTATIC
    ))
    public GameRule<Boolean> passiveMobGriefing(GameRule<Boolean> original) {
        return GameRuleRegistry.PEACEFUL_MOB_GRIEFING;
    }
}
