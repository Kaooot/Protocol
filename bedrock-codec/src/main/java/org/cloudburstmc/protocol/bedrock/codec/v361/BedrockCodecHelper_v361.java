package org.cloudburstmc.protocol.bedrock.codec.v361;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v340.BedrockCodecHelper_v340;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType;
import org.cloudburstmc.protocol.bedrock.data.structure.AnimationMode;
import org.cloudburstmc.protocol.bedrock.data.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.transformer.EntityDataTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.*;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

public class BedrockCodecHelper_v361 extends BedrockCodecHelper_v340 {

    public BedrockCodecHelper_v361(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
        super(entityData, gameRulesTypes);
    }

    @Override
    public void readEntityData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "entityDataMap");

        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(this.encodingSettings.maxListSize() <= 0 || length <= this.encodingSettings.maxListSize(), "Entity data size is too big: %s", length);

        for (int i = 0; i < length; i++) {
            int id = VarInts.readUnsignedInt(buffer);
            int formatId = VarInts.readUnsignedInt(buffer);
            ActorDataFormat format = ActorDataFormat.values()[formatId];

            Object value;
            switch (format) {
                case BYTE:
                    value = buffer.readByte();
                    break;
                case SHORT:
                    value = buffer.readShortLE();
                    break;
                case INT:
                    value = VarInts.readInt(buffer);
                    break;
                case FLOAT:
                    value = buffer.readFloatLE();
                    break;
                case STRING:
                    value = readString(buffer);
                    break;
                case NBT:
                    value = this.readTag(buffer, Object.class);
                    break;
                case VECTOR3I:
                    value = readVector3i(buffer);
                    break;
                case LONG:
                    value = VarInts.readLong(buffer);
                    break;
                case VECTOR3F:
                    value = readVector3f(buffer);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown entity data type received");
            }

            ActorDataTypeMap.Definition<?>[] definitions = this.entityData.fromId(id, format);
            if (definitions != null) {
                for (ActorDataTypeMap.Definition<?> definition : definitions) {
                    //noinspection unchecked
                    EntityDataTransformer<Object, ?> transformer = (EntityDataTransformer<Object, ?>) definition.getTransformer();
                    Object transformedValue = transformer.deserialize(this, actorDataMap, value);
                    if (transformedValue != null) {
                        actorDataMap.put(definition.getType(), transformedValue);
                    }
                }
            } else {
                log.debug("Unknown entity data: {} type {} value {}", id, format, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeEntityData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "entityDataMap");

        // Collect serialized entries first
        List<Map.Entry<ActorDataTypeMap.Definition<?>, Object>> serializedEntries = new LinkedList<>();

        for (Map.Entry<ActorDataType<?>, Object> entry : actorDataMap.entrySet()) {
            ActorDataTypeMap.Definition<?> definition = this.entityData.fromType(entry.getKey());

            try {
                Object value = ((EntityDataTransformer<?, Object>) definition.getTransformer())
                        .serialize(this, actorDataMap, entry.getValue());

                // Skip if transformer returns null (indicating this entry shouldn't be serialized)
                if (value == null) {
                    continue;
                }

                serializedEntries.add(new AbstractMap.SimpleEntry<>(definition, value));
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to encode EntityData " + definition.getId() + " of " + definition.getType().getTypeName(), e);
            }
        }

        VarInts.writeUnsignedInt(buffer, serializedEntries.size());

        for (Map.Entry<ActorDataTypeMap.Definition<?>, Object> entry : serializedEntries) {
            ActorDataTypeMap.Definition<?> definition = entry.getKey();
            Object value = entry.getValue();
            VarInts.writeUnsignedInt(buffer, definition.getId());
            VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());

            switch (definition.getFormat()) {
                case BYTE:
                    buffer.writeByte((byte) value);
                    break;
                case SHORT:
                    buffer.writeShortLE((short) value);
                    break;
                case INT:
                    VarInts.writeInt(buffer, (int) value);
                    break;
                case FLOAT:
                    buffer.writeFloatLE((float) value);
                    break;
                case STRING:
                    writeString(buffer, (String) value);
                    break;
                case NBT:
                    this.writeTag(buffer, value);
                    break;
                case VECTOR3I:
                    writeVector3i(buffer, (Vector3i) value);
                    break;
                case LONG:
                    VarInts.writeLong(buffer, (long) value);
                    break;
                case VECTOR3F:
                    writeVector3f(buffer, (Vector3f) value);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown entity data type " + definition.getFormat());
            }
        }
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        String paletteName = this.readString(buffer);
        boolean ignoringEntities = buffer.readBoolean();
        boolean ignoringBlocks = buffer.readBoolean();
        Vector3i size = this.readBlockPosition(buffer);
        Vector3i offset = this.readBlockPosition(buffer);
        long lastEditedByEntityId = VarInts.readLong(buffer);
        Rotation rotation = Rotation.from(buffer.readByte());
        Mirror mirror = Mirror.from(buffer.readByte());
        float integrityValue = buffer.readFloatLE();
        int integritySeed = buffer.readIntLE();

        return new StructureSettings(paletteName, ignoringEntities, ignoringBlocks, true, size, offset, lastEditedByEntityId,
                rotation, mirror, AnimationMode.NONE, 0f, integrityValue, integritySeed,
                Vector3f.ZERO);
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
        buffer.writeFloatLE(settings.getIntegrityValue());
        buffer.writeIntLE(settings.getIntegritySeed());
    }
}