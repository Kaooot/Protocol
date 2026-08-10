package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.AttributeData;
import org.cloudburstmc.protocol.bedrock.packet.AddActorPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddActorSerializer_v291 implements BedrockPacketSerializer<AddActorPacket> {
    public static final AddActorSerializer_v291 INSTANCE = new AddActorSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        VarInts.writeUnsignedInt(buffer, packet.getEntityType());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector2f(buffer, packet.getRotation());
        buffer.writeFloatLE(packet.getHeadRotation());
        helper.writeArray(buffer, packet.getAttributesList(), this::writeAttribute);
        helper.writeActorData(buffer, packet.getActorData());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setEntityType(VarInts.readUnsignedInt(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));
        packet.setHeadRotation(buffer.readFloatLE());
        helper.readArray(buffer, packet.getAttributesList(), this::readAttribute);
        helper.readActorData(buffer, packet.getActorData());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
    }

    public AttributeData readAttribute(ByteBuf buffer, BedrockCodecHelper helper) {

        String name = helper.readString(buffer);
        float min = buffer.readFloatLE();
        float val = buffer.readFloatLE();
        float max = buffer.readFloatLE();

        return new AttributeData(name, min, max, val);
    }

    public void writeAttribute(ByteBuf buffer, BedrockCodecHelper helper, AttributeData attribute) {
        requireNonNull(attribute, "attribute is null");

        helper.writeString(buffer, attribute.getAttributeName());
        buffer.writeFloatLE(attribute.getMinValue());
        buffer.writeFloatLE(attribute.getCurrentValue());
        buffer.writeFloatLE(attribute.getMaxValue());
    }
}
