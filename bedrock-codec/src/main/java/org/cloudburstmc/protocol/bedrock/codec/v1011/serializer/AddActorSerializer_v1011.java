package org.cloudburstmc.protocol.bedrock.codec.v1011.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.BaseBedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v557.serializer.AddActorSerializer_v557;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataFormat;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataType;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.AttributeData;
import org.cloudburstmc.protocol.bedrock.packet.AddActorPacket;
import org.cloudburstmc.protocol.bedrock.transformer.ActorDataTransformer;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Map;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkNotNull;

/**
 * @author Kaooot
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddActorSerializer_v1011 extends AddActorSerializer_v557 {
    public static final AddActorSerializer_v1011 INSTANCE = new AddActorSerializer_v1011();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeString(buffer, packet.getActorType());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector3f(buffer, packet.getVelocity());
        helper.writeVector2f(buffer, packet.getRotation());
        buffer.writeFloatLE(packet.getHeadRotation());
        buffer.writeFloatLE(packet.getBodyRotation());
        helper.writeArray(buffer, packet.getAttributesList(), this::writeAttribute);
        this.writeActorData(buffer, helper, packet.getActorData());
        helper.writeEntityProperties(buffer, packet.getSyncedProperties());
        helper.writeArray(buffer, packet.getActorLinks(), helper::writeActorLink);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AddActorPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setActorType(helper.readString(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setVelocity(helper.readVector3f(buffer));
        packet.setRotation(helper.readVector2f(buffer));
        packet.setHeadRotation(buffer.readFloatLE());
        packet.setBodyRotation(buffer.readFloatLE());
        helper.readArray(buffer, packet.getAttributesList(), this::readAttribute);
        this.readActorData(buffer, helper, packet.getActorData());
        helper.readEntityProperties(buffer, packet.getSyncedProperties());
        helper.readArray(buffer, packet.getActorLinks(), helper::readActorLink);
    }

    @Override
    public void writeAttribute(ByteBuf buffer, BedrockCodecHelper helper, AttributeData attribute) {
        helper.writeString(buffer, attribute.getAttributeName());
        buffer.writeFloatLE(attribute.getMinValue());
        buffer.writeFloatLE(attribute.getCurrentValue());
        buffer.writeFloatLE(attribute.getMaxValue());
    }

    @Override
    public AttributeData readAttribute(ByteBuf buffer, BedrockCodecHelper helper) {
        final String attributeName = helper.readString(buffer);
        final float minValue = buffer.readFloatLE();
        final float currentValue = buffer.readFloatLE();
        final float maxValue = buffer.readFloatLE();
        return new AttributeData(attributeName, minValue, maxValue, currentValue);
    }

    @SuppressWarnings("unchecked")
    public void writeActorData(ByteBuf buffer, BedrockCodecHelper helper, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        VarInts.writeUnsignedInt(buffer, actorDataMap.size());

        for (Map.Entry<ActorDataType<?>, Object> entry : actorDataMap.entrySet()) {
            ActorDataTypeMap.Definition<?> definition = ((BaseBedrockCodecHelper) helper).getActorData().fromType(entry.getKey());

            if (definition == null) {
                throw new NullPointerException("Failed to get definition for Actor Data Type: " + entry.getKey());
            }

            VarInts.writeUnsignedInt(buffer, definition.getId());
            VarInts.writeUnsignedInt(buffer, definition.getFormat().ordinal());
            switch (definition.getFormat()) {
                case BYTE:
                case SHORT:
                case INT:
                case FLOAT:
                case STRING:
                    helper.writeString(buffer, definition.getFormat().name().toLowerCase());
                    break;
                case LONG:
                    helper.writeString(buffer, "int64");
                    break;
                case VECTOR3I:
                    helper.writeString(buffer, "pos");
                    break;
                case VECTOR3F:
                    helper.writeString(buffer, "vec3");
                    break;
                case NBT:
                    helper.writeString(buffer, "compoundtag");
                    break;
            }

            try {
                Object value = ((ActorDataTransformer<?, Object>) definition.getTransformer())
                        .serialize(helper, actorDataMap, entry.getValue());

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
                        helper.writeString(buffer, (String) value);
                        break;
                    case NBT:
                        helper.writeTag(buffer, value);
                        break;
                    case VECTOR3I:
                        helper.writeVector3i(buffer, (Vector3i) value);
                        break;
                    case LONG:
                        VarInts.writeLong(buffer, (long) value);
                        break;
                    case VECTOR3F:
                        helper.writeVector3f(buffer, (Vector3f) value);
                        break;
                    default:
                        throw new UnsupportedOperationException("Unknown actor data type " + definition.getFormat());
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to encode ActorData " + definition.getId() + " of " + definition.getType().getTypeName(), e);
            }
        }
    }

    public void readActorData(ByteBuf buffer, BedrockCodecHelper helper, ActorDataMap actorDataMap) {
        checkNotNull(actorDataMap, "actorDataDictionary");

        int length = VarInts.readUnsignedInt(buffer);
        checkArgument(helper.getEncodingSettings().maxListSize() <= 0 || length <= helper.getEncodingSettings().maxListSize(), "Entity data size is too big: %s", length);

        for (int i = 0; i < length; i++) {
            int id = VarInts.readUnsignedInt(buffer);
            int formatId = VarInts.readUnsignedInt(buffer);
            ActorDataFormat format = ActorDataFormat.values()[formatId];
            helper.readString(buffer); // Type
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
                    value = helper.readString(buffer);
                    break;
                case NBT:
                    value = helper.readTag(buffer, Object.class);
                    break;
                case VECTOR3I:
                    value = helper.readVector3i(buffer);
                    break;
                case LONG:
                    value = VarInts.readLong(buffer);
                    break;
                case VECTOR3F:
                    value = helper.readVector3f(buffer);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown actor data type received");
            }

            ActorDataTypeMap.Definition<?>[] definitions = ((BaseBedrockCodecHelper) helper).getActorData().fromId(id, format);
            if (definitions != null) {
                for (ActorDataTypeMap.Definition<?> definition : definitions) {
                    //noinspection unchecked
                    ActorDataTransformer<Object, ?> transformer = (ActorDataTransformer<Object, ?>) definition.getTransformer();
                    Object transformedValue = transformer.deserialize(helper, actorDataMap, value);
                    if (transformedValue != null) {
                        actorDataMap.put(definition.getType(), transformer.deserialize(helper, actorDataMap, value));
                    }
                }
            } else {
                log.debug("Unknown actor data: {} type {} value {}", id, format, value);
            }
        }
    }
}