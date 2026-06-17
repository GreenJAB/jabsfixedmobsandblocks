package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.greenjab.jabsfixedmobsandblocks.registry.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Function;

import static net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks.corals;
import static net.minecraft.world.level.block.Blocks.*;

public class BlockRegistry {

    static BlockSetType AZALEA_BLOCKSETTYPE = BlockSetType.register(new BlockSetType("azalea"));
    static WoodType AZALEA_WOODTYPE = WoodType.register(new WoodType("azalea", AZALEA_BLOCKSETTYPE));

    public static final Block AZALEA_PLANKS = register(
            "azalea_planks",
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block AZALEA_LOG = register("azalea_log", RotatedPillarBlock::new, logProperties(MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_LIGHT_GREEN, SoundType.WOOD));
    public static final Block STRIPPED_AZALEA_LOG = register(
            "stripped_azalea_log", RotatedPillarBlock::new, logProperties(MapColor.COLOR_LIGHT_GREEN, MapColor.COLOR_LIGHT_GREEN, SoundType.WOOD)
    );
    public static final Block AZALEA_WOOD = register(
            "azalea_wood",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block STRIPPED_AZALEA_WOOD = register(
            "stripped_azalea_wood",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block AZALEA_SIGN = register(
            "azalea_sign",
           settings -> new StandingSignBlock(AZALEA_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
    );
    public static final Block AZALEA_WALL_SIGN = register(
            "azalea_wall_sign",
            settings -> new WallSignBlock(AZALEA_WOODTYPE, settings),
            copyLootTable(AZALEA_SIGN).mapColor(MapColor.COLOR_LIGHT_GREEN).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
    );
    public static final Block AZALEA_HANGING_SIGN = register(
            "azalea_hanging_sign",
            settings -> new CeilingHangingSignBlock(AZALEA_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
    );
    public static final Block AZALEA_WALL_HANGING_SIGN = register(
            "azalea_wall_hanging_sign",
           settings -> new WallHangingSignBlock(AZALEA_WOODTYPE, settings),
            copyLootTable(AZALEA_HANGING_SIGN).mapColor(MapColor.COLOR_LIGHT_GREEN).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava()
    );

    public static final Block AZALEA_PRESSURE_PLATE = register(
            "azalea_pressure_plate",
            settings -> new PressurePlateBlock(AZALEA_BLOCKSETTYPE, settings),
            BlockBehaviour.Properties.of()
                    .mapColor(AZALEA_PLANKS.defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(0.5F)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final Block AZALEA_TRAPDOOR = register(
            "azalea_trapdoor",
            settings -> new TrapDoorBlock(AZALEA_BLOCKSETTYPE, settings),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .ignitedByLava()
    );
    public static final Block AZALEA_BUTTON = register(
            "azalea_button",settings -> new ButtonBlock(AZALEA_BLOCKSETTYPE, 30, settings), buttonProperties()
    );
    public static final Block AZALEA_STAIRS = registerOldStairsBlock("azalea_stairs", AZALEA_PLANKS);
    public static final Block AZALEA_SLAB = register(
            "azalea_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block AZALEA_FENCE_GATE = register(
            "azalea_fence_gate",
            settings -> new FenceGateBlock(AZALEA_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(AZALEA_PLANKS.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava()
    );
    public static final Block AZALEA_FENCE = register(
            "azalea_fence",
            FenceBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(AZALEA_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .ignitedByLava()
                    .sound(SoundType.WOOD)
    );
    public static final Block AZALEA_DOOR = register(
            "azalea_door",
            settings -> new DoorBlock(AZALEA_BLOCKSETTYPE, settings),
            BlockBehaviour.Properties.of()
                    .mapColor(AZALEA_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final Block AZALEA_SHELF = register(
            "azalea_shelf",
            ShelfBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(AZALEA_PLANKS.defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2f,3.0F)
                    .ignitedByLava()
                    .sound(SoundType.SHELF)
    );

    public static final Block COPPER_FIRE = register(
            "copper_fire",
            CopperFireBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .replaceable()
                    .noCollision()
                    .instabreak()
                    .lightLevel(_ -> 10)
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final Block REDSTONE_LANTERN = register(
            "redstone_lantern",
            RedstoneLanternBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .forceSolidOn()
                    .strength(3.5F)
                    .sound(SoundType.LANTERN)
                    .lightLevel(litBlockEmission(10))
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    );

    public static void registerBlocks() {
        System.out.println("register Blocks");
    }

    public static void registerFireBlocks() {
        FireBlock fireBlock = (FireBlock)Blocks.FIRE;
        fireBlock.setFlammable(AZALEA_PLANKS, 5, 20);
        fireBlock.setFlammable(AZALEA_SLAB, 5, 20);
        fireBlock.setFlammable(AZALEA_FENCE_GATE, 5, 20);
        fireBlock.setFlammable(AZALEA_FENCE, 5, 20);
        fireBlock.setFlammable(AZALEA_STAIRS, 5, 20);
        fireBlock.setFlammable(AZALEA_LOG, 5, 5);
        fireBlock.setFlammable(AZALEA_WOOD, 5, 5);
        fireBlock.setFlammable(STRIPPED_AZALEA_LOG, 5, 5);
        fireBlock.setFlammable(STRIPPED_AZALEA_LOG, 5, 5);

        StrippableBlockRegistry.register(AZALEA_LOG, STRIPPED_AZALEA_LOG);
        StrippableBlockRegistry.register(AZALEA_WOOD, STRIPPED_AZALEA_WOOD);
    }

    private static Block register(String id, BlockBehaviour.Properties settings) {
        return register(id, Block::new, settings);
    }
    private static Block register(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return register(keyOf(id), factory, settings);
    }
    private static ResourceKey<Block> keyOf(String id) {
        return ResourceKey.create(Registries.BLOCK, JabsFixedMobsAndBlocks.id(id));
    }
    public static Block register(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        Block block = factory.apply(settings.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static Block registerOldStairsBlock(String id, Block base) {
        return register(id, settings -> new StairBlock(base.defaultBlockState(), settings), BlockBehaviour.Properties.ofLegacyCopy(base));
    }
    private static BlockBehaviour.Properties copyLootTable(Block block) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.of().overrideLootTable(block.getLootTable());
        settings = settings.overrideDescription(block.getDescriptionId());
        return settings;
    }


    @Unique
    public static void addCoral() {
        corals.clear();
        corals.put(Blocks.DEAD_TUBE_CORAL, Blocks.TUBE_CORAL);
        corals.put(Blocks.DEAD_BRAIN_CORAL, Blocks.BRAIN_CORAL);
        corals.put(Blocks.DEAD_BUBBLE_CORAL, Blocks.BUBBLE_CORAL);
        corals.put(Blocks.DEAD_FIRE_CORAL, Blocks.FIRE_CORAL);
        corals.put(Blocks.DEAD_HORN_CORAL, Blocks.HORN_CORAL);
        corals.put(Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK);
        corals.put(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK);
        corals.put(Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK);
        corals.put(Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK);
        corals.put(Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK);
        corals.put(Blocks.DEAD_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN);
        corals.put(Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN);
        corals.put(Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN);
        corals.put(Blocks.DEAD_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN);
        corals.put(Blocks.DEAD_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN);
        corals.put(Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN);
        corals.put(Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN);
        corals.put(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN);
        corals.put(Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN);
        corals.put(Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN);
    }
}
