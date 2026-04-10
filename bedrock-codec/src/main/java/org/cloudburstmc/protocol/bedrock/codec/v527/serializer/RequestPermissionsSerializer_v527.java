package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermissionLevel;
import org.cloudburstmc.protocol.bedrock.packet.RequestPermissionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Set;

@NoArgsConstructor
public class RequestPermissionsSerializer_v527 implements BedrockPacketSerializer<RequestPermissionsPacket> {

    private static final PlayerPermissionLevel[] VALUES = PlayerPermissionLevel.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
        buffer.writeLongLE(packet.getTargetPlayerId());
        VarInts.writeInt(buffer, packet.getPlayerPermissionLevel().ordinal());
        this.serializeBitset(buffer, packet.getCommandPermissionFlags());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
        packet.setTargetPlayerId(buffer.readLongLE());
        packet.setPlayerPermissionLevel(VALUES[VarInts.readInt(buffer)]);
        this.deserializeBitset(buffer, packet.getCommandPermissionFlags());
    }

    protected void serializeBitset(ByteBuf buffer, Set<AbilitiesIndex> set) {
        int commandPermssionFlags = 0;
        for (AbilitiesIndex value : set) {
            commandPermssionFlags |= (1 << (value.ordinal() - 1));
        }
        buffer.writeShortLE(commandPermssionFlags);
    }

    protected void deserializeBitset(ByteBuf buffer, Set<AbilitiesIndex> set) {
        final int commandPermissionFlags = buffer.readUnsignedShortLE();
        for (AbilitiesIndex value : AbilitiesIndex.values()) {
            if (value.ordinal() < this.getMinIndex() || value.ordinal() > this.getMaxIndex()) {
                continue;
            }
            if ((commandPermissionFlags & (1 << (value.ordinal() - 1))) != 0) {
                set.add(value);
            }
        }
    }

    protected long getMinIndex() {
        return 1;
    }

    protected long getMaxIndex() {
        return 8;
    }
}