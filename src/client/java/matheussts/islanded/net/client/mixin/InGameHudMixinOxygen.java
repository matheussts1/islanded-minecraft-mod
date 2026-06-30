package matheussts.islanded.net.client.mixin;

import matheussts.islanded.hud.OxygenHudRender;
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

public abstract class InGameHudMixinOxygen {

    @Inject(method = "render", at = @At("HEAD"))
    private void renderOxygen(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo callbackInfo) {
        Player player = Minecraft.getInstance().player;

        if (player != null) {
            OxygenHudRender.renderOxygenHud(
                    graphics,
                    Minecraft.getInstance(),
                    player,
                    graphics.guiWidth(),
                    graphics.guiHeight(),
                    player.tickCount
            );
        }
    }

    @Inject(method = "renderAirBubbles", at = @At("HEAD"), cancellable = true)
    public void ExcludeOxygenBubbles(GuiGraphics graphics, Player player, int i, int j, int k, CallbackInfo callbackInfo) {
        if (callbackInfo.isCancellable()) {
            callbackInfo.cancel();
        }
    }
}
