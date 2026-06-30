package matheussts.islanded.entities.fishs.monkfish;

import matheussts.islanded.entities.ModEntities;
import matheussts.islanded.items.factory.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;

public class BucketOfMonkFish extends MobBucketItem {

    public BucketOfMonkFish(Item.Properties properties) {
        super(ModEntities.MONK_FISH, Fluids.WATER, SoundEvents.COD_FLOP, properties);
    }

    public static final MobBucketItem BUCKET_OF_MONKFISH = ModItems.registerModItems("bucket_of_monkfish", BucketOfMonkFish::new, new Item.Properties().stacksTo(1));

    //creative tab
    public static void registerMaterialsEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(BUCKET_OF_MONKFISH);
        });
    }

}
