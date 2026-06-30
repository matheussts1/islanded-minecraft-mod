package matheussts.islanded.network.packet;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public record HydrationTemplatePacket(List<Integer> templateList, List<ResourceLocation> templateIdentifiers) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<HydrationTemplatePacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("islanded", "hydration_template_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HydrationTemplatePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.collection(ArrayList::new, ByteBufCodecs.VAR_INT), HydrationTemplatePacket::templateList,
            ByteBufCodecs.collection(ArrayList::new, ResourceLocation.STREAM_CODEC), HydrationTemplatePacket::templateIdentifiers,
            HydrationTemplatePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}
