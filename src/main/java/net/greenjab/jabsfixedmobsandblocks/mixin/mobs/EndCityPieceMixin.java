package net.greenjab.jabsfixedmobsandblocks.mixin.mobs;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(EndCityPieces.EndCityPiece.class)
public abstract class EndCityPieceMixin {

    @Inject(method = "handleDataMarker", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/ServerLevelAccessor;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z", ordinal = 0
    ))
    private void dyeableShulkerEntities(String markerId, BlockPos position, ServerLevelAccessor level, RandomSource random, BoundingBox chunkBB,
                                        CallbackInfo ci, @Local Shulker sentry) {
        if (random.nextInt(10)==0) {
            sentry.setVariant(Optional.of(DyeColor.PURPLE));
        }
    }
}
