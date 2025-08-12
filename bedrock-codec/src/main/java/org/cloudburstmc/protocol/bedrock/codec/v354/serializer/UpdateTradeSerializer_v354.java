package org.cloudburstmc.protocol.bedrock.codec.v354.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateTradePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateTradeSerializer_v354 implements BedrockPacketSerializer<UpdateTradePacket> {
    public static final UpdateTradeSerializer_v354 INSTANCE = new UpdateTradeSerializer_v354();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
        buffer.writeByte(packet.getContainerId());
        buffer.writeByte(packet.getType().getId());
        VarInts.writeInt(buffer, packet.getSize());
        VarInts.writeInt(buffer, packet.getTradeTier());
        VarInts.writeLong(buffer, packet.getEntityUniqueId());
        VarInts.writeLong(buffer, packet.getLastTradingPlayer());
        helper.writeString(buffer, packet.getDisplayName());
        buffer.writeBoolean(packet.isUseNewTradeScreen());
        buffer.writeBoolean(packet.isUsingEconomyTrade());
        helper.writeTag(buffer, packet.getOffers());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
        packet.setContainerId(buffer.readByte());
        packet.setType(ContainerType.from(buffer.readByte()));
        packet.setSize(VarInts.readInt(buffer));
        packet.setTradeTier(VarInts.readInt(buffer));
        packet.setEntityUniqueId(VarInts.readLong(buffer));
        packet.setLastTradingPlayer(VarInts.readLong(buffer));
        packet.setDisplayName(helper.readString(buffer));
        packet.setUseNewTradeScreen(buffer.readBoolean());
        packet.setUsingEconomyTrade(buffer.readBoolean());
        packet.setOffers(helper.readTag(buffer, NbtMap.class));
    }
}
