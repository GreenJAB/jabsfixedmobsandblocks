package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityTypePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class LootTableAdditions {

    public static void registerLootTableAdds() {
        System.out.println("register LootTableAdds");

        LootTableEvents.MODIFY.register((key, tableBuilder, source, holder) -> {
            if (key==BuiltInLootTables.CHARGED_CREEPER) {
                LootItemCondition.Builder predicate = LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityType.PLAYER)));
                LootPool.Builder poolBuilder = LootPool.lootPool().add(NestedLootTable.lootTableReference(LootTableRegistry.SUPER_CHARGED_CREEPER_PLAYER_LOOT_TABLE).when(predicate));
                tableBuilder.pool(poolBuilder.build());
            }
            if (key==EntityType.CREEPER.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_PIGSTEP))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityType.PIGLIN))).build());

                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_OTHERSIDE))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityType.SHULKER))).build());
            }
            if (key==EntityType.SNIFFER.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityType.CREEPER))).build());
            }
            if (key==EntityType.WARDEN.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_5))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityType.CREEPER))).build());
            }
        });
    }

    public static ResourceKey<PlacedFeature> of(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, JabsFixedMobsAndBlocks.id(id));
    }
}
