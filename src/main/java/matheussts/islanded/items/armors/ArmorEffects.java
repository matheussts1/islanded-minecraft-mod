package matheussts.islanded.items.armors;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;

public class ArmorEffects {
    public static void checkArmorEffects(ServerPlayer player) {
        if (player != null) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(ArmorMaker.COPPER_HELMET)) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, true, true));
            }
            if (player.getItemBySlot(EquipmentSlot.CHEST).is(ArmorMaker.COPPER_CHESTPLATE)) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 250, 0, false, true, true));
            }
            if (player.getItemBySlot(EquipmentSlot.LEGS).is(ArmorMaker.COPPER_LEGGINGS)) {
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 150, 0, false, true, true));
            }
            if (player.getItemBySlot(EquipmentSlot.FEET).is(ArmorMaker.COPPER_BOOTS)) {
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 0, false, true, true));
            }
        }
    }

    public static void registerEffects() {
        ServerTickEvents.START_WORLD_TICK.register((ServerLevel world) -> {
            for (ServerPlayer player : world.players()) {
                checkArmorEffects(player);
            }
        });
    }
}
