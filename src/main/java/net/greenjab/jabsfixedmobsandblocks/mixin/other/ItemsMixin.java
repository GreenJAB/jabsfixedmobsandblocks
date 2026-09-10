package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.greenjab.jabsfixedmobsandblocks.registry.other.BaitComponent;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.ComponentRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @Shadow private static Item registerItem(ResourceKey<Item> id, Item.Properties properties) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @ModifyArg(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;", ordinal = 0), slice = @Slice(from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;SPIDER_EYE:Lnet/minecraft/resources/ResourceKey;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/item/Items;SPIDER_EYE:Lnet/minecraft/world/item/Item;", opcode = Opcodes.PUTSTATIC)), index = 1)
    private static Item.Properties spiderEyeBait(Item.Properties properties) {
        return properties.component(ComponentRegistry.BAIT_POWER, new BaitComponent(1));}

    @WrapOperation(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/Item;", ordinal = 0), slice = @Slice(from =
    @At(value = "FIELD", target = "Lnet/minecraft/references/ItemIds;FERMENTED_SPIDER_EYE:Lnet/minecraft/resources/ResourceKey;", opcode = Opcodes.GETSTATIC), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/item/Items;FERMENTED_SPIDER_EYE:Lnet/minecraft/world/item/Item;", opcode = Opcodes.PUTSTATIC)))
    private static Item fermentedSpiderEyeBait(ResourceKey<Item> id, Operation<Item> original) {
        return registerItem(id, new Item.Properties().component(ComponentRegistry.BAIT_POWER, new BaitComponent(2)));}
}
