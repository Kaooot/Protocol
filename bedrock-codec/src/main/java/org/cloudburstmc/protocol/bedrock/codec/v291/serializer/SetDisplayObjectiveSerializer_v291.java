package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ObjectiveSortOrder;
import org.cloudburstmc.protocol.bedrock.packet.SetDisplayObjectivePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetDisplayObjectiveSerializer_v291 implements BedrockPacketSerializer<SetDisplayObjectivePacket> {
    public static final SetDisplayObjectiveSerializer_v291 INSTANCE = new SetDisplayObjectiveSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetDisplayObjectivePacket packet) {
        helper.writeString(buffer, packet.getDisplaySlotName());
        helper.writeString(buffer, packet.getObjectiveName());
        helper.writeString(buffer, packet.getObjectiveDisplayName());
        helper.writeString(buffer, packet.getCriteriaName());
        VarInts.writeInt(buffer, packet.getSortOrder().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetDisplayObjectivePacket packet) {
        packet.setDisplaySlotName(helper.readString(buffer));
        packet.setObjectiveName(helper.readString(buffer));
        packet.setObjectiveDisplayName(helper.readString(buffer));
        packet.setCriteriaName(helper.readString(buffer));
        packet.setSortOrder(ObjectiveSortOrder.values()[VarInts.readInt(buffer)]);
    }
}
