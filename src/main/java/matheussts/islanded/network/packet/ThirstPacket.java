package matheussts.islanded.network.packet;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ThirstPacket(int playerId, int thirstLevel) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ThirstPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("islanded", "thirst_update"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ThirstPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ThirstPacket::playerId,
            ByteBufCodecs.VAR_INT, ThirstPacket::thirstLevel,
            ThirstPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}