package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorEvent;
import org.cloudburstmc.protocol.bedrock.packet.ActorEventPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor
public class ActorEventSerializer_v291 implements BedrockPacketSerializer<ActorEventPacket> {

    private static final InternalLogger log = InternalLoggerFactory.getInstance(ActorEventSerializer_v291.class);

    private final TypeMap<ActorEvent> typeMap;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ActorEventPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        buffer.writeByte(this.typeMap.getId(packet.getType()));
        VarInts.writeInt(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ActorEventPacket packet) {
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        int event = buffer.readUnsignedByte();
        packet.setType(this.typeMap.getType(event));
        packet.setData(VarInts.readInt(buffer));
        if (log.isDebugEnabled() && packet.getType() == null) {
            log.debug("Unknown ActorEvent {} in packet {}", event, packet);
        }
    }
}
