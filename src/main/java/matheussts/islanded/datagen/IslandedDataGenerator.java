package matheussts.islanded.datagen;

import matheussts.islanded.datagen.worldgen.WorldGenProvider;
import matheussts.islanded.datagen.worldgen.features.ConfiguredFeatures;
import matheussts.islanded.datagen.worldgen.features.PlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;

public class IslandedDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(WorldGenProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatures::bootstrap);
    }
}

