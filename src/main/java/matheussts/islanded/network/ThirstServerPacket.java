package matheussts.islanded.network;

import matheussts.islanded.access.ThirstManagerAccess;
import matheussts.islanded.net.Islanded;
import matheussts.islanded.network.packet.ExcludedThirstPacket;
import matheussts.islanded.network.packet.HydrationTemplatePacket;
import matheussts.islanded.network.packet.ThirstPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;


import java.util.ArrayList;
import java.util.List;

//communication between server -> client
public class ThirstServerPacket {

    public static void init() {
        PayloadTypeRegistry.playS2C().register(ThirstPacket.PACKET_TYPE, ThirstPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ExcludedThirstPacket.PACKET_TYPE, ExcludedThirstPacket.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(HydrationTemplatePacket.PACKET_TYPE, HydrationTemplatePacket.STREAM_CODEC);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            writeS2CThirstUpdatePacket(handler.player);
        });
    }

    public static void writeS2CExcludedSyncPacket(ServerPlayer serverPlayer, boolean setThirst) {
        ServerPlayNetworking.send(serverPlayer, new ExcludedThirstPacket(serverPlayer.getId(), setThirst));
    }

    public static void writeS2CThirstUpdatePacket(ServerPlayer serverPlayer) {
        ServerPlayNetworking.send(serverPlayer, new ThirstPacket(serverPlayer.getId(), ((ThirstManagerAccess) serverPlayer).getThirstManager().getThirstLevel()));
    }

    public static void writeS2CHydrationTemplateSyncPacket(ServerPlayer serverPlayer) {
        List<Integer> templateList = new ArrayList<>();
        Islanded.HYDRATION_TEMPLATES.forEach((template) -> {
            templateList.add(template.getHydration());
            templateList.add(template.getItems().size());
        });
        List<ResourceLocation> templateIdentifiers = new ArrayList<>();
        Islanded.HYDRATION_TEMPLATES.forEach((template) -> {
            template.getItems().forEach((item) -> {
                templateIdentifiers.add(BuiltInRegistries.ITEM.getKey(item));
            });
        });
        ServerPlayNetworking.send(serverPlayer, new HydrationTemplatePacket(templateList, templateIdentifiers));
    }
}