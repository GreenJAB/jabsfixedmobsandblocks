package net.greenjab.jabsfixedmobsandblocks.registry.registries;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

public class BiomeAdditions {

    public static void registerBiomeAdds() {
        System.out.println("register BiomeAdds");

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.MONSTER, EntityType.ENDERMAN, 100, 1, 4);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SAVANNA), MobCategory.CREATURE, EntityType.LLAMA, 10, 4, 4);
    }
}
