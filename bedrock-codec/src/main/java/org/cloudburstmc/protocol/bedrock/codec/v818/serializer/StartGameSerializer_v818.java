package org.cloudburstmc.protocol.bedrock.codec.v818.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.StartGameSerializer_v776;
import org.cloudburstmc.protocol.bedrock.data.LevelSettings;
import org.cloudburstmc.protocol.bedrock.data.ServerAuthMovementMode;
import org.cloudburstmc.protocol.bedrock.data.SyncedPlayerMovementSettings;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class StartGameSerializer_v818 extends StartGameSerializer_v776 {

    public static final StartGameSerializer_v818 INSTANCE = new StartGameSerializer_v818();

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.writeLevelSettings(buffer, helper, settings);
        helper.writeString(buffer, settings.getOwnerId());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.readLevelSettings(buffer, helper, settings);
        settings.setOwnerId(helper.readString(buffer));
    }

    protected void writeSyncedPlayerMovementSettings(ByteBuf buffer, SyncedPlayerMovementSettings settings) {
        VarInts.writeInt(buffer, settings.getRewindHistorySize());
        buffer.writeBoolean(settings.isServerAuthoritativeBlockBreaking());
    }

    protected void readSyncedPlayerMovementSettings(ByteBuf buffer, StartGamePacket packet) {
        packet.setMovementSettings(new SyncedPlayerMovementSettings(
                ServerAuthMovementMode.SERVER_AUTHORITATIVE_V3,
                VarInts.readInt(buffer),
                buffer.readBoolean()
        ));
    }
}