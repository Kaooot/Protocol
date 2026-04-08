package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ActorEventSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.packet.ActorEventPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class ActorEventSerializer_v974 extends ActorEventSerializer_v291 {

    public ActorEventSerializer_v974(TypeMap<ActorEvent> typeMap) {
        super(typeMap);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ActorEventPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getFireAtPosition(), helper::writeVector3f);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ActorEventPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setFireAtPosition(helper.readOptional(buffer, null, helper::readVector3f));
    }
}