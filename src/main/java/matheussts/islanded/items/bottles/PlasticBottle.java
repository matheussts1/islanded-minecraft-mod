package matheussts.islanded.items.bottles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.item.BucketItem.getEmptySuccessItem;

public class PlasticBottle extends Item implements DispensibleContainerItem {

    public PlasticBottle(Properties settings) {
        super(settings);
    }

    //take water from the sea
    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockPos;
        BlockPos blockPos2;
        Direction direction;
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        } else {
            blockPos = blockHitResult.getBlockPos();
            direction = blockHitResult.getDirection();
            blockPos2 = blockPos.relative(direction);
        }
        if (level.mayInteract(player, blockPos) && player.mayUseItemAt(blockPos2, direction, itemStack)) {
            BlockState blockState = level.getBlockState(blockPos);
            Block var15 = blockState.getBlock();
            if (var15 instanceof BucketPickup) {
                level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 11);
                ItemStack itemStack2 = new ItemStack(RegisterBottles.DIRTY_WATER_BOTTLE.asItem());
                if (!itemStack2.isEmpty()) {
                    player.awardStat(Stats.ITEM_USED.get(this));
                    player.playSound(net.minecraft.sounds.SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                    level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                    ItemStack itemStack3 = ItemUtils.createFilledResult(itemStack, player, itemStack2);

                    return InteractionResult.SUCCESS.heldItemTransformedTo(itemStack3);
                }
            }

            return InteractionResult.FAIL;
        } else {
            BlockState blockState = level.getBlockState(blockPos);
            BlockPos blockPos3 = blockState.getBlock() instanceof LiquidBlockContainer ? blockPos : blockPos2;
            if (this.emptyContents(player, level, blockPos3, blockHitResult)) {
                this.checkExtraContent(player, level, itemStack, blockPos3);

                ItemStack itemStack2 = ItemUtils.createFilledResult(itemStack, player, getEmptySuccessItem(itemStack, player));
                return InteractionResult.SUCCESS.heldItemTransformedTo(itemStack2);
            } else {
                return InteractionResult.FAIL;
            }
        }
    }

    //take clean water from the cauldron
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
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
            return InteractionResult.PASS;
        }

        if (state.getBlock() instanceof LayeredCauldronBlock) {
            int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);

            if (!level.isClientSide()) {
                if (currentLevel != 1) {
                    level.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, currentLevel - 1), 3);
                    executeEffects(level, pos, player, context);
                } else {
                    player.level().setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                    executeEffects(level, pos, player, context);
                }
            }

            if (!player.getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
                player.getInventory().add(new ItemStack(RegisterBottles.WATER_BOTTLE));
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    //empty the content
    @Override
    public boolean emptyContents(@Nullable LivingEntity livingEntity, Level level, BlockPos blockPos, @Nullable BlockHitResult blockHitResult) {
        return false;
    }

    //effects
    public void executeEffects(Level level, BlockPos pos, Player player, UseOnContext context) {
        level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.awardStat(Stats.USE_CAULDRON);
    }
}
