package matheussts.islanded.effect;

import matheussts.islanded.access.ThirstManagerAccess;
import matheussts.islanded.network.ThirstServerPacket;
import matheussts.islanded.thirst.ThirstManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ThirstEffect extends MobEffect {

    public ThirstEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }

    //interval to apply the thirst effect
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = 150 / (amplifier + 1);
        return duration % Math.max(1, interval) == 0;
    }

    //decreases one in thirst bar for each 150 ticks / amplifier
    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            ThirstManager thirstManager = ((ThirstManagerAccess) serverPlayer).getThirstManager();
            thirstManager.add(-1);
            ThirstServerPacket.writeS2CThirstUpdatePacket(serverPlayer);
        }
        return super.applyEffectTick(serverLevel, livingEntity, amplifier);
    }
}