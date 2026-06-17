package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import net.greenjab.jabsfixedmobsandblocks.registry.block.*;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.BlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.function.Function;

@Mixin(Blocks.class)
public abstract class BlocksMixin {

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "CONSTANT", args = "stringValue=snow"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;SNOW:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block snow(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        BlockRegistry.registerBlocks();
        return register("snow", NewSnowBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((state, _, _) -> state.getValue(SnowLayerBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY));}

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "CONSTANT", args = "stringValue=amethyst_block"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;AMETHYST_BLOCK:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block powerAmethystBlock(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register("amethyst_block", NewAmethystBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());}

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "CONSTANT", args = "stringValue=torchflower"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;TORCHFLOWER:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block newTorchFlower(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register("torchflower", new_properties -> new NewTorchFlowerBlock(MobEffects.NIGHT_VISION, 5.0F, new_properties),
                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).noCollision().instabreak()
                        .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
                        .pushReaction(PushReaction.DESTROY).lightLevel(_ -> 13));}

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "CONSTANT", args = "stringValue=pitcher_crop"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;PITCHER_CROP:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block newPitcherCrop(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register("pitcher_crop", NewPitcherCropBlock::new,
                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollision().instabreak()
                        .randomTicks().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));}

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "CONSTANT", args = "stringValue=pitcher_plant"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;PITCHER_PLANT:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block newPitcherPod(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register("pitcher_plant", NewPitcherPlantBlock::new,
                BlockBehaviour.Properties.of().mapColor(DyeColor.CYAN).noCollision().instabreak().sound(SoundType.CROP)
                        .offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY));}


    @Unique
    private static Block register(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register(keyOf(id), factory, properties);
    }
    @Unique
    private static ResourceKey<Block> keyOf(String id) {
        return ResourceKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(id));
    }
    @Unique
    private static Block register(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }
}
