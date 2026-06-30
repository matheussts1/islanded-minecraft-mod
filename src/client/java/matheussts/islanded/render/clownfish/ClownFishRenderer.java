package matheussts.islanded.render.clownfish;

import matheussts.islanded.entities.fishs.clownfish.ClownFish;
import matheussts.islanded.layers.ModEntityModelLayers;
import matheussts.islanded.model.ClownFishModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class ClownFishRenderer extends MobRenderer<ClownFish, ClownFishRenderState, ClownFishModel<ClownFishRenderState>> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("islanded", "textures/entity/fish/clown_fish.png");

    public ClownFishRenderer(EntityRendererProvider.Context context) {
        super(context, new ClownFishModel<>(context.bakeLayer(ModEntityModelLayers.CLOWN_FISH)), 0.3f);
    }

    @Override
    public @NotNull ClownFishRenderState createRenderState() {
        return new ClownFishRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClownFishRenderState state) {
        return TEXTURE;
    }
}
