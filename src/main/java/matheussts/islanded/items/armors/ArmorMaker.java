package matheussts.islanded.items.armors;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

import static matheussts.islanded.items.factory.ModItems.registerModItems;

public class ArmorMaker {
    public static final Item COPPER_HELMET = registerModItems(
            "copper_diving_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(CopperDivingSuit.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(CopperDivingSuit.BASE_DURABILITY))
    );

    public static final Item COPPER_CHESTPLATE = registerModItems(
            "copper_diving_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(CopperDivingSuit.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(CopperDivingSuit.BASE_DURABILITY))
    );

    public static final Item COPPER_LEGGINGS = registerModItems(
            "copper_diving_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(CopperDivingSuit.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(CopperDivingSuit.BASE_DURABILITY))
    );

    public static final Item COPPER_BOOTS = registerModItems(
            "copper_diving_boots",
            Item::new,
            new Item.Properties().humanoidArmor(CopperDivingSuit.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(CopperDivingSuit.BASE_DURABILITY))
    );


    public static void registerArmorEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.accept(COPPER_HELMET);
            entries.accept(COPPER_CHESTPLATE);
            entries.accept(COPPER_LEGGINGS);
            entries.accept(COPPER_BOOTS);
        });
    }
}
