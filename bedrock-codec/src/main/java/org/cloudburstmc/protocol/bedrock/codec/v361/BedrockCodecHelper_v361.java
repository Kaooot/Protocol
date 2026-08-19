package org.cloudburstmc.protocol.bedrock.codec.v361;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v340.BedrockCodecHelper_v340;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.transformer.ActorDataTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Map;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

public class BedrockCodecHelper_v361 extends BedrockCodecHelper_v340 {

    public BedrockCodecHelper_v361(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
        super(entityData, gameRulesTypes);
    }

    @Override
    public void readActorData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

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
                    throw new IllegalArgumentException("Unknown actor data type received");
            }

            ActorDataTypeMap.Definition<?>[] definitions = this.actorData.fromId(id, format);
            if (definitions != null) {
                for (ActorDataTypeMap.Definition<?> definition : definitions) {
                    //noinspection unchecked
                    ActorDataTransformer<Object, ?> transformer = (ActorDataTransformer<Object, ?>) definition.getTransformer();
                    Object transformedValue = transformer.deserialize(this, actorDataMap, value);
                    if (transformedValue != null) {
                        actorDataMap.put(definition.getType(), transformer.deserialize(this, actorDataMap, value));
                    }
                }
            } else {
                log.debug("Unknown actor data: {} type {} value {}", id, format, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeActorData(ByteBuf buffer, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        VarInts.writeUnsignedInt(buffer, actorDataMap.size());

        for (Map.Entry<ActorDataType<?>, Object> entry : actorDataMap.entrySet()) {
            ActorDataTypeMap.Definition<?> definition = this.actorData.fromType(entry.getKey());

            if (definition == null) {
                throw new NullPointerException("Failed to get definition for Actor Data Type: " + entry.getKey());
            }

            VarInts.writeUnsignedInt(buffer, definition.getId());
            VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());

            try {
                Object value = ((ActorDataTransformer<?, Object>) definition.getTransformer())
                        .serialize(this, actorDataMap, entry.getValue());

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
                        throw new UnsupportedOperationException("Unknown actor data type " + definition.getFormat());
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to encode ActorData " + definition.getId() + " of " + definition.getType().getTypeName(), e);
            }
        }
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        final StructureSettings structureSettings = new StructureSettings();
        structureSettings.setStructurePaletteName(this.readStringMaxLen(buffer, 256));
        structureSettings.setShouldIgnoreEntities(buffer.readBoolean());
        structureSettings.setShouldIgnoreBlocks(buffer.readBoolean());
        structureSettings.setStructureSize(this.readBlockPosition(buffer));
        structureSettings.setStructureOffset(this.readBlockPosition(buffer));
        structureSettings.setLastEditPlayer(VarInts.readLong(buffer));
        structureSettings.setRotation(Rotation.from(buffer.readUnsignedByte()));
        structureSettings.setMirror(Mirror.from(buffer.readUnsignedByte()));
        structureSettings.setIntegrityValue(buffer.readFloatLE());
        structureSettings.setIntegritySeed(buffer.readIntLE());
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
        buffer.writeFloatLE(settings.getIntegrityValue());
        buffer.writeIntLE(settings.getIntegritySeed());
    }
}
