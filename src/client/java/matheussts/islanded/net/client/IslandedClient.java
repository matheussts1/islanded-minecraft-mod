package matheussts.islanded.net.client;

import matheussts.islanded.entities.ModEntities;
import matheussts.islanded.layers.ModEntityModelLayers;
import matheussts.islanded.render.clownfish.ClownFishRenderer;
import matheussts.islanded.render.monkfish.MonkFishRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class IslandedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ThirstClientPacket.init();
		ModEntityModelLayers.registerModelLayers();
		EntityRenderers.register(ModEntities.MONK_FISH, MonkFishRenderer::new);
		EntityRenderers.register(ModEntities.CLOWN_FISH, ClownFishRenderer::new);
	}
}