package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.StartGameSerializer_v419;
import org.cloudburstmc.protocol.bedrock.data.player.input.ServerAuthMovementMode;
import org.cloudburstmc.protocol.bedrock.data.world.GameType;
import org.cloudburstmc.protocol.bedrock.data.player.input.SyncedPlayerMovementSettings;
import org.cloudburstmc.protocol.bedrock.data.world.ServerBlockProperty;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.TextConverter;
import org.cloudburstmc.protocol.common.util.VarInts;

@SuppressWarnings("DuplicatedCode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v428 extends StartGameSerializer_v419 {

    public static final StartGameSerializer_v428 INSTANCE = new StartGameSerializer_v428();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        VarInts.writeLong(buffer, packet.getEntityID());
        VarInts.writeUnsignedLong(buffer, packet.getRuntimeID());
        VarInts.writeInt(buffer, packet.getGameType().ordinal());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getRotation());

        this.writeLevelSettings(buffer, helper, packet.getSettings());

        helper.writeString(buffer, packet.getLevelID());
        final TextConverter converter = helper.getTextConverter();
        helper.writeString(buffer, converter.serialize(packet.getLevelName(CharSequence.class)));
        helper.writeString(buffer, packet.getTemplateContentIdentity());
        buffer.writeBoolean(packet.isTrial());
        this.writeSyncedPlayerMovementSettings(buffer, packet.getMovementSettings()); // new for v428
        buffer.writeLongLE(packet.getLevelCurrentTime());
        VarInts.writeInt(buffer, packet.getEnchantmentSeed());

        helper.writeArray(buffer, packet.getBlockProperties(), (buf, packetHelper, block) -> {
            packetHelper.writeString(buf, block.getBlockName());
            packetHelper.writeTag(buf, block.getBlockDefinition());
        });

        this.writeItemDefinitions(buffer, helper, packet.getItemDefinitions());

        helper.writeString(buffer, packet.getMultiplayerCorrelationId());
        buffer.writeBoolean(packet.isEnableItemStackNetManager());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        packet.setEntityID(VarInts.readLong(buffer));
        packet.setRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setGameType(GameType.from(VarInts.readInt(buffer)));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));

        this.readLevelSettings(buffer, helper, packet.getSettings());

        packet.setLevelID(helper.readString(buffer));
        final TextConverter converter = helper.getTextConverter();
        packet.setLevelName(converter.deserialize(helper.readString(buffer)));
        packet.setTemplateContentIdentity(helper.readString(buffer));
        packet.setTrial(buffer.readBoolean());
        packet.setMovementSettings(this.readSyncedPlayerMovementSettings(buffer)); // new for v428
        packet.setLevelCurrentTime(buffer.readLongLE());
        packet.setEnchantmentSeed(VarInts.readInt(buffer));

        helper.readArray(buffer, packet.getBlockProperties(), (buf, packetHelper) -> {
            ServerBlockProperty block = new ServerBlockProperty();
            block.setBlockName(packetHelper.readString(buf));
            block.setBlockDefinition(packetHelper.readTag(buf, NbtMap.class));
            return block;
        });

        this.readItemDefinitions(buffer, helper, packet.getItemDefinitions());

        packet.setMultiplayerCorrelationId(helper.readString(buffer));
        packet.setEnableItemStackNetManager(buffer.readBoolean());
    }

    protected void writeSyncedPlayerMovementSettings(ByteBuf buffer, SyncedPlayerMovementSettings settings) {
        VarInts.writeInt(buffer, settings.getAuthorityMode().ordinal());
        VarInts.writeInt(buffer, settings.getRewindHistorySize());
        buffer.writeBoolean(settings.isServerAuthoritativeBlockBreaking());
    }

    protected SyncedPlayerMovementSettings readSyncedPlayerMovementSettings(ByteBuf buffer) {
        final SyncedPlayerMovementSettings movementSettings = new SyncedPlayerMovementSettings();
        movementSettings.setAuthorityMode(ServerAuthMovementMode.from(VarInts.readInt(buffer)));
        movementSettings.setRewindHistorySize(VarInts.readInt(buffer));
        movementSettings.setServerAuthoritativeBlockBreaking(buffer.readBoolean());
        return movementSettings;
    }
}