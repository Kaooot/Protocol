package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.actor.attribute.SyncedAttribute;
import org.cloudburstmc.protocol.bedrock.packet.AddActorPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddActorSerializer_v291 implements BedrockPacketSerializer<AddActorPacket> {
    public static final AddActorSerializer_v291 INSTANCE = new AddActorSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        VarInts.writeUnsignedInt(buffer, packet.getActorTypeDeprecated());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector2f(buffer, packet.getRotation());
        buffer.writeFloatLE(packet.getYHeadRotation());
        helper.writeArray(buffer, packet.getAttributesList(), this::writeSyncedAttribute);
        helper.writeEntityData(buffer, packet.getActorData());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setActorTypeDeprecated(VarInts.readUnsignedInt(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));
        packet.setYHeadRotation(buffer.readFloatLE());
        helper.readArray(buffer, packet.getAttributesList(), this::readSyncedAttribute);
        helper.readEntityData(buffer, packet.getActorData());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
    }

    protected void writeSyncedAttribute(ByteBuf buffer, BedrockCodecHelper helper, SyncedAttribute attribute) {
        helper.writeString(buffer, attribute.getAttributeName());
        buffer.writeFloatLE(attribute.getMinValue());
        buffer.writeFloatLE(attribute.getCurrentValue());
        buffer.writeFloatLE(attribute.getMaxValue());
    }

    protected SyncedAttribute readSyncedAttribute(ByteBuf buffer, BedrockCodecHelper helper) {
        final SyncedAttribute attribute = new SyncedAttribute();
        attribute.setAttributeName(helper.readString(buffer));
        attribute.setMinValue(buffer.readFloatLE());
        attribute.setCurrentValue(buffer.readFloatLE());
        attribute.setMaxValue(buffer.readFloatLE());
        return attribute;
    }
}