package matheussts.islanded.items.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

//radio
public class RadioItem extends Item {
    public RadioItem(Properties settings) {
        super(settings);
    }

    //make the radio talk in the chat when interacting with it
    @Override
    public @NotNull InteractionResult use(Level level, Player user, InteractionHand hand) {
        if (level.isClientSide()) {
            user.displayClientMessage(Component.literal("mensagem foda"), false);
        }
        return InteractionResult.SUCCESS;
    }
}