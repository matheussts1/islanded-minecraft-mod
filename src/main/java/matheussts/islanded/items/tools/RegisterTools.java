package matheussts.islanded.items.tools;

import matheussts.islanded.items.factory.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

//registering all the tools in mod
public class RegisterTools {
    public static final Item RADIO = ModItems.registerModItems("radio", RadioItem::new, new Item.Properties());

    //put all the tools in the creative tab
    public static void RegisterToolsEntries () {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(RADIO);
        });
    }
}
