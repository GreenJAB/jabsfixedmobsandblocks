package net.greenjab.jabsfixedmobsandblocks.mixin.other;

import net.greenjab.jabsfixedmobsandblocks.registry.item.NewPhantomMembraneItem;
import net.greenjab.jabsfixedmobsandblocks.registry.other.BaitComponent;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.ComponentRegistry;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.function.Function;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @Shadow
    private static Item registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }
    @Shadow
    private static Item registerItem(String name, Item.Properties properties) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }


    @ModifyArg(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item;", ordinal = 0), slice = @Slice(from =
    @At(value = "CONSTANT", args = "stringValue=spider_eye"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/item/Items;SPIDER_EYE:Lnet/minecraft/world/item/Item;", opcode = Opcodes.PUTSTATIC)), index = 1)
    private static Item.Properties spiderEyeBait(Item.Properties properties) {
        return properties.component(ComponentRegistry.BAIT_POWER, new BaitComponent(1));}

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;)Lnet/minecraft/world/item/Item;", ordinal = 0), slice = @Slice(from =
    @At(value = "CONSTANT", args = "stringValue=fermented_spider_eye"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/item/Items;FERMENTED_SPIDER_EYE:Lnet/minecraft/world/item/Item;", opcode = Opcodes.PUTSTATIC)))
    private static Item fermentedSpiderEyeBait(String name) {
        return registerItem("fermented_spider_eye", new Item.Properties().component(ComponentRegistry.BAIT_POWER, new BaitComponent(2)));}

    @Redirect(method="<clinit>", at = @At( value = "INVOKE", target = "Lnet/minecraft/world/item/Items;registerItem(Ljava/lang/String;)Lnet/minecraft/world/item/Item;", ordinal = 0 ), slice = @Slice(from =
    @At(value = "CONSTANT", args = "stringValue=phantom_membrane"), to =
    @At(value = "FIELD",target = "Lnet/minecraft/world/item/Items;PHANTOM_MEMBRANE:Lnet/minecraft/world/item/Item;", opcode = Opcodes.PUTSTATIC)))
    private static Item edibleMembrane(String name) {
        return registerItem("phantom_membrane", NewPhantomMembraneItem::new, new Item.Properties().stacksTo(64).food(Foods.CHORUS_FRUIT));}
}
