package matheussts.islanded.entities;

import matheussts.islanded.entities.fishs.clownfish.ClownFish;
import matheussts.islanded.entities.fishs.monkfish.MonkFish;
import matheussts.islanded.net.Islanded;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;


public class ModEntities {
    public static final EntityType<MonkFish> MONK_FISH = registerEntities(
            "monk_fish",
            EntityType.Builder.of(MonkFish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.3f)

    );

    public static final EntityType<ClownFish> CLOWN_FISH = registerEntities(
            "clown_fish",
            EntityType.Builder.of(ClownFish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.3f)

    );


    private static <T extends Entity> EntityType<T> registerEntities(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Islanded.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes() {
        Islanded.LOGGER.info("Registering EntityTypes for " + Islanded.MOD_ID);
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(MONK_FISH, MonkFish.createCubeAttributes());
        FabricDefaultAttributeRegistry.register(CLOWN_FISH, ClownFish.createCubeAttributes());
    }
}
