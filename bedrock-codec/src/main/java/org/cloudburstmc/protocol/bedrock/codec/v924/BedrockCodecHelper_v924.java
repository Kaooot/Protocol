package org.cloudburstmc.protocol.bedrock.codec.v924;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v898.BedrockCodecHelper_v898;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.connection.GatheringsConfig;
import org.cloudburstmc.protocol.bedrock.data.connection.ServerConfig;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class BedrockCodecHelper_v924 extends BedrockCodecHelper_v898 {

    public BedrockCodecHelper_v924(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeGatheringsConfig(ByteBuf buf, GatheringsConfig info) {
        this.writeUuid(buf, info.getExperienceId());
        this.writeString(buf, info.getExperienceName());
        this.writeUuid(buf, info.getWorldId());
        this.writeString(buf, info.getWorldName());
        this.writeString(buf, info.getCreatorId());
        this.writeUuid(buf, info.getTargetId());
        this.writeString(buf, info.getScenarioId());
        this.writeString(buf, info.getServerId());
    }

    @Override
    public GatheringsConfig readGatheringsConfig(ByteBuf buf) {
        final GatheringsConfig config = new GatheringsConfig();
        config.setExperienceId(this.readUuid(buf));
        config.setExperienceName(this.readString(buf));
        config.setWorldId(this.readUuid(buf));
        config.setWorldName(this.readString(buf));
        config.setCreatorId(this.readString(buf));
        config.setTargetId(this.readUuid(buf));
        config.setScenarioId(this.readString(buf));
        config.setServerId(this.readString(buf));
        return config;
    }

    @Override
    public void writeDataStoreUpdate(ByteBuf buffer, DataStoreUpdate update) {
        super.writeDataStoreUpdate(buffer, update);
        buffer.writeIntLE(update.getPathUpdateCount());
    }

    @Override
    public DataStoreUpdate readDataStoreUpdate(ByteBuf buffer) {
        final DataStoreUpdate update = super.readDataStoreUpdate(buffer);
        update.setPathUpdateCount(buffer.readIntLE());
        return update;
    }

    @Override
    public void writeServerConfig(ByteBuf buffer, ServerConfig config) {
        this.writeOptionalNull(buffer, config.getGathering(), this::writeGatheringsConfig);
    }

    @Override
    public ServerConfig readServerConfig(ByteBuf buffer) {
        final ServerConfig config = new ServerConfig();
        config.setGathering(this.readOptional(buffer, null, this::readGatheringsConfig));
        return config;
    }
}