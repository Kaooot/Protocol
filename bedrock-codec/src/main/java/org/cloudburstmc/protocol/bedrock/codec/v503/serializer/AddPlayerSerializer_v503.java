package org.cloudburstmc.protocol.bedrock.codec.v503.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.AdventureSettingsSerializer_v291;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.AddPlayerSerializer_v388;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class AddPlayerSerializer_v503 extends AddPlayerSerializer_v388 {

    protected static final GameType[] VALUES = GameType.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
        helper.writeUuid(buffer, packet.getUuid());
        helper.writeString(buffer, packet.getPlayerName());
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeString(buffer, packet.getPlatformChatId());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector3f(buffer, packet.getRotation());
        helper.writeItem(buffer, packet.getCarriedItem());
        VarInts.writeInt(buffer, packet.getPlayerGameType().ordinal());
        helper.writeActorData(buffer, packet.getActorData());
        AdventureSettingsSerializer_v291.INSTANCE.serialize(buffer, helper, packet.getAdventureSettings());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
        helper.writeString(buffer, packet.getDeviceId());
        buffer.writeIntLE(packet.getBuildPlatform().getId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddPlayerPacket packet) {
        packet.setUuid(helper.readUuid(buffer));
        packet.setPlayerName(helper.readString(buffer));
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setPlatformChatId(helper.readString(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector3f(buffer));
        packet.setCarriedItem(helper.readItem(buffer));
        packet.setPlayerGameType(VALUES[VarInts.readInt(buffer)]);
        helper.readActorData(buffer, packet.getActorData());
        AdventureSettingsSerializer_v291.INSTANCE.deserialize(buffer, helper, packet.getAdventureSettings());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
        packet.setDeviceId(helper.readString(buffer));
        packet.setBuildPlatform(BuildPlatform.from(buffer.readIntLE()));
    }
}
