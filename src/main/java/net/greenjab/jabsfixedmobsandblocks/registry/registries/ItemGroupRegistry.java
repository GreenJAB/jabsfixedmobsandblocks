package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupRegistry {

    public static final CreativeModeTab JABS_FIXED_MOBS_AND_BLOCKS = FabricCreativeModeTab.builder().title(Component.translatable("itemgroup.jabsfixedmobsandblocks"))
            .icon( () -> new ItemStack(ItemRegistry.AZALEA_PLANKS))
            .displayItems(
                     (_, entries) -> {

                        entries.accept(ItemRegistry.PATINA);
                        entries.accept(ItemRegistry.REDSTONE_LANTERN);
                         entries.accept(ItemRegistry.DISPENSER_MINECART);

                         entries.accept(BlockRegistry.AZALEA_PLANKS);
                         entries.accept(BlockRegistry.AZALEA_LOG);
                         entries.accept(BlockRegistry.STRIPPED_AZALEA_LOG);
                         entries.accept(BlockRegistry.AZALEA_WOOD);
                         entries.accept(BlockRegistry.STRIPPED_AZALEA_WOOD);
                         entries.accept(ItemRegistry.AZALEA_SIGN);
                         entries.accept(ItemRegistry.AZALEA_HANGING_SIGN);
                         entries.accept(BlockRegistry.AZALEA_PRESSURE_PLATE);
                         entries.accept(BlockRegistry.AZALEA_TRAPDOOR);
                         entries.accept(BlockRegistry.AZALEA_BUTTON);
                         entries.accept(BlockRegistry.AZALEA_STAIRS);
                         entries.accept(BlockRegistry.AZALEA_SLAB);
                         entries.accept(BlockRegistry.AZALEA_FENCE_GATE);
                         entries.accept(BlockRegistry.AZALEA_FENCE);
                         entries.accept(BlockRegistry.AZALEA_DOOR);
                         entries.accept(ItemRegistry.AZALEA_BOAT);
                         entries.accept(ItemRegistry.AZALEA_CHEST_BOAT);
                         entries.accept(ItemRegistry.AZALEA_SHELF);
                    }).build();


    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, "jabsfixedmobsandblocks", JABS_FIXED_MOBS_AND_BLOCKS);
    }
}
