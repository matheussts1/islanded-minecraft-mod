package matheussts.islanded.loot;

import matheussts.islanded.entities.fishs.FishMaker;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class ModLootTables {
    public static void changingFishLoot () {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (BuiltInLootTables.FISHING.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .with(LootItem.lootTableItem(FishMaker.RAW_MONK_FISH).build())
                        .with(LootItem.lootTableItem(FishMaker.RAW_CLOWN_FISH).build())
                        .conditionally(LootItemRandomChanceCondition.randomChance(0.05f).build())
                        .conditionally(LootItemRandomChanceCondition.randomChance(0.05f).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
