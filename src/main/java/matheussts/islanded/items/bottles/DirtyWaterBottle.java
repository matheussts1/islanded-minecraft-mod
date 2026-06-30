package matheussts.islanded.items.bottles;

import matheussts.islanded.init.EffectInit;
import matheussts.islanded.network.ThirstServerPacket;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class DirtyWaterBottle extends BucketItem {

    public DirtyWaterBottle(Properties properties) {
        super(Fluids.WATER, properties);
    }

    //put water in the cauldron
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        BlockState belowState = level.getBlockState(pos.below());
        Player player = context.getPlayer();
        boolean isHot = belowState.getBlock() instanceof CampfireBlock && belowState.getValue(CampfireBlock.LIT);

        if (player == null || !player.isCrouching()) {
            return InteractionResult.PASS;
        }

        if (!isHot) {
            return InteractionResult.PASS;
        }

        if (state.is(Blocks.CAULDRON)) {
            if (!level.isClientSide()) {
                level.setBlock(pos, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1), 3);
                executeEffects(level, pos, player, context);
            }
            return InteractionResult.SUCCESS;
        }

        if (state.getBlock() instanceof LayeredCauldronBlock) {
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);

            if (currentLevel >= 3) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide()) {
                level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, currentLevel + 1), 3);
                executeEffects(level, pos, player, context);
            }

            if (!player.getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
                player.getInventory().add(new ItemStack(RegisterBottles.PLASTIC_BOTTLE));
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    //consume and empty the dirty water
    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockPos = blockHitResult.getBlockPos();
        Direction direction = blockHitResult.getDirection();
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState = level.getBlockState(blockPos);
        BlockPos blockPos3 = blockState.getBlock() instanceof LiquidBlockContainer ? blockPos : blockPos2;

        //start consuming Tiete river water and condition to not make air waterfalls
        if (blockHitResult.getType() != HitResult.Type.BLOCK || player.isEyeInFluid(FluidTags.WATER) || player.isSwimming()) {
            player.startUsingItem(interactionHand);
            return InteractionResult.CONSUME;
        }

        //emptying in the ground the dirty bottle
        if (this.emptyContents(player, level, blockPos3, blockHitResult)) {
            this.checkExtraContent(player, level, itemStack, blockPos3);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockPos3, itemStack);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, getEmptySuccessItem(itemStack, player));
            return InteractionResult.SUCCESS.heldItemTransformedTo(itemStack2);
        } else {
            return InteractionResult.FAIL;
        }
    }

    //making the sound of drinking continuous, playing for each 4 ticks
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

    //effects to put in the player when finishing using the dirty bottle
    public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(itemStack, level, livingEntity);

        if (livingEntity instanceof Player player) {
            if (livingEntity instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.isCreative()) {
                itemStack.shrink(1);
                if (!level.isClientSide() && livingEntity instanceof ServerPlayer serverPlayer) {
                    player.addEffect(new MobEffectInstance(EffectInit.THIRST, 200, 1, false, false, true));
                    ThirstServerPacket.writeS2CThirstUpdatePacket(serverPlayer);
                }
            }
        }
        return itemStack;
    }

    //effects for the cauldron
    public void executeEffects(Level level, BlockPos pos, Player player, UseOnContext context) {
        level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.awardStat(Stats.USE_CAULDRON);
    }

    //using the method of minecraft for emptying a bucket
    @Override
    public boolean emptyContents(@Nullable LivingEntity livingEntity, Level level, BlockPos blockPos, @Nullable BlockHitResult blockHitResult) {
        return super.emptyContents(livingEntity, level, blockPos, blockHitResult);
    }

    //changing just the Bucket from Plastic Bottle
    public static @NotNull ItemStack getEmptySuccessItem(ItemStack itemStack, Player player) {
        return !player.hasInfiniteMaterials() ? new ItemStack(RegisterBottles.PLASTIC_BOTTLE) : itemStack;
    }

    //use duration for drinking the dirty water
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