package net.greenjab.jabsfixedmobsandblocks;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.greenjab.jabsfixedmobsandblocks.network.VillagerNeedsPayload;
import net.greenjab.jabsfixedmobsandblocks.registry.registries.*;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class JabsFixedMobsAndBlocks implements ModInitializer {
	public static final String MOD_NAME = "Jabs Fixed Mobs and Blocks";
	public static final String NAMESPACE = "jabsfixedmobsandblocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);
	public static MinecraftServer SERVER = null;

	public static HashMap<Block, Block> corals = new HashMap<>();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_NAME);

		VillagerNeedsPayload.register();
		ComponentRegistry.registerComponent();
		BlockRegistry.registerFireBlocks();
		ItemRegistry.registerItems();
		ItemGroupRegistry.register();
		GameRuleRegistry.registerGameRules();
		LootTableRegistry.registerLootTable();
		MobEffectRegistry.registerMobEffects();
		ParticleRegistry.registerParticles();
		EntityTypeRegistry.registerEntityTypes();
		MemoryRegistry.registerMemories();

		BiomeAdditions.registerBiomeAdds();
		LootTableAdditions.registerLootTableAdds();

		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.MONSTER, EntityType.ENDERMAN, 100, 1, 4);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SAVANNA), MobCategory.CREATURE, EntityType.LLAMA, 10, 4, 4);
	}

	public static ArrayList<ItemStack> getArmor(LivingEntity entity) {
		ArrayList<ItemStack> armor = new ArrayList<>();
		armor.add(entity.getItemBySlot(EquipmentSlot.FEET));
		armor.add(entity.getItemBySlot(EquipmentSlot.LEGS));
		armor.add(entity.getItemBySlot(EquipmentSlot.CHEST));
		armor.add(entity.getItemBySlot(EquipmentSlot.HEAD));
		return armor;
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(NAMESPACE, path);
	}
}