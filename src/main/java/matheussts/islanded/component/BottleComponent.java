package matheussts.islanded.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BottleComponent(Integer fillLevel, Integer qualityLevel) {
    public static final BottleComponent DEFAULT = new BottleComponent(0, 0);

    public static final Codec<BottleComponent> CODEC = RecordCodecBuilder
            .create(instance -> instance.group(Codec.INT.fieldOf("fill_level").forGetter(BottleComponent::fillLevel), Codec.INT.fieldOf("quality_level").forGetter(BottleComponent::qualityLevel))
                    .apply(instance, BottleComponent::new));

    public static final StreamCodec<ByteBuf, BottleComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, BottleComponent::fillLevel,
            ByteBufCodecs.INT, BottleComponent::qualityLevel,
            BottleComponent::new
    );
}
