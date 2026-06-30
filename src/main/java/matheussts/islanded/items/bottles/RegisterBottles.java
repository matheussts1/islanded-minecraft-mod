package matheussts.islanded.items.bottles;

import matheussts.islanded.items.factory.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

//registering all the bottles
public class RegisterBottles {
    public static final Item PLASTIC_BOTTLE = ModItems.registerModItems("plastic_bottle", PlasticBottle::new, new Item.Properties());
    public static final Item DIRTY_WATER_BOTTLE = ModItems.registerModItems("dirty_water_bottle", DirtyWaterBottle::new, new Item.Properties());
    public static final Item WATER_BOTTLE = ModItems.registerModItems("water_bottle", WaterBottle::new, new Item.Properties());

    //put the bottles in creative tab
    public static void registerBottleEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(PLASTIC_BOTTLE);
            entries.accept(DIRTY_WATER_BOTTLE);
            entries.accept(WATER_BOTTLE);
        });
    }
}
