package matheussts.islanded.net.client.mixin;

import matheussts.islanded.hud.ThirstHudRender;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Gui.class)

public abstract class InGameHudMixinThirst {

    @Inject(method = "render", at = @At("TAIL"))
    private void renderThirst(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo callbackInfo) {
        Player player = Minecraft.getInstance().player;

        if (player != null) {
            ThirstHudRender.renderThirstHud(
                    graphics,
                    Minecraft.getInstance(),
                    player,
                    graphics.guiWidth(),
                    graphics.guiHeight(),
                    player.tickCount
            );
        }
    }
}
