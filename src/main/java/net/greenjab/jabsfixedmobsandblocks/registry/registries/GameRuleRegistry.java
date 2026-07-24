package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;

import java.util.function.ToIntFunction;

public class GameRuleRegistry {
    public static final GameRuleCategory JABSFIXEDMOBSANDBLOCKS = GameRuleCategory.register(JabsFixedMobsAndBlocks.id("aad_jabsfixedmobsandblocks"));

    public static GameRule<Boolean> PEACEFUL_MOB_GRIEFING;

    public static GameRule<Integer> NIGHTS_UNTIL_INSOMNIA;
    public static GameRule<Boolean> INSOMNIA_SLEEP_REQUIREMENT;

    public static GameRule<Boolean> VILLAGERS_NEED_SLEEP;
    public static GameRule<Boolean> VILLAGERS_NEED_FOOD;
    public static GameRule<Boolean> VILLAGERS_NEED_SUNLIGHT;
    public static GameRule<Boolean> VILLAGERS_NEED_FRIENDS;
    public static GameRule<Boolean> VILLAGERS_NEED_SPACE;
    public static GameRule<Boolean> VILLAGERS_TRADE_AT_NIGHT;
    public static GameRule<Boolean> VILLAGERS_STRONGER_DEMAND;
    public static GameRule<Boolean> VILLAGERS_NITWITIFY_ON_ZOMBIFICATION;
    public static GameRule<Boolean> ONE_IRON_GOLEM_PER_MOB;

    public static GameRule<Boolean> HOSTILE_SNIFFER_PLANTS;

    public static void registerGameRules() {
        System.out.println("register GameRules");
        PEACEFUL_MOB_GRIEFING = registerBoolean2("peaceful_mob_griefing", GameRuleCategory.MOBS, true);

        NIGHTS_UNTIL_INSOMNIA = registerInteger("nights_until_insomnia", 7, 0, Integer.MAX_VALUE);
        INSOMNIA_SLEEP_REQUIREMENT = registerBoolean("insomnia_sleep_requirement", false);

        VILLAGERS_NEED_SLEEP = registerBoolean("villagers_need_sleep", true);
        VILLAGERS_NEED_FOOD = registerBoolean("villagers_need_food", true);
        VILLAGERS_NEED_SUNLIGHT = registerBoolean("villagers_need_sunlight", true);
        VILLAGERS_NEED_FRIENDS = registerBoolean("villagers_need_friends", true);
        VILLAGERS_NEED_SPACE = registerBoolean("villagers_need_space", true);
        VILLAGERS_TRADE_AT_NIGHT = registerBoolean("villagers_trade_at_night", true);
        VILLAGERS_STRONGER_DEMAND = registerBoolean("villagers_stronger_demand", true);
        VILLAGERS_NITWITIFY_ON_ZOMBIFICATION = registerBoolean("villagers_nitwitify_on_zombification", true);
        ONE_IRON_GOLEM_PER_MOB = registerBoolean("one_iron_golem_per_mob", true);

        HOSTILE_SNIFFER_PLANTS = registerBoolean("hostile_sniffer_plants", true);
    }

    private static GameRule<Boolean> registerBoolean(String name, boolean defaultValue) {
        return register(name, GameRuleType.BOOL, BoolArgumentType.bool(), Codec.BOOL, defaultValue,
                FeatureFlagSet.of(), GameRuleTypeVisitor::visitBoolean,value -> value ? 1 : 0);
    }

    private static GameRule<Integer> registerInteger(
            final String id, final int defaultValue, final int min, final int max) {
        return register(id, GameRuleType.INT, IntegerArgumentType.integer(min, max), Codec.intRange(min, max),
                defaultValue, FeatureFlagSet.of(), GameRuleTypeVisitor::visitInteger, i -> i);
    }

    private static <T> GameRule<T> register(String name, GameRuleType type,
                                            ArgumentType<T> argumentType, Codec<T> codec, T defaultValue, FeatureFlagSet requiredFeatures,
                                            GameRules.VisitorCaller<T> acceptor, ToIntFunction<T> commandResultSupplier) {
        return Registry.register(
                BuiltInRegistries.GAME_RULE, JabsFixedMobsAndBlocks.id(name),
                new GameRule<>(GameRuleRegistry.JABSFIXEDMOBSANDBLOCKS, type, argumentType, acceptor, codec, commandResultSupplier, defaultValue, requiredFeatures));
    }


    private static GameRule<Boolean> registerBoolean2(String name, GameRuleCategory category, boolean defaultValue) {
        return register2(name, category, GameRuleType.BOOL, BoolArgumentType.bool(), Codec.BOOL, defaultValue,
                FeatureFlagSet.of(), GameRuleTypeVisitor::visitBoolean,value -> value ? 1 : 0);
    }

    private static <T> GameRule<T> register2( String name, GameRuleCategory category, GameRuleType type,
                                              ArgumentType<T> argumentType, Codec<T> codec, T defaultValue,  FeatureFlagSet requiredFeatures,
                                              GameRules.VisitorCaller<T> acceptor, ToIntFunction<T> commandResultSupplier) {
        return Registry.register(
                BuiltInRegistries.GAME_RULE, JabsFixedMobsAndBlocks.id(name),
                new GameRule<>(category, type, argumentType, acceptor, codec, commandResultSupplier, defaultValue, requiredFeatures));
    }
}
