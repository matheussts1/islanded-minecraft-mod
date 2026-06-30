package matheussts.islanded.network.packet;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ExcludedThirstPacket(int playerId, boolean excludingThirst) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ExcludedThirstPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("islanded", "excluded_thirst_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ExcludedThirstPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ExcludedThirstPacket::playerId,
            ByteBufCodecs.BOOL, ExcludedThirstPacket::excludingThirst,
            ExcludedThirstPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }
}
