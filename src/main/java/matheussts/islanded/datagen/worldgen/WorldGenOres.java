package matheussts.islanded.datagen.worldgen;

import matheussts.islanded.datagen.worldgen.features.PlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

//generating the new ores in the world
public class WorldGenOres {
    public static void generateOres() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                PlacedFeatures.PLASTIC_BLOCK_PLACED_KEY
        );
    }
}
