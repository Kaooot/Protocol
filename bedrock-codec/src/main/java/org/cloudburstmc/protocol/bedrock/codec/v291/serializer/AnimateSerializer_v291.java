package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.common.util.Int2ObjectBiMap;
import org.cloudburstmc.protocol.common.util.VarInts;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimateSerializer_v291 implements BedrockPacketSerializer<AnimatePacket> {
    public static final AnimateSerializer_v291 INSTANCE = new AnimateSerializer_v291();
    protected static final Int2ObjectBiMap<AnimatePacket.Action> types = new Int2ObjectBiMap<>();

    static {
        types.put(0, AnimatePacket.Action.NO_ACTION);
        types.put(1, AnimatePacket.Action.SWING);
        types.put(3, AnimatePacket.Action.WAKE_UP);
        types.put(4, AnimatePacket.Action.CRITICAL_HIT);
        types.put(5, AnimatePacket.Action.MAGIC_CRITICAL_HIT);
        types.put(128, AnimatePacket.Action.ROW_RIGHT);
        types.put(129, AnimatePacket.Action.ROW_LEFT);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = packet.getAction();
        VarInts.writeInt(buffer, types.get(action));
        VarInts.writeUnsignedLong(buffer, packet.getTargetActorRuntimeID());
        if (action == AnimatePacket.Action.ROW_LEFT || action == AnimatePacket.Action.ROW_RIGHT) {
            buffer.writeFloatLE(packet.getRowingTime());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnimatePacket packet) {
        AnimatePacket.Action action = types.get(VarInts.readInt(buffer));
        packet.setAction(action);
        packet.setTargetActorRuntimeID(VarInts.readUnsignedLong(buffer));
        if (action == AnimatePacket.Action.ROW_LEFT || action == AnimatePacket.Action.ROW_RIGHT) {
            packet.setRowingTime(buffer.readFloatLE());
        }
    }
}