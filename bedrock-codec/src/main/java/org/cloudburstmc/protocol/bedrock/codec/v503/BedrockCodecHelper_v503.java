package org.cloudburstmc.protocol.bedrock.codec.v503;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v471.BedrockCodecHelper_v471;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.AnimationMode;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureSettings;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v503 extends BedrockCodecHelper_v471 {
    public BedrockCodecHelper_v503(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        final StructureSettings structureSettings = new StructureSettings();
        structureSettings.setStructurePaletteName(this.readString(buffer));
        structureSettings.setShouldIgnoreEntities(buffer.readBoolean());
        structureSettings.setShouldIgnoreBlocks(buffer.readBoolean());
        structureSettings.setShouldAllowNonTickingPlayerAndTickingAreaChunks(buffer.readBoolean());
        structureSettings.setStructureSize(this.readBlockPosition(buffer));
        structureSettings.setStructureOffset(this.readBlockPosition(buffer));
        structureSettings.setLastEditPlayer(VarInts.readLong(buffer));
        structureSettings.setRotation(Rotation.from(buffer.readUnsignedByte()));
        structureSettings.setMirror(Mirror.from(buffer.readUnsignedByte()));
        structureSettings.setAnimationMode(AnimationMode.from(buffer.readUnsignedByte()));
        structureSettings.setAnimationSeconds(buffer.readFloatLE());
        structureSettings.setIntegrityValue(buffer.readFloatLE());
        structureSettings.setIntegritySeed(buffer.readIntLE());
        structureSettings.setRotationPivot(this.readVector3f(buffer));
        return structureSettings;
    }

    @Override
    public void writeStructureSettings(ByteBuf buffer, StructureSettings settings) {
        this.writeString(buffer, settings.getStructurePaletteName());
        buffer.writeBoolean(settings.isShouldIgnoreEntities());
        buffer.writeBoolean(settings.isShouldIgnoreBlocks());
        buffer.writeBoolean(settings.isShouldAllowNonTickingPlayerAndTickingAreaChunks());
        this.writeBlockPosition(buffer, settings.getStructureSize());
        this.writeBlockPosition(buffer, settings.getStructureOffset());
        VarInts.writeLong(buffer, settings.getLastEditPlayer());
        buffer.writeByte(settings.getRotation().ordinal());
        buffer.writeByte(settings.getMirror().ordinal());
        buffer.writeByte(settings.getAnimationMode().ordinal());
        buffer.writeFloatLE(settings.getAnimationSeconds());
        buffer.writeFloatLE(settings.getIntegrityValue());
        buffer.writeIntLE(settings.getIntegritySeed());
        this.writeVector3f(buffer, settings.getRotationPivot());
    }
}
