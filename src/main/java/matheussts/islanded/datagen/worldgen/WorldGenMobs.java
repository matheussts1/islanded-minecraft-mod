package matheussts.islanded.datagen.worldgen;

import matheussts.islanded.entities.ModEntities;
import matheussts.islanded.entities.fishs.clownfish.ClownFish;
import matheussts.islanded.entities.fishs.monkfish.MonkFish;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.function.Predicate;

import static net.fabricmc.fabric.api.biome.v1.BiomeSelectors.includeByKey;

public class WorldGenMobs {

    public static void changingSeaGeneration() {
        MobCategory category = MobCategory.WATER_AMBIENT;
        Predicate<BiomeSelectionContext> ocean = BiomeSelectors.includeByKey(Biomes.OCEAN);
        EntityType<MonkFish> monkfish = ModEntities.MONK_FISH;
        EntityType<ClownFish> clownfish = ModEntities.CLOWN_FISH;

        BiomeModifications.addSpawn(ocean, category, monkfish, 30, 2, 4);
        BiomeModifications.addSpawn(ocean, category, clownfish, 30, 2, 4);
    }
}
