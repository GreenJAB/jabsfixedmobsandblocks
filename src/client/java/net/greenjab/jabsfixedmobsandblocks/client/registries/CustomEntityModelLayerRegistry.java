package net.greenjab.jabsfixedmobsandblocks.client.registries;

import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class CustomEntityModelLayerRegistry {

    public static final ModelLayerLocation DISPENSER_MINECART = new ModelLayerLocation(JabsFixedMobsAndBlocks.id("dispenser_minecart"), "main");

    public static void registerEntityModelLayer() {
        System.out.println("register EntityModelLayer");
    }
}
