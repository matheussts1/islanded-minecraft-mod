package matheussts.islanded.items.materials;

import matheussts.islanded.items.factory.ModBlocks;
import matheussts.islanded.items.factory.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

//registering all the materials in mod
public class RegisterMaterials {
    public static final Item PLASTIC = ModItems.registerModItems("plastic", Item::new, new Item.Properties());
    public static final Block PLASTIC_BLOCK = ModBlocks.registerModBlocks("plastic_block", Block::new, BlockBehaviour.Properties.of().sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f), true);

    //put the materials in the creative tab
    public static void registerMaterialsEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.accept(PLASTIC);
            entries.accept(PLASTIC_BLOCK);
        });
    }
}
