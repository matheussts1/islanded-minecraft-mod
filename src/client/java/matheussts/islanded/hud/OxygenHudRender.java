package matheussts.islanded.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;

//this class is responsible for drawing the oxygen hud in the screen
@Environment(EnvType.CLIENT)
public class OxygenHudRender {

    //this two static final takes the bg image and the fg image for the oxygen
    public static final ResourceLocation OXYGEN_BACKGROUND = ResourceLocation.fromNamespaceAndPath("islanded", "textures/gui/oxygen_background.png");
    public static final ResourceLocation OXYGEN_FOREGROUND = ResourceLocation.fromNamespaceAndPath("islanded", "textures/gui/oxygen_foreground.png");

    public static void renderOxygenHud(GuiGraphics graphics, Minecraft client, Player player, int scaledWidth, int scaledHeight, int ticks) {
        //the size of the images in the screen
        float scale = 0.35f;

        //the width and the height of the image
        int hudWidth = 256;
        int hudHeight = 171;

        //horizontal coordinate and vertical coordinate
        int x = 25;
        int y = scaledHeight - (int)(hudHeight * scale) - (-16);

        if (player != null && !player.isInvulnerable() && !player.isCreative()
                && !player.isSpectator() && !client.options.hideGui
                && player.isEyeInFluid(FluidTags.WATER)) {

            //configurations to the state, location and size of the image
            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            graphics.pose().scale(scale, scale);

            //drawing on the screen
            graphics.blit(RenderPipelines.GUI_TEXTURED, OXYGEN_BACKGROUND, 0, 0, 0, 0, hudWidth, hudHeight, 256, 171);
            graphics.pose().popMatrix();

            //division to take the actual oxygen and clamp to not bug the hud with negative numbers
            float oxygen = (float) player.getAirSupply() / (float) player.getMaxAirSupply();
            oxygen = Math.max(0f, Math.min(1f, oxygen));

            //defining what's going to appear accordingly the amount of oxygen
            int visibleWidth = (int)((hudWidth * scale) * oxygen);

            //this line cuts exactly what is needed to appear in the hud
            graphics.enableScissor(x, y, x + visibleWidth, y + (int)(hudHeight * scale));

            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            graphics.pose().scale(scale, scale);
            graphics.blit(RenderPipelines.GUI_TEXTURED, OXYGEN_FOREGROUND, 0, 0, 0, 0, hudWidth, hudHeight, 256, 171);
            graphics.pose().popMatrix();

            graphics.disableScissor();
        }
    }
}