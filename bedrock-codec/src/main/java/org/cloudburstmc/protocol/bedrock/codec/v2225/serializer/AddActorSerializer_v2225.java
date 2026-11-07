package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v557.serializer.AddActorSerializer_v557;
import org.cloudburstmc.protocol.bedrock.packet.AddActorPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddActorSerializer_v2225 extends AddActorSerializer_v557 {
    public static final AddActorSerializer_v2225 INSTANCE = new AddActorSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeString(buffer, packet.getActorType());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector2f(buffer, packet.getRotation());
        buffer.writeFloatLE(packet.getYHeadRotation());
        buffer.writeFloatLE(packet.getYBodyRotation());
        helper.writeArray(buffer, packet.getAttributesList(), this::writeSyncedAttribute);
        helper.writeEntityData(buffer, packet.getActorData());
        helper.writePropertySyncData(buffer, packet.getSynchedProperties());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
        helper.writeOptionalNull(buffer, packet.getPassengerBlockData(), helper::writePassengerOfBlockArguments);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setActorType(helper.readString(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));
        packet.setYHeadRotation(buffer.readFloatLE());
        packet.setYBodyRotation(buffer.readFloatLE());
        helper.readArray(buffer, packet.getAttributesList(), this::readSyncedAttribute);
        helper.readEntityData(buffer, packet.getActorData());
        helper.readPropertySyncData(buffer, packet.getSynchedProperties());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
        packet.setPassengerBlockData(helper.readOptional(buffer, null, helper::readPassengerOfBlockArguments));
    }
}