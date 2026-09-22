package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.greenjab.jabsfixedmobsandblocks.registry.block.*;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.BlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
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
import org.spongepowered.asm.mixin.injection.Slice;
import java.util.function.Function;

@Mixin(Blocks.class)
public abstract class BlocksMixin {

    @WrapOperation(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;SNOW:Lnet/minecraft/references/BlockItemId;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;SNOW:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block snow(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, Operation<Block> original) {
        BlockRegistry.registerBlocks();
        return register(id, NewSnowBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).replaceable().forceSolidOff().randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((state, _, _) -> state.getValue(SnowLayerBlock.LAYERS) >= 8).pushReaction(PushReaction.DESTROY));}

    @WrapOperation(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;AMETHYST_BLOCK:Lnet/minecraft/references/BlockItemId;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;AMETHYST_BLOCK:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block powerAmethystBlock(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, Operation<Block> original) {
        return register(id, NewAmethystBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());}

    @WrapOperation(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;TORCHFLOWER:Lnet/minecraft/references/BlockItemId;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;TORCHFLOWER:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block newTorchFlower(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, Operation<Block> original) {
        return register(id, new_properties -> new NewTorchFlowerBlock(MobEffects.NIGHT_VISION, 5.0F, new_properties),
                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).noCollision().instabreak()
                        .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
                        .pushReaction(PushReaction.DESTROY).lightLevel(_ -> 13));}

    @WrapOperation(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;PITCHER_CROP:Lnet/minecraft/references/BlockItemId;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;PITCHER_CROP:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block newPitcherCrop(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, Operation<Block> original) {
        return register(id, NewPitcherCropBlock::new,
                BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollision().instabreak()
                        .randomTicks().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY));}

    @WrapOperation(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/references/BlockItemId;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", ordinal = 0), slice = @Slice( from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/BlockItemIds;PITCHER_PLANT:Lnet/minecraft/references/BlockItemId;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/level/block/Blocks;PITCHER_PLANT:Lnet/minecraft/world/level/block/Block;", opcode = Opcodes.PUTSTATIC)))
    private static Block newPitcherPod(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties,
                                       Operation<Block> original) {
        return register(id, NewPitcherPlantBlock::new,
                BlockBehaviour.Properties.of().mapColor(DyeColor.CYAN).noCollision().instabreak().sound(SoundType.CROP)
                        .offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY));}


    @Unique private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register(id.block(), factory, properties);
    }

    @Unique private static Block register(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }
}
