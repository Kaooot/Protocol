package org.cloudburstmc.protocol.bedrock.codec.v944;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v924.BedrockCodecHelper_v924;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.payload.common.GatheringsConfigurationJoinInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.configuration.PresenceConfiguration;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v944 extends BedrockCodecHelper_v924 {

    public BedrockCodecHelper_v944(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public void writeBlockPosition(ByteBuf buffer, Vector3i blockPosition) {
        VarInts.writeInt(buffer, blockPosition.getX());
        VarInts.writeInt(buffer, blockPosition.getY());
        VarInts.writeInt(buffer, blockPosition.getZ());
    }

    @Override
    public Vector3i readBlockPosition(ByteBuf buffer) {
        int x = VarInts.readInt(buffer);
        int y = VarInts.readInt(buffer);
        int z = VarInts.readInt(buffer);
        return Vector3i.from(x, y, z);
    }

    @Override
    public void writeGatheringsConfigurationJoinInfo(ByteBuf buffer, GatheringsConfigurationJoinInfo config) {
        this.writeUuid(buffer, config.getExperienceId());
        this.writeString(buffer, config.getExperienceName());
        this.writeOptionalNull(buffer, config.getWorldId(), this::writeUuid);
        this.writeOptionalNull(buffer, config.getWorldName(), this::writeString);
        this.writeString(buffer, config.getCreatorId());
        this.writeOptionalNull(buffer, config.getTargetId(), this::writeUuid);
        this.writeOptionalNull(buffer, config.getScenarioId(), this::writeString);
        this.writeOptionalNull(buffer, config.getServerId(), this::writeString);
    }

    @Override
    public GatheringsConfigurationJoinInfo readGatheringsConfigurationJoinInfo(ByteBuf buffer) {
        final GatheringsConfigurationJoinInfo config = new GatheringsConfigurationJoinInfo();
        config.setExperienceId(this.readUuid(buffer));
        config.setExperienceName(this.readStringMaxLen(buffer, 29));
        config.setWorldId(this.readOptional(buffer, null, this::readUuid));
        config.setWorldName(this.readOptional(buffer, null, (buf, codecHelper) -> codecHelper.readStringMaxLen(buf, 29)));
        config.setCreatorId(this.readStringMaxLen(buffer, 60));
        config.setTargetId(this.readOptional(buffer, null, this::readUuid));
        config.setScenarioId(this.readOptional(buffer, null, (buf, codecHelper) -> codecHelper.readStringMaxLen(buf, 100)));
        config.setServerId(this.readOptional(buffer, null, (buf, codecHelper) -> codecHelper.readStringMaxLen(buf, 100)));
        return config;
    }

    @Override
    public void writePresenceConfiguration(ByteBuf buffer, PresenceConfiguration configuration) {
        this.writeString(buffer, configuration.getExperienceName());
        this.writeString(buffer, configuration.getWorldName());
    }

    @Override
    public PresenceConfiguration readPresenceConfiguration(ByteBuf buffer) {
        final PresenceConfiguration config = new PresenceConfiguration();
        config.setExperienceName(this.readString(buffer));
        config.setWorldName(this.readString(buffer));
        return config;
    }
}