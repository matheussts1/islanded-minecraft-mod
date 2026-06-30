package matheussts.islanded.net;

import matheussts.islanded.api.HydrationTemplate;
import matheussts.islanded.datagen.worldgen.WorldGenMobs;
import matheussts.islanded.datagen.worldgen.WorldGenOres;
import matheussts.islanded.biomes.SeaChanger;
import matheussts.islanded.entities.ModEntities;
import matheussts.islanded.entities.fishs.FishMaker;
import matheussts.islanded.entities.fishs.clownfish.BucketOfClownFish;
import matheussts.islanded.entities.fishs.monkfish.BucketOfMonkFish;
import matheussts.islanded.init.EffectInit;
import matheussts.islanded.items.armors.ArmorEffects;
import matheussts.islanded.items.armors.ArmorMaker;
import matheussts.islanded.items.factory.ModBlocks;
import matheussts.islanded.items.bottles.RegisterBottles;
import matheussts.islanded.items.materials.RegisterMaterials;
import matheussts.islanded.items.tools.RegisterTools;
import matheussts.islanded.loot.ModLootTables;
import matheussts.islanded.network.ThirstServerPacket;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Islanded implements ModInitializer {
	public static final String MOD_ID = "islanded";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//map for storing attributes before logging into a server
	public static final List<HydrationTemplate> HYDRATION_TEMPLATES = new ArrayList<HydrationTemplate>();

	@Override
	public void onInitialize() {
		System.out.print("ta carregando?");

		//Thirst
		ThirstServerPacket.init();

		//registering the items
		RegisterBottles.registerBottleEntries();
		RegisterMaterials.registerMaterialsEntries();

		//registering the blocks
		ModBlocks.initialize();

		//registering effects
		EffectInit.init();

		//starting the ore generation
		WorldGenOres.generateOres();

		//changing the profundity of sea
		SeaChanger.topographyInterceptor();

		//registering the fish
		ModEntities.registerModEntityTypes();
		ModEntities.registerAttributes();
		FishMaker.registerMaterialsEntries();
		BucketOfMonkFish.registerMaterialsEntries();
		BucketOfClownFish.registerMaterialsEntries();

		//adding the fish to the sea
		WorldGenMobs.changingSeaGeneration();

		//fish in the rod
		ModLootTables.changingFishLoot();

		//adding armor
		ArmorMaker.registerArmorEntries();

		//status effects
		ArmorEffects.registerEffects();
	}
}