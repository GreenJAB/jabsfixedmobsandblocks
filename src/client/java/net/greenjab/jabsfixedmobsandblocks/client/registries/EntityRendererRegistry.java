package net.greenjab.jabsfixedmobsandblocks.client.registries;

import net.greenjab.jabsfixedmobsandblocks.registry.registries.EntityTypeRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.MinecartRenderer;

public class EntityRendererRegistry {

    public static void registerEntityRenderer() {
        System.out.println("register EntityRenderer");

        EntityRenderers.register(EntityTypeRegistry.DISPENCER_MINECART_ENTITY_TYPE, context -> new MinecartRenderer(context, CustomEntityModelLayerRegistry.DISPENSER_MINECART));
    }
}
