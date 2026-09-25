package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

import static net.greenjab.jabsfixedmobsandblocks.registry.ModTags.FISHING_TREASURE_EBOOKS;
import static net.greenjab.jabsfixedmobsandblocks.registry.registries.LootTableRegistry.FIXED_FISHING_TREASURE_LOOT_TABLE;

public class LootTableAdditions {

    public static void registerLootTableAdds() {
        System.out.println("register LootTableAdds");

        LootTableEvents.MODIFY.register((key, tableBuilder, _, holder) -> {
            HolderLookup.RegistryLookup<LootTable> lootTables = holder.lookupOrThrow(Registries.LOOT_TABLE);
            if (key==BuiltInLootTables.CHARGED_CREEPER) {
                tableBuilder.pool(LootPool.lootPool().add(NestedLootTable.lootTableReference(lootTables.getOrThrow(LootTableRegistry.CHARGED_CREEPER_PLAYER_LOOT_TABLE)).when(LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityTypes.PLAYER))))).build());
            } else if (key== EntityTypes.CREEPER.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_PIGSTEP))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityTypes.PIGLIN))).build());
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_OTHERSIDE))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().located(LocationPredicate.Builder.inDimension(Level.END)))).build());
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_BOUNCE))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityTypes.SULFUR_CUBE))).build());
            } else if (key==EntityTypes.SNIFFER.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_RELIC))
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(holder.lookupOrThrow(Registries.ENTITY_TYPE), EntityTypes.CREEPER))).build());
            } else if (key==BuiltInLootTables.SNIFFER_DIGGING) {
                tableBuilder.modifyPools(builder ->
                        builder.add(LootItem.lootTableItem(Items.GOLDEN_DANDELION))
                                .add(NestedLootTable.lootTableReference(lootTables.getOrThrow(LootTableRegistry.SNIFFER_EXTRA))));
            } else if (key==EntityTypes.WARDEN.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.DISC_FRAGMENT_5)).build());
            } else if (key==BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_CONSUMABLES) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CREATOR))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_CREATOR_MUSIC_BOX))
                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_PRECIPICE))
                        .add(LootItem.lootTableItem(Items.AIR).setWeight(3))
                        .build());
            } else if (key==EntityTypes.ELDER_GUARDIAN.getDefaultLootTable().get()) {
                tableBuilder.pool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(2))
                        .add(LootItem.lootTableItem(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE))
                        .add(LootItem.lootTableItem(Items.AIR))
                        .build());
            } else if (key==FIXED_FISHING_TREASURE_LOOT_TABLE && FabricLoader.getInstance().isModLoaded("jabsfixedenchanting")) {
                HolderLookup.RegistryLookup<Enchantment> enchantments = holder.lookupOrThrow(Registries.ENCHANTMENT);
                tableBuilder.modifyPools(builder -> builder
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(10)
                                .apply(new EnchantRandomlyFunction.Builder().withOneOf(enchantments.getOrThrow(FISHING_TREASURE_EBOOKS)))));
            }
        });
    }

    public static ResourceKey<PlacedFeature> of(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, JabsFixedMobsAndBlocks.id(id));
    }
}
