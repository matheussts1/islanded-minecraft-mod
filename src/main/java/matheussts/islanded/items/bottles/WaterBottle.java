package matheussts.islanded.items.bottles;

import matheussts.islanded.access.ThirstManagerAccess;
import matheussts.islanded.network.ThirstServerPacket;
import matheussts.islanded.thirst.ThirstManager;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.entity.BeaconBlockEntity.playSound;

public class WaterBottle extends Item {

    public WaterBottle(Properties properties) {
        super(properties);
    }

    //drink the water
    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        player.startUsingItem(interactionHand);
        return InteractionResult.CONSUME;
    }

    //making the sound of drinking continuous, playing for each tick
    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (remainingUseDuration % 4 == 0) {
            level.playSound(null,
                    livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(),
                    SoundEvents.GENERIC_DRINK,
                    SoundSource.PLAYERS,
                    0.5f,
                    level.random.nextFloat() * 0.1f + 0.9f
            );
        }
    }

    //what to do when finish drinking the clean bottle
    public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if (livingEntity instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.isCreative()) {
                itemStack.shrink(1);
                if (!level.isClientSide() && livingEntity instanceof ServerPlayer serverPlayer) {
                    ThirstManager thirstManager = ((ThirstManagerAccess) player).getThirstManager();
                    thirstManager.add(4);
                    ThirstServerPacket.writeS2CThirstUpdatePacket(serverPlayer);
                }
            }
        }
        return itemStack;
    }

    //use duration for drinking the clean water
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    //animation for the drinking
    @Override
    public @NotNull ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.DRINK;
    }
}
