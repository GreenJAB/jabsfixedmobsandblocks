package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.greenjab.jabsfixedmobsandblocks.registry.other.BaitComponent;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class ComponentRegistry {

    public static final DataComponentType<BaitComponent> BAIT_POWER = registerComponent("bait_power", (builder) -> builder.persistent(BaitComponent.CODEC).networkSynchronized(BaitComponent.PACKET_CODEC).cacheEncoding());
    private static <T> DataComponentType<T> registerComponent(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id, builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerComponent() {
        System.out.println("register Components");
    }
}
