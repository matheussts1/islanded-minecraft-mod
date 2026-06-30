package matheussts.islanded.biomes;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

//changing the profundity of sea
public class SeaChanger {

    //getting the unique key of overworld
    public static final ResourceKey<NoiseGeneratorSettings> OVERWORLD = ResourceKey.create(
            Registries.NOISE_SETTINGS,
            ResourceLocation.withDefaultNamespace("overworld")
    );

    //getting the present sea profundity and multiplying by 10 (fuck it)
    public static void topographyInterceptor() {
        DynamicRegistrySetupCallback.EVENT.register(registryView -> {
            registryView.registerEntryAdded(Registries.NOISE_SETTINGS, ((rawId, id, object) -> {
                if (OVERWORLD.location().equals(id)) {
                    DensityFunction profundity, newProfundity, finalProfundity;

                    profundity = object.noiseRouter().continents();
                    newProfundity = DensityFunctions.mul(profundity, DensityFunctions.constant(10.0));
                    finalProfundity = DensityFunctions.rangeChoice(profundity, -100000.0, 0.0, newProfundity, profundity);
                }
                }));
            }
        );
    }
}
