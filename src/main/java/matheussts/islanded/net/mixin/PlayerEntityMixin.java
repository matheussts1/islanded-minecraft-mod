package matheussts.islanded.net.mixin;

import matheussts.islanded.access.ThirstManagerAccess;
import matheussts.islanded.thirst.ThirstManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ThirstManagerAccess {
    @Unique
    private final ThirstManager thirstManager = new ThirstManager();

    @Override
    public ThirstManager getThirstManager() {
        return this.thirstManager;
    }

    @Shadow
    protected FoodData foodData = new FoodData();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "Lnet/minecraft/world/entity/player/Player;tick()V", at = @At(value = "HEAD", target = "Lnet/minecraft/world/food/FoodData;update(Lnet/minecraft/world/entity/player/Player;)V", shift = At.Shift.AFTER))
    private void tickMixin(CallbackInfo info) {
        if (this.thirstManager.hasThirst()) {
            this.thirstManager.update((Player) (Object) this);
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At(value = "TAIL"))
    private void readCustomDataFromTagMixin(ValueInput input, CallbackInfo info) {
        if (input instanceof net.minecraft.world.level.storage.TagValueInput tagInput) {
            int savedThirst = input.getInt("thirstLevel").orElse(20);
            this.thirstManager.setThirstLevel(savedThirst);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At(value = "TAIL"))
    private void writeCustomDataToTagMixin(ValueOutput output, CallbackInfo info) {
        output.putInt("thirstLevel", this.thirstManager.getThirstLevel());
    }
}