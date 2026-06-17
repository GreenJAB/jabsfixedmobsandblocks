package net.greenjab.jabsfixedmobsandblocks.client.models;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.ItemRegistry;
import net.greenjab.jabsfixedmobsandblocks.JabsFixedMobsAndBlocks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

/** Credit: Viola-Siemens */
public class CustomModelLayers {
   public static final ModelLayerLocation VILLAGER_ARMOR_HEAD = register("villager", "outer_armor_head");
    public static final ModelLayerLocation VILLAGER_ARMOR_CHEST = register("villager", "outer_armor_chest");
    public static final ModelLayerLocation VILLAGER_ARMOR_LEGS = register("villager", "outer_armor_legs");
    public static final ModelLayerLocation VILLAGER_ARMOR_FEET = register("villager", "outer_armor_feet");

    public static ModelLayerLocation AZALEA_BOAT = register("boat/azalea", "azalea");
    public static ModelLayerLocation AZALEA_CHEST_BOAT = register("chest_boat/azalea", "azalea");

    private static ModelLayerLocation register(String path, String layer) {
        return new ModelLayerLocation(JabsFixedMobsAndBlocks.id(path), layer);
    }

    public static void onRegisterLayers() {
        ModelLayerRegistry.registerModelLayer(VILLAGER_ARMOR_HEAD, () -> VillagerArmorModel.createBodyLayer(new CubeDeformation(0.5F)));
        ModelLayerRegistry.registerModelLayer(VILLAGER_ARMOR_CHEST, () -> VillagerArmorModel.createBodyLayer(new CubeDeformation(0.5F)));
        ModelLayerRegistry.registerModelLayer(VILLAGER_ARMOR_LEGS, () -> VillagerArmorModel.createBodyLayer(new CubeDeformation(0.0F)));
        ModelLayerRegistry.registerModelLayer(VILLAGER_ARMOR_FEET, () -> VillagerArmorModel.createBodyLayer(new CubeDeformation(0.5F)));
        ModelLayerRegistry.registerModelLayer(AZALEA_BOAT, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(AZALEA_CHEST_BOAT, BoatModel::createChestBoatModel);

        EntityRenderers.register(ItemRegistry.AZALEA_BOAT_ENTITY, context -> new BoatRenderer(context, CustomModelLayers.AZALEA_BOAT));
        EntityRenderers.register(ItemRegistry.AZALEA_CHEST_BOAT_ENTITY,context -> new BoatRenderer(context, CustomModelLayers.AZALEA_CHEST_BOAT));

    }
}
