package org.cloudburstmc.protocol.bedrock.codec.v944;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v924.BedrockCodecHelper_v924;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.connection.ClientStoreEntryPointConfig;
import org.cloudburstmc.protocol.bedrock.data.connection.PresenceConfig;
import org.cloudburstmc.protocol.bedrock.data.connection.ServerConfig;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

public class BedrockCodecHelper_v944 extends BedrockCodecHelper_v924 {

    public BedrockCodecHelper_v944(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public Vector3i readBlockPosition(ByteBuf buffer) {
        int x = VarInts.readInt(buffer);
        int y = VarInts.readInt(buffer);
        int z = VarInts.readInt(buffer);

        return Vector3i.from(x, y, z);
    }

    @Override
    public void writeBlockPosition(ByteBuf buffer, Vector3i blockPosition) {
        checkNotNull(blockPosition, "blockPosition");
        VarInts.writeInt(buffer, blockPosition.getX());
        VarInts.writeInt(buffer, blockPosition.getY());
        VarInts.writeInt(buffer, blockPosition.getZ());
    }

    @Override
    public void writeServerConfig(ByteBuf buffer, ServerConfig config) {
        this.writeOptionalNull(buffer, config.getGathering(), this::writeGatheringsConfig);
        this.writeOptionalNull(buffer, config.getClientStoreEntryPoint(), this::writeClientStoreEntryPointConfig);
        this.writeOptionalNull(buffer, config.getPresence(), this::writePresenceConfig);
    }

    @Override
    public ServerConfig readServerConfig(ByteBuf buffer) {
        final ServerConfig config = new ServerConfig();
        config.setGathering(this.readOptional(buffer, null, this::readGatheringsConfig));
        config.setClientStoreEntryPoint(this.readOptional(buffer, null, this::readClientStoreEntryPointConfig));
        config.setPresence(this.readOptional(buffer, null, this::readPresenceConfig));
        return config;
    }

    @Override
    public void writePresenceConfig(ByteBuf buffer, PresenceConfig config) {
        this.writeString(buffer, config.getExperienceName());
        this.writeString(buffer, config.getWorldName());
    }

    @Override
    public PresenceConfig readPresenceConfig(ByteBuf buffer) {
        final PresenceConfig config = new PresenceConfig();
        config.setExperienceName(this.readString(buffer));
        config.setWorldName(this.readString(buffer));
        return config;
    }

    @Override
    public void writeClientStoreEntryPointConfig(ByteBuf buffer, BedrockCodecHelper helper, ClientStoreEntryPointConfig config) {
        helper.writeString(buffer, config.getStoreId());
        helper.writeString(buffer, config.getStoreName());
    }

    @Override
    public ClientStoreEntryPointConfig readClientStoreEntryPointConfig(ByteBuf buffer, BedrockCodecHelper helper) {
        final ClientStoreEntryPointConfig configuration = new ClientStoreEntryPointConfig();
        configuration.setStoreId(helper.readString(buffer));
        configuration.setStoreName(helper.readString(buffer));
        return configuration;
    }
}