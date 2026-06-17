package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;

import java.util.function.ToIntFunction;

public class GameRuleRegistry {
    public static GameRule<Boolean> INSOMNIA_SLEEP_REQUIREMENT;
    public static GameRule<Boolean> PEACEFUL_MOB_GRIEFING;

    public static void registerGameRules() {
        System.out.println("register GameRules");
        INSOMNIA_SLEEP_REQUIREMENT =registerBooleanRule("insomnia_sleep_requirement", GameRuleCategory.PLAYER, false);
        PEACEFUL_MOB_GRIEFING = registerBooleanRule("peaceful_mob_griefing", GameRuleCategory.MOBS, true);
    }

    private static GameRule<Boolean> registerBooleanRule(String name, GameRuleCategory category, boolean defaultValue) {
        return register(name, category, GameRuleType.BOOL, BoolArgumentType.bool(), Codec.BOOL, defaultValue,
                FeatureFlagSet.of(), GameRuleTypeVisitor::visitBoolean,value -> value ? 1 : 0
        );
    }

    private static <T> GameRule<T> register( String name, GameRuleCategory category, GameRuleType type,
            ArgumentType<T> argumentType, Codec<T> codec, T defaultValue,  FeatureFlagSet requiredFeatures,
            GameRules.VisitorCaller<T> acceptor, ToIntFunction<T> commandResultSupplier
    ) {
        return Registry.register(
                BuiltInRegistries.GAME_RULE, JabsFixedMobsAndBlocks.id(name), new GameRule<>(category, type, argumentType, acceptor, codec, commandResultSupplier, defaultValue, requiredFeatures)
        );
    }

}
