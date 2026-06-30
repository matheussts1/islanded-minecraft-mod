package matheussts.islanded.init;

import matheussts.islanded.effect.ThirstEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

//registering the new effect called: "thirst"
public class EffectInit {
    public final static Holder<MobEffect> THIRST = register("islanded:thirst_effect", new ThirstEffect(MobEffectCategory.HARMFUL, 3062757));

    private static Holder<MobEffect> register(String id, MobEffect mobEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.parse(id), mobEffect);
    }

    public static void init() {}
}
