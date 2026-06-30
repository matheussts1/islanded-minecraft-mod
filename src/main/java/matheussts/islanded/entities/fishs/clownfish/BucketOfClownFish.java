package matheussts.islanded.entities.fishs.clownfish;

import matheussts.islanded.entities.ModEntities;
import matheussts.islanded.items.factory.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;

public class BucketOfClownFish extends MobBucketItem {

    public BucketOfClownFish(Item.Properties properties) {
        super(ModEntities.CLOWN_FISH, Fluids.WATER, SoundEvents.COD_FLOP, properties);
    }

    public static final MobBucketItem BUCKET_OF_CLOWNFISH = ModItems.registerModItems("bucket_of_clownfish", matheussts.islanded.entities.fishs.clownfish.BucketOfClownFish::new, new Item.Properties().stacksTo(1));

    //creative tab
    public static void registerMaterialsEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(BUCKET_OF_CLOWNFISH);
        });
    }
}