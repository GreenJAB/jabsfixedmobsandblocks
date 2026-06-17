package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.registry.other.SuspiciousBlockRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeSerializers.class)
public abstract class RecipeSerializersMixin {

    @Inject(method = "bootstrap", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 0))
    private static void addRecipe(Registry<RecipeSerializer<?>> registry, CallbackInfoReturnable<Object> cir) {
        Registry.register(registry, JabsFixedMobsAndBlocks.id("suspicious_block"), SuspiciousBlockRecipe.SERIALIZER);
    }
}
