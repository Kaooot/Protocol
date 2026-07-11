package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v557.serializer.AddPlayerSerializer_v557;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPlayerSerializer_v2168 extends AddPlayerSerializer_v557 {
    public static final AddPlayerSerializer_v2168 INSTANCE = new AddPlayerSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
        helper.writeUuid(buffer, packet.getUuid());
        helper.writeString(buffer, packet.getPlayerName());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeString(buffer, packet.getPlatformChatId());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector3f(buffer, packet.getRotation());
        helper.writeNetworkItemStackDescriptor(buffer, packet.getCarriedItem());
        VarInts.writeInt(buffer, packet.getPlayerGameType().ordinal());
        helper.writeActorData(buffer, packet.getActorData());
        helper.writeEntityProperties(buffer, packet.getSyncedProperties());
        helper.writeSerializedAbilitiesData(buffer, packet.getAbilitiesData());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
        helper.writeString(buffer, packet.getDeviceId());
        buffer.writeIntLE(packet.getBuildPlatform().getId()); // TODO check BuildPlatform enum
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
        packet.setUuid(helper.readUuid(buffer));
        packet.setPlayerName(helper.readString(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setPlatformChatId(helper.readString(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector3f(buffer));
        packet.setCarriedItem(helper.readNetworkItemStackDescriptor(buffer));
        packet.setPlayerGameType(VALUES[VarInts.readInt(buffer)]);
        helper.readActorData(buffer, packet.getActorData());
        helper.readEntityProperties(buffer, packet.getSyncedProperties());
        helper.readSerializedAbilitiesData(buffer, packet.getAbilitiesData());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
        packet.setDeviceId(helper.readString(buffer));
        packet.setBuildPlatform(BuildPlatform.from(buffer.readIntLE()));
    }
}