package org.cloudburstmc.protocol.bedrock.codec.v440;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v431.BedrockCodecHelper_v431;
import org.cloudburstmc.protocol.bedrock.data.GameRuleData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.AnimationMode;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureSettings;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v440 extends BedrockCodecHelper_v431 {

    public BedrockCodecHelper_v440(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerSlotTypes) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
    }

    @Override
    public void writeGameRule(ByteBuf buffer, GameRuleData<?> gameRule) {
        Preconditions.checkNotNull(buffer, "buffer");
        Preconditions.checkNotNull(gameRule, "gameRule");

        Object value = gameRule.getValue();
        int id = this.gameRuleType.getId(value.getClass());

        writeString(buffer, gameRule.getName());
        buffer.writeBoolean(gameRule.isEditable());
        VarInts.writeUnsignedInt(buffer, id);
        switch (id) {
            case 1:
                buffer.writeBoolean((boolean) value);
                break;
            case 2:
                VarInts.writeUnsignedInt(buffer, (int) value);
                break;
            case 3:
                buffer.writeFloatLE((float) value);
                break;
        }
    }

    @Override
    public GameRuleData<?> readGameRule(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");

        String name = readString(buffer);
        boolean editable = buffer.readBoolean();
        GameRuleData.Type type = GameRuleData.Type.from(VarInts.readUnsignedInt(buffer));

        switch (type) {
            case BOOL:
                return new GameRuleData<>(name, editable, type, buffer.readBoolean());
            case INT:
                return new GameRuleData<>(name, editable, type, VarInts.readUnsignedInt(buffer));
            case FLOAT:
                return new GameRuleData<>(name, editable, type, buffer.readFloatLE());
        }
        throw new IllegalStateException("Invalid gamerule type received");
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        final StructureSettings structureSettings = new StructureSettings();
        structureSettings.setStructurePaletteName(this.readString(buffer));
        structureSettings.setShouldIgnoreEntities(buffer.readBoolean());
        structureSettings.setShouldIgnoreBlocks(buffer.readBoolean());
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
