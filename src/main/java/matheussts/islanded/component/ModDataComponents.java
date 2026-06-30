package matheussts.islanded.component;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModDataComponents {
    public static final DataComponentType<BottleComponent> BOTTLE_DATA = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath("islanded", "dirty_water_bottle_data"),
            DataComponentType.<BottleComponent>builder()
                    .persistent(BottleComponent.CODEC)
                    .networkSynchronized(BottleComponent.STREAM_CODEC)
                    .build()
    );
}
