package matheussts.islanded.misc;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

public class ThirstTooltipData implements TooltipComponent {

    private final int thirstQuench;
    private final int quality;

    public ThirstTooltipData(int quality, int thirstQuench) {
        this.thirstQuench = thirstQuench;
        this.quality = quality;
    }

    public int getThirstQuench() {
        return this.thirstQuench;
    }

    public int getDrinkQuality() {
        return this.quality;
    }
}
