package matheussts.islanded.net.client;

import matheussts.islanded.access.ThirstManagerAccess;
import matheussts.islanded.api.HydrationTemplate;
import matheussts.islanded.net.Islanded;
import matheussts.islanded.network.packet.ExcludedThirstPacket;
import matheussts.islanded.network.packet.HydrationTemplatePacket;
import matheussts.islanded.network.packet.ThirstPacket;
import matheussts.islanded.thirst.ThirstManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ThirstClientPacket {

    public static void handle(ThirstPacket payload, ClientPlayNetworking.Context context) {
        int thirstLevel = payload.thirstLevel();
        context.client().execute(() -> {
            if (context.player() != null) {
                ThirstManager thirstManager = ((ThirstManagerAccess) context.player()).getThirstManager();
                thirstManager.setThirstLevel(thirstLevel);
            }
        });
    }

    public static void init() {
        ClientPlayConnectionEvents.INIT.register((handler, client) -> {
            ClientPlayNetworking.registerReceiver(ThirstPacket.PACKET_TYPE, ThirstClientPacket::handle);

            ClientPlayNetworking.registerReceiver(ExcludedThirstPacket.PACKET_TYPE, (payload, context) -> {
                boolean excludedThirst = payload.excludingThirst();
                context.client().execute(() -> {
                    if (context.player().level().getEntity(payload.playerId()) instanceof Player player) {
                        ((ThirstManagerAccess) player).getThirstManager().setThirst(excludedThirst);
                    }
                });
            });

            ClientPlayNetworking.registerReceiver(HydrationTemplatePacket.PACKET_TYPE, (payload, context) -> {
                List<HydrationTemplate> hydrationTemplates = new ArrayList<>();
                List<Integer> templateList = payload.templateList();
                int count = 0;
                for (int i = 0; i < templateList.size(); i += 2) {
                    List<Item> items = new ArrayList<>();
                    for (int u = 0; u < templateList.get(i + 1); u++) {
                        items.add(BuiltInRegistries.ITEM.get(payload.templateIdentifiers().get(count++)).get().value());
                    }
                    hydrationTemplates.add(new HydrationTemplate(templateList.get(i), items));
                }
                context.client().execute(() -> {
                    Islanded.HYDRATION_TEMPLATES.clear();
                    Islanded.HYDRATION_TEMPLATES.addAll(hydrationTemplates);
                });
            });
        });
    }
}

