package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.greenjab.jabsfixedmobsandblocks.registry.item.PatinaItem;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.registry.other.BaitComponent;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemRegistry {


    public static final Item PATINA = register("patina", PatinaItem::new, new Item.Properties());
    public static final Item REDSTONE_LANTERN = register(BlockRegistry.REDSTONE_LANTERN);
    public static final Item DISPENSER_MINECART = register(
            "dispenser_minecart", settings -> new MinecartItem(EntityTypeRegistry.DISPENCER_MINECART_ENTITY_TYPE, settings), new Item.Properties().stacksTo(1)
    );
    public static final Item GOLDEN_FERMENTED_SPIDER_EYE = register("golden_fermented_spider_eye", new Item.Properties().component(ComponentRegistry.BAIT_POWER, new BaitComponent(3)));

    public static final Item AZALEA_PLANKS = register(BlockRegistry.AZALEA_PLANKS);
    public static final Item AZALEA_LOG = register(BlockRegistry.AZALEA_LOG);
    public static final Item STRIPPED_AZALEA_LOG = register(BlockRegistry.STRIPPED_AZALEA_LOG);
    public static final Item STRIPPED_AZALEA_WOOD = register(BlockRegistry.STRIPPED_AZALEA_WOOD);
    public static final Item AZALEA_WOOD = register(BlockRegistry.AZALEA_WOOD);
    public static final Item AZALEA_SLAB = register(BlockRegistry.AZALEA_SLAB);
    public static final Item AZALEA_FENCE = register(BlockRegistry.AZALEA_FENCE);
    public static final Item AZALEA_STAIRS = register(BlockRegistry.AZALEA_STAIRS);
    public static final Item AZALEA_BUTTON = register(BlockRegistry.AZALEA_BUTTON);
    public static final Item AZALEA_PRESSURE_PLATE = register(BlockRegistry.AZALEA_PRESSURE_PLATE);
    public static final Item AZALEA_DOOR = register(BlockRegistry.AZALEA_DOOR, DoubleHighBlockItem::new);
    public static final Item AZALEA_TRAPDOOR = register(BlockRegistry.AZALEA_TRAPDOOR);
    public static final Item AZALEA_FENCE_GATE = register(BlockRegistry.AZALEA_FENCE_GATE);
    public static final Item AZALEA_SHELF = register(BlockRegistry.AZALEA_SHELF);

    public static final Item AZALEA_SIGN = register(
            BlockRegistry.AZALEA_SIGN, (block, settings) -> new SignItem(block, BlockRegistry.AZALEA_WALL_SIGN, settings), new Item.Properties().stacksTo(16)
    );
    public static final Item AZALEA_HANGING_SIGN = register(
            BlockRegistry.AZALEA_HANGING_SIGN,
            (block, settings) -> new HangingSignItem(block, BlockRegistry.AZALEA_WALL_HANGING_SIGN, settings),
            new Item.Properties().stacksTo(16)
    );

    public static final EntityType<Boat> AZALEA_BOAT_ENTITY = register2(
            "azalea_boat",
            EntityType.Builder.of(getBoatFactory(() -> ItemRegistry.AZALEA_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );
    public static final EntityType<ChestBoat> AZALEA_CHEST_BOAT_ENTITY = register2(
            "azalea_chest_boat",
            EntityType.Builder.of(getChestBoatFactory(() -> ItemRegistry.AZALEA_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );

    public static final Item AZALEA_BOAT = register(
            "azalea_boat", settings -> new BoatItem(AZALEA_BOAT_ENTITY, settings), new Item.Properties().stacksTo(1)
    );
    public static final Item AZALEA_CHEST_BOAT = register(
            "azalea_chest_boat", settings -> new BoatItem(AZALEA_CHEST_BOAT_ENTITY, settings), new Item.Properties().stacksTo(1)
    );

    private static EntityType.EntityFactory<Boat> getBoatFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new Boat(type, world, itemSupplier);
    }
    private static EntityType.EntityFactory<ChestBoat> getChestBoatFactory(Supplier<Item> itemSupplier) {
        return (type, world) -> new ChestBoat(type, world, itemSupplier);
    }
    private static <T extends Entity> EntityType<T> register2(String id, EntityType.Builder<T> type) {
        return register2(keyOf2(id), type);
    }
    private static ResourceKey<EntityType<?>> keyOf2(String id) {
        return ResourceKey.create(Registries.ENTITY_TYPE, JabsFixedMobsAndBlocks.id(id));
    }
    private static <T extends Entity> EntityType<T> register2(ResourceKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }


    private static Item register(final String name, final Item.Properties properties) {
        return register(name, Item::new, properties);
    }
    public static Item register(String id, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return register(keyOf(id), factory, settings);
    }
    private static ResourceKey<Item> keyOf(String id) {
        return ResourceKey.create(Registries.ITEM, JabsFixedMobsAndBlocks.id(id));
    }
    public static Item register(ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties settings) {
        Item item = factory.apply(settings.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
    public static Item register(Block block) {
        return register(block, BlockItem::new, new Item.Properties());
    }

    public static Item register(Block block, BiFunction<Block, Item.Properties, Item> factory) {
        return register(block, factory, new Item.Properties());
    }
    public static Item register(Block block, BiFunction<Block, Item.Properties, Item> factory, Item.Properties settings) {
        return register(
                keyOf(block.builtInRegistryHolder().key()),
                itemSettings -> factory.apply(block, itemSettings),
                settings.useBlockDescriptionPrefix()
        );
    }
    private static ResourceKey<Item> keyOf(ResourceKey<Block> blockKey) {
        return ResourceKey.create(Registries.ITEM, blockKey.identifier());
    }

    public static void registerItems() {
        System.out.println("register Items");
    }
}
