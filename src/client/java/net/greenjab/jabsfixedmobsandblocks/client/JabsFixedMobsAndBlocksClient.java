package net.greenjab.jabsfixedmobsandblocks.client;

import net.fabricmc.api.ClientModInitializer;
import net.greenjab.jabsfixedmobsandblocks.client.models.CustomModelLayers;
import net.greenjab.jabsfixedmobsandblocks.client.registries.CustomEntityModelLayerRegistry;
import net.greenjab.jabsfixedmobsandblocks.client.registries.EntityRendererRegistry;
import net.minecraft.client.Minecraft;

public class JabsFixedMobsAndBlocksClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientSyncHandler.init();
		CustomModelLayers.onRegisterLayers();
		EntityRendererRegistry.registerEntityRenderer();
		CustomEntityModelLayerRegistry.registerEntityModelLayer();
	}

	public static boolean usingCustomContainers() {
		return (Minecraft.getInstance().getResourcePackRepository().getSelectedPacks().stream().anyMatch(pack -> pack.location().id().toLowerCase().contains("recolourful_containers")));
	}
}