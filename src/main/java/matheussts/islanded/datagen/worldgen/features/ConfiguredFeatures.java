package matheussts.islanded.datagen.worldgen.features;


import matheussts.islanded.items.materials.RegisterMaterials;
import matheussts.islanded.net.Islanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

// modifying the worldgen
public class ConfiguredFeatures {

    //putting all the pieces together and finally registering my change
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(
                PLASTIC_BLOCK_CONFIGURED_KEY,
                new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(plasticBlockOreConfig, 10))
        );
    }
    //creating the id for my change, which is adding my plastic block to world generation
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLASTIC_BLOCK_CONFIGURED_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(Islanded.MOD_ID, "plastic_ore")
    );

    //getting everything that can be replaceable, for example: stones, dirt, etc...
    static RuleTest templateReplaceableRule = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

    //doing the change, getting what is replaceable, and changing to my plastic block
    static List<OreConfiguration.TargetBlockState> plasticBlockOreConfig = List.of(OreConfiguration.target(templateReplaceableRule, RegisterMaterials.PLASTIC_BLOCK.defaultBlockState()));
    }

