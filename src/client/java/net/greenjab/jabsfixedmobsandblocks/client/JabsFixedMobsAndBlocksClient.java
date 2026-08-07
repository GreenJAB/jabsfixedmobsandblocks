package net.greenjab.jabsfixedmobsandblocks.client;

import net.fabricmc.api.ClientModInitializer;
import net.greenjab.jabsfixedmobsandblocks.client.models.CustomModelLayers;
import net.greenjab.jabsfixedmobsandblocks.client.registries.CustomEntityModelLayerRegistry;
import net.greenjab.jabsfixedmobsandblocks.client.registries.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;

public class JabsFixedMobsAndBlocksClient implements ClientModInitializer {

	public static OptionInstance<Boolean> villagersSpeak = OptionInstance.createBoolean("options.chat.villagersSpeak", true);

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