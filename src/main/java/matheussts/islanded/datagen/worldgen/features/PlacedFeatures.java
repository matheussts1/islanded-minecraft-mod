package matheussts.islanded.datagen.worldgen.features;

import matheussts.islanded.net.Islanded;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static matheussts.islanded.datagen.worldgen.features.ConfiguredFeatures.plasticBlockOreConfig;

//defining how the feature is going to be placed
public class PlacedFeatures {

    //this makes the class look the configured feature
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(
                PLASTIC_BLOCK_PLACED_KEY,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(ConfiguredFeatures.PLASTIC_BLOCK_CONFIGURED_KEY),
                        plasticBlockVeinModifiers
                )
        );
    }

    //getting the id of the change
    public static final ResourceKey<PlacedFeature> PLASTIC_BLOCK_PLACED_KEY =
            ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Islanded.MOD_ID, "plastic_ore"));

    //attributes to set when spawning the feature
    static List<PlacementModifier> plasticBlockVeinModifiers = List.of(
            CountPlacement.of(20),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128)),
            BiomeFilter.biome()
    );
}
