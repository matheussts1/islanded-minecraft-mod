package matheussts.islanded.entities.fishs;

import matheussts.islanded.items.factory.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.level.Level;

public class FishMaker extends Item implements ConsumableListener {

    public FishMaker(Properties properties) {
        super(properties);
    }

    //registering monkfish
    public static final Item RAW_MONK_FISH = ModItems.registerModItems("raw_monk_fish", Item::new, new Item.Properties().food(new FoodProperties(2, 0.1f, false)));
    public static final Item COOKED_MONK_FISH = ModItems.registerModItems("cooked_monk_fish", Item::new, new Item.Properties().food(new FoodProperties(6, 0.8f, false)));

    //registering clownfish
    public static final Item RAW_CLOWN_FISH = ModItems.registerModItems("raw_clown_fish", Item::new, new Item.Properties().food(new FoodProperties(2, 0.1f, false)));
    public static final Item COOKED_CLOWN_FISH = ModItems.registerModItems("cooked_clown_fish", Item::new, new Item.Properties().food(new FoodProperties(6, 0.8f, false)));

    //play the sound of eating
    @Override
    public void onConsume(Level level, LivingEntity livingEntity, ItemStack itemStack, Consumable consumable) {
        RandomSource randomSource = livingEntity.getRandom();
        level.playSound(
                null,
                livingEntity.getX(),
                livingEntity.getY(),
                livingEntity.getZ(),
                consumable.sound().value(),
                SoundSource.NEUTRAL,
                1.0F,
                randomSource.triangle(1.0F, 0.4F)
        );
        if (livingEntity instanceof Player player) {
            level.playSound(
                    null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, Mth.randomBetween(randomSource, 0.9F, 1.0F)
            );
        }
    }

    //creative tab
    public static void registerMaterialsEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(COOKED_MONK_FISH);
            entries.accept(RAW_MONK_FISH);

            entries.accept(COOKED_CLOWN_FISH);
            entries.accept(RAW_CLOWN_FISH);
        });
    }
}
