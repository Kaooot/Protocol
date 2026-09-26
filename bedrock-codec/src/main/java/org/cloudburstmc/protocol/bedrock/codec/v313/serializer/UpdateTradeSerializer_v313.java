package org.cloudburstmc.protocol.bedrock.codec.v313.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.UpdateTradePacket;
import org.cloudburstmc.protocol.common.util.TextConverter;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateTradeSerializer_v313 implements BedrockPacketSerializer<UpdateTradePacket> {
    public static final UpdateTradeSerializer_v313 INSTANCE = new UpdateTradeSerializer_v313();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
        buffer.writeByte(packet.getContainerId());
        buffer.writeByte(packet.getType());
        VarInts.writeInt(buffer, packet.getSize());
        VarInts.writeInt(buffer, packet.isUseNewTradeScreen() ? 40 : 0);
        VarInts.writeInt(buffer, packet.getTraderTier());
        buffer.writeBoolean(packet.isRecipeAddedOnUpdate());
        VarInts.writeLong(buffer, packet.getEntityUniqueId());
        VarInts.writeLong(buffer, packet.getLastTradingPlayer());
        TextConverter converter = helper.getTextConverter();
        helper.writeString(buffer, converter.serialize(packet.getDisplayName(CharSequence.class)));
        helper.writeTag(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateTradePacket packet) {
        packet.setContainerId(buffer.readByte());
        packet.setType(buffer.readByte());
        packet.setSize(VarInts.readInt(buffer));
        packet.setUseNewTradeScreen(VarInts.readInt(buffer) >= 40);
        packet.setTraderTier(VarInts.readInt(buffer));
        packet.setRecipeAddedOnUpdate(buffer.readBoolean());
        packet.setEntityUniqueId(VarInts.readLong(buffer));
        packet.setLastTradingPlayer(VarInts.readLong(buffer));
        TextConverter converter = helper.getTextConverter();
        packet.setDisplayName(converter.deserialize(helper.readString(buffer)));
        packet.setData(helper.readTag(buffer, NbtMap.class));
    }
}
