package net.greenjab.jabsfixedmobsandblocks.mixin.night;

import net.minecraft.world.DifficultyInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DifficultyInstance.class)
public abstract class DifficultyInstanceMixin {

    @ModifyVariable(method = "calculateDifficulty", at = @At(value = "HEAD"), argsOnly = true, ordinal = 0)
    private float inverseMoon(float moonBrightness) {
        return 1 - moonBrightness;
    }
}
