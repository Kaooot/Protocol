package org.cloudburstmc.protocol.bedrock.codec.v534.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v503.serializer.AddPlayerSerializer_v503;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPlayerSerializer_v534 extends AddPlayerSerializer_v503 {
    public static final AddPlayerSerializer_v534 INSTANCE = new AddPlayerSerializer_v534();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
        helper.writeUuid(buffer, packet.getUuid());
        helper.writeString(buffer, packet.getPlayerName());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeString(buffer, packet.getPlatformChatId());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector3f(buffer, packet.getRotation());
        helper.writeItem(buffer, packet.getCarriedItem());
        VarInts.writeInt(buffer, packet.getPlayerGameType().ordinal());
        helper.writeActorData(buffer, packet.getActorData());
        helper.writeSerializedAbilitiesData(buffer, packet.getAbilitiesData());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
        helper.writeString(buffer, packet.getDeviceId());
        buffer.writeIntLE(packet.getBuildPlatform().ordinal());
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
        packet.setCarriedItem(helper.readItem(buffer));
        packet.setPlayerGameType(VALUES[VarInts.readInt(buffer)]);
        helper.readActorData(buffer, packet.getActorData());
        helper.readSerializedAbilitiesData(buffer, packet.getAbilitiesData());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
        packet.setDeviceId(helper.readString(buffer));
        packet.setBuildPlatform(BuildPlatform.from(buffer.readIntLE()));
    }
}
