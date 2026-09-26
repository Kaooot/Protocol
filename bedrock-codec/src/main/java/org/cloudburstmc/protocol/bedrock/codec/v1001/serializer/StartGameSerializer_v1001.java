package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.StartGameSerializer_v924;
import org.cloudburstmc.protocol.bedrock.data.editor.ServerEditorConnectionPolicy;
import org.cloudburstmc.protocol.bedrock.data.world.LevelSettings;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class StartGameSerializer_v1001 extends StartGameSerializer_v924 {

    public static final StartGameSerializer_v1001 INSTANCE = new StartGameSerializer_v1001();

    @Override
    protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.writeLevelSettings(buffer, helper, settings);

        VarInts.writeInt(buffer, settings.getServerEditorConnectionPolicy().ordinal());
        buffer.writeBoolean(settings.isAllowAnonymousBlockDropsInEditorWorlds());
    }

    @Override
    protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, LevelSettings settings) {
        super.readLevelSettings(buffer, helper, settings);

        settings.setServerEditorConnectionPolicy(ServerEditorConnectionPolicy.from(VarInts.readInt(buffer)));
        settings.setAllowAnonymousBlockDropsInEditorWorlds(buffer.readBoolean());
    }

    @Override
    protected void readBeforeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        packet.setNetworkPermissions(this.readNetworkPermissions(buffer, helper));

        packet.setChatLogging(buffer.readBoolean());
    }

    @Override
    protected void writeBeforeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        this.writeNetworkPermissions(buffer, helper, packet.getNetworkPermissions());

        buffer.writeBoolean(packet.isChatLogging());
    }
}