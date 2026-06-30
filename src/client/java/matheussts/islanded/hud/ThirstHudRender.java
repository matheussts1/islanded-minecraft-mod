package matheussts.islanded.hud;

import matheussts.islanded.access.ThirstManagerAccess;
import matheussts.islanded.init.EffectInit;
import matheussts.islanded.misc.ThirstTooltipData;
import matheussts.islanded.thirst.ThirstManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ThirstHudRender {

    public static final ResourceLocation THIRST_ICON = ResourceLocation.fromNamespaceAndPath("islanded", "textures/gui/thirst_icon.png");
    private static float flashAlpha = 0f;
    private static float otherFlashAlpha = 0f;

    public static void renderThirstHud(GuiGraphics graphics, Minecraft client, Player player, int scaledWidth, int scaledHeight, int ticks) {
        if (player != null && !player.isInvulnerable() && !player.isCreative() && !player.isSpectator() && !client.options.hideGui) {
            ThirstManager thirstManager = ((ThirstManagerAccess) player).getThirstManager();
            if (thirstManager.hasThirst()) {
                int thirst = thirstManager.getThirstLevel();
                int drop_index;
                int posX;
                int posY;
                int height = scaledHeight - 49;
                int width = scaledWidth / 2 + 91;

                if (player.getVehicle() == null || !player.getVehicle().showVehicleHealth()) {
                    ItemStack itemStack = null;

                if (thirst < 20) {
                    if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && player.getItemInHand(InteractionHand.MAIN_HAND).getTooltipImage().isPresent()
                            && player.getItemInHand(InteractionHand.MAIN_HAND).getTooltipImage().get() instanceof ThirstTooltipData) {
                        itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                        } else if (!player.getItemInHand(InteractionHand.OFF_HAND).isEmpty() && player.getItemInHand(InteractionHand.OFF_HAND).getTooltipImage().isPresent()
                        && player.getItemInHand(InteractionHand.OFF_HAND).getTooltipImage().get() instanceof ThirstTooltipData) {
                            itemStack = player.getItemInHand(InteractionHand.OFF_HAND);
                        }
                    }
                if (itemStack != null) {
                    otherFlashAlpha += (float) (Math.PI / (48F));
                    flashAlpha = (float) ((Math.cos(otherFlashAlpha + Math.PI) + 1f) / 2f);
                    } else if (otherFlashAlpha > 0.01F) {
                    otherFlashAlpha = 0.0F;
                    flashAlpha = 0.0F;
                    }

                for (drop_index = 0; drop_index < 10; ++drop_index) {
                    posY = height;
                    if (ticks % (thirst * 3 + 1) == 0) {
                        posY = height + (player.level().getRandom().nextInt(3) - 1);
                    } else if (ticks % (thirst * 8 + 3) == 0) {
                        posY = height + (player.level().getRandom().nextInt(3) - 1);
                    }
                    int upperCord = 9;
                    int beneathCoord = 0;

                    if (player.hasEffect(EffectInit.THIRST)) {
                        beneathCoord = 36;
                    }

                    posX = width - drop_index * 8 - 9;
                    posY = height;

                    graphics.blit(RenderPipelines.GUI_TEXTURED, THIRST_ICON, posX, posY, 0, 0, 9, 9, 256, 256);
                    if (drop_index * 2 + 1 < thirst) {
                        graphics.blit(RenderPipelines.GUI_TEXTURED, THIRST_ICON, posX, posY, beneathCoord, upperCord, 9, 9, 256, 256);
                    }
                    if (drop_index * 2 + 1 == thirst) {
                        graphics.blit(RenderPipelines.GUI_TEXTURED, THIRST_ICON, posX, posY, beneathCoord + 9, upperCord, 9, 9, 256, 256);
                        }

                    if (drop_index >= thirst / 2) {
                        if (itemStack != null) {
                            int thirstQuench = ((ThirstTooltipData) itemStack.getTooltipImage().get()).getThirstQuench();
                            int quality = ((ThirstTooltipData) itemStack.getTooltipImage().get()).getDrinkQuality();

                            if (drop_index < (thirst + thirstQuench) / 2) {
                                graphics.blit(RenderPipelines.GUI_TEXTURED, THIRST_ICON, posX, posY, quality * 18, 9, 9, 9, 256, 256);

                            } else if ((thirst + thirstQuench) % 2 != 0 && drop_index < (thirst + thirstQuench) / 2 + 1) {
                                graphics.blit(RenderPipelines.GUI_TEXTURED, THIRST_ICON, posX, posY, quality * 18 + 9, 9, 9, 9, 256, 256);
                            }

                            }
                        }
                    }
                }
            }
        }
    }

    private static int getHeartCount(@Nullable Player player) {
        if (player == null || !player.isAlive()) {
            return 0;
        }
        float f = player.getMaxHealth();
        int i = (int) (f + 0.5f) / 2;
        if (i > 30) {
            i = 30;
        }
        return i;
    }
}
