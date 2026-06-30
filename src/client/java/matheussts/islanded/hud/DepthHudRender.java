package matheussts.islanded.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

//this class is responsible for showing the depth in the screen
@Environment(EnvType.CLIENT)
public class DepthHudRender {

    public static void renderDepthHud (GuiGraphics graphics, Minecraft client, Player player, Level level, int scaledWidth, int scaledHeight, int ticks) {
        if (player.isEyeInFluid(FluidTags.WATER) && !client.options.hideGui && !player.isSpectator() && !player.isCreative() && !player.isInvulnerable()) {

            //takes posY and seaPosition and subtract both of them to find the currentDepth
            double posY = player.getY();
            int seaPosition = level.getSeaLevel();
            int currentDepth = (int) (seaPosition - posY);

            //width for each monitor / TV
            int width = scaledWidth / 2;

            //drawing in the middle and above of the screen the currentDepth
            graphics.drawString(client.font, currentDepth + "M", width, 25, 0xFFFFFFFF, false);
        }
    }
}