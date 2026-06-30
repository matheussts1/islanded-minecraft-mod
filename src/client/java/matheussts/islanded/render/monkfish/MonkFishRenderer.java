package matheussts.islanded.render.monkfish;


import matheussts.islanded.layers.ModEntityModelLayers;
import matheussts.islanded.model.MonkFishModel;
import matheussts.islanded.entities.fishs.monkfish.MonkFish;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class MonkFishRenderer extends MobRenderer<MonkFish, MonkFishRenderState, MonkFishModel<MonkFishRenderState>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("islanded", "textures/entity/fish/monkfish.png");

    public MonkFishRenderer(EntityRendererProvider.Context context) {
        super(context, new MonkFishModel(context.bakeLayer(ModEntityModelLayers.MONK_FISH)), 0.3f);
    }

    @Override
    public @NotNull MonkFishRenderState createRenderState() {
        return new MonkFishRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MonkFishRenderState state) {
        return TEXTURE;
    }
}