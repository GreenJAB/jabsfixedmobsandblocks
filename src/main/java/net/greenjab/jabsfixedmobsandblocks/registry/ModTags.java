package net.greenjab.jabsfixedmobsandblocks.registry;

import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.TradeSets;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> ORES = TagKey.create(Registries.BLOCK, JabsFixedMobsAndBlocks.id("ores"));

    public static final TagKey<Item> STAINED_GLASS = TagKey.create(Registries.ITEM, JabsFixedMobsAndBlocks.id("stained_glass"));
    public static final TagKey<Item> STAINED_GLASS_PANE = TagKey.create(Registries.ITEM, JabsFixedMobsAndBlocks.id("stained_glass_pane"));

    public static final ResourceKey<TradeSet> WANDERING_TRADER_SPECIAL = TradeSets.resourceKey("wandering_trader/special");
}
