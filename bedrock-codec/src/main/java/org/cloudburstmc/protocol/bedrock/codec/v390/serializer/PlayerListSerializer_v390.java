package org.cloudburstmc.protocol.bedrock.codec.v390.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.PlayerListSerializer_v388;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListAddEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.list.PlayerListPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerListSerializer_v390 extends PlayerListSerializer_v388 {
    public static final PlayerListSerializer_v390 INSTANCE = new PlayerListSerializer_v390();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getEntries().get(0).getPacketType().ordinal());
        helper.writeArray(buffer, packet.getEntries(), this::writePlayerListEntryVariant);

        if (packet.getEntries().get(0).getPacketType().equals(PlayerListPacketType.ADD)) {
            for (PlayerListEntry entry : packet.getEntries()) {
                buffer.writeBoolean(((PlayerListAddEntry) entry).isTrustedSkin());
            }
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerListPacket packet) {
        final PlayerListPacketType packetType = PlayerListPacketType.from(VarInts.readUnsignedInt(buffer));
        helper.readArray(buffer, packet.getEntries(), (buf, codecHelper) ->
                this.readPlayerListEntryVariant(buf, codecHelper, packetType));

        if (packetType.equals(PlayerListPacketType.ADD)) {
            for (int i = 0; i < packet.getEntries().size() && buffer.isReadable(); i++) {
                ((PlayerListAddEntry) packet.getEntries().get(i)).setTrustedSkin(buffer.readBoolean());
            }
        }
    }
}