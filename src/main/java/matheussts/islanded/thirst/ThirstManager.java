package matheussts.islanded.thirst;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

//entire thirst logic
public class ThirstManager {
    public static final ResourceKey<DamageType> THIRST = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath("islanded", "thirst"));

    boolean hasThirst = true;
    private int thirstLevel = 20;
    private int thirstTickCounter = 0;

    public int add(int thirst_value) {
        this.thirstLevel = Math.min(thirst_value + this.thirstLevel, 20);
        return thirst_value;
    }

    public void update(Player player) {
        Difficulty difficulty = player.level().getDifficulty();
        if (difficulty != Difficulty.PEACEFUL && !player.isCreative()) {
            this.thirstTickCounter++;

            if (this.thirstTickCounter >= 1000) {
                this.thirstLevel = Math.max(this.thirstLevel - 1, 0);
                this.thirstTickCounter = 0;
            }
            if (this.thirstLevel <= 0) {
                if (!player.level().isClientSide()) {
                    player.hurt(this.createDamageSource(player), 1.0f);
                }
            }
        }
    }

    public void readNbt(CompoundTag tag) {
        if (tag.contains("ThirstLevel")) {
            this.thirstLevel = tag.getInt("ThirstLevel").orElse(20);
        }
    }

    public void writeNbt(CompoundTag tag) {
        tag.putInt("ThirstLevel", this.thirstLevel);
    }

    public int getThirstLevel() {
        return this.thirstLevel;
    }

    public void setThirstLevel(int new_thirstLevel) {
        this.thirstLevel = new_thirstLevel;
    }

    public boolean isNotFull() {
        return this.thirstLevel < 20;
    }

    public boolean hasThirst() {
        return this.hasThirst;
    }

    public void setThirst(boolean canHaveThirst) {
        this.hasThirst = canHaveThirst;
    }

    private DamageSource createDamageSource(Entity entity) {
        return entity.damageSources().source(THIRST);
    }
}
