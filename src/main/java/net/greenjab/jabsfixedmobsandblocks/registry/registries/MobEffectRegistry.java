package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.registry.other.CustomEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MobEffectRegistry {

    public static Holder<MobEffect> INSOMNIA = register("insomnia", new CustomEffect(MobEffectCategory.BENEFICIAL,0x98D982));

    private static Holder<MobEffect> register(String name, MobEffect statusEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, JabsFixedMobsAndBlocks.id(name), statusEffect);
    }

    public static void registerMobEffects() {
        System.out.println("register MobEffects");
    }
}
