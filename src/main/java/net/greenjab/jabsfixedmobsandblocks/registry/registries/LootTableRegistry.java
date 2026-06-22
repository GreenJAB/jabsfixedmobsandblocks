package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.registry.other.WanderingTraderSpecialLootFunction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

public class LootTableRegistry {
    public static final ResourceKey<LootTable> SUPER_CHARGED_CREEPER_PLAYER_LOOT_TABLE = registerLoot_Table("gameplay/super_charged_creeper_player");

    private static ResourceKey<LootTable> registerLoot_Table(String id) {
        return registerLootTable(ResourceKey.create(Registries.LOOT_TABLE, JabsFixedMobsAndBlocks.id(id)));
    }
    private static ResourceKey<LootTable> registerLootTable(ResourceKey<LootTable> key) {
        if (BuiltInLootTables.LOCATIONS.add(key)) {
            return key;
        } else {
            throw new IllegalArgumentException(key.identifier() + " is already a registered built-in loot table");
        }
    }

    public static void registerLootTable() {
        System.out.println("register LootTables");
        Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, JabsFixedMobsAndBlocks.id("wandering_trader_special"), WanderingTraderSpecialLootFunction.CODEC);
    }
}
