package org.cloudburstmc.protocol.bedrock.codec.v972.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.UpdateClientOptionsSerializer_v786;
import org.cloudburstmc.protocol.bedrock.packet.UpdateClientOptionsPacket;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateClientOptionsSerializer_v972 extends UpdateClientOptionsSerializer_v786 {
    public static final UpdateClientOptionsSerializer_v972 INSTANCE = new UpdateClientOptionsSerializer_v972();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientOptionsPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getFilterProfanityChange(),
                (buf, filterProfanityChange) -> buf.writeBoolean(filterProfanityChange.getAsBoolean()));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientOptionsPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setFilterProfanityChange(
                helper.readOptional(buffer, OptionalBoolean.empty(),
                        (buf, codecHelper) -> OptionalBoolean.of(buf.readBoolean()))
        );
    }
}