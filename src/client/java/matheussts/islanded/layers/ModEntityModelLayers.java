package matheussts.islanded.layers;

import matheussts.islanded.model.ClownFishModel;
import matheussts.islanded.model.MonkFishModel;
import matheussts.islanded.net.Islanded;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModEntityModelLayers {
    public static final ModelLayerLocation MONK_FISH = createMain("monk_fish");
    public static final ModelLayerLocation CLOWN_FISH = createMain("clown_fish");

    private static ModelLayerLocation createMain(String name) {
    return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Islanded.MOD_ID, name), "main");
    }

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.MONK_FISH, MonkFishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CLOWN_FISH, ClownFishModel::createBodyLayer);
    }
}

