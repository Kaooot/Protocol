package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.attribute.*;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v944 implements BedrockPacketSerializer<ClientboundAttributeLayerSyncPacket> {
    public static final ClientboundAttributeLayerSyncSerializer_v944 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v944();

    protected static final int NAME_LENGTH = 128;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        this.writeData(buffer, helper, packet);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        this.readData(buffer, helper, packet);
    }

    protected void writeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final Object data = packet.getData();
        final ClientboundAttributeLayerSyncDataType type;
        if (data instanceof UpdateAttributeLayersData) {
            type = ClientboundAttributeLayerSyncDataType.UPDATE_ATTRIBUTE_LAYERS;
        } else if (data instanceof UpdateAttributeLayerSettingsData) {
            type = ClientboundAttributeLayerSyncDataType.UPDATE_ATTRIBUTE_LAYER_SETTINGS;
        } else if (data instanceof UpdateEnvironmentAttributesData) {
            type = ClientboundAttributeLayerSyncDataType.UPDATE_ENVIRONMENT_ATTRIBUTES;
        } else if (data instanceof RemoveEnvironmentAttributesData) {
            type = ClientboundAttributeLayerSyncDataType.REMOVE_ENVIRONMENT_ATTRIBUTES;
        } else {
            return;
        }
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        switch (type) {
            case UPDATE_ATTRIBUTE_LAYERS:
                this.writeUpdateAttributeLayersData(buffer, helper, packet, (UpdateAttributeLayersData) data);
                break;
            case UPDATE_ATTRIBUTE_LAYER_SETTINGS:
                this.writeUpdateAttributeLayerSettingsData(buffer, helper, packet, (UpdateAttributeLayerSettingsData) data);
                break;
            case UPDATE_ENVIRONMENT_ATTRIBUTES:
                this.writeUpdateEnvironmentAttributesData(buffer, helper, packet, (UpdateEnvironmentAttributesData) data);
                break;
            case REMOVE_ENVIRONMENT_ATTRIBUTES:
                this.writeRemoveEnvironmentAttributesData(buffer, helper, packet, (RemoveEnvironmentAttributesData) data);
                break;
        }
    }

    protected void readData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        switch (ClientboundAttributeLayerSyncDataType.values()[VarInts.readUnsignedInt(buffer)]) {
            case UPDATE_ATTRIBUTE_LAYERS:
                packet.setData(this.readUpdateAttributeLayersData(buffer, helper, packet));
                break;
            case UPDATE_ATTRIBUTE_LAYER_SETTINGS:
                packet.setData(this.readUpdateAttributeLayerSettingsData(buffer, helper, packet));
                break;
            case UPDATE_ENVIRONMENT_ATTRIBUTES:
                packet.setData(this.readUpdateEnvironmentAttributesData(buffer, helper, packet));
                break;
            case REMOVE_ENVIRONMENT_ATTRIBUTES:
                packet.setData(this.readRemoveEnvironmentAttributesData(buffer, helper, packet));
                break;
        }
    }

    protected void writeAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, Object attribute) {
        final AttributeDataType type;
        if (attribute instanceof BoolAttributeData) {
            type = AttributeDataType.BOOL;
        } else if (attribute instanceof FloatAttributeData) {
            type = AttributeDataType.FLOAT;
        } else if (attribute instanceof ColorAttributeData) {
            type = AttributeDataType.COLOR;
        } else {
            return;
        }
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        switch (type) {
            case BOOL:
                this.writeBoolAttributeData(buffer, helper, packet, (BoolAttributeData) attribute);
                break;
            case FLOAT:
                this.writeFloatAttributeData(buffer, helper, packet, (FloatAttributeData) attribute);
                break;
            case COLOR:
                this.writeColorAttributeData(buffer, helper, packet, (ColorAttributeData) attribute);
                break;
        }
    }

    protected Object readAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        return this.readAttributeData(buffer, helper, packet, AttributeDataType.from(VarInts.readUnsignedInt(buffer)));
    }

    protected Object readAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, AttributeDataType type) {
        switch (type) {
            case BOOL:
                return this.readBoolAttributeData(buffer, helper, packet);
            case FLOAT:
                return this.readFloatAttributeData(buffer, helper, packet);
            case COLOR:
                return this.readColorAttributeData(buffer, helper, packet);
        }
        return null;
    }

    protected void writeUpdateAttributeLayersData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, UpdateAttributeLayersData data) {
        helper.writeArray(
                buffer,
                data.getAttributeLayers(),
                (buf, codecHelper, value) -> this.writeAttributeLayerData(buf, codecHelper, packet, value)
        );
    }

    protected UpdateAttributeLayersData readUpdateAttributeLayersData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final UpdateAttributeLayersData data = new UpdateAttributeLayersData();
        helper.readArray(
                buffer,
                data.getAttributeLayers(),
                (buf, codecHelper) -> this.readAttributeLayerData(buf, codecHelper, packet),
                512
        );
        return data;
    }

    protected void writeUpdateAttributeLayerSettingsData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, UpdateAttributeLayerSettingsData data) {
        helper.writeString(buffer, data.getAttributeLayerName());
        VarInts.writeInt(buffer, data.getAttributeLayerDimension().getValue());
        this.writeAttributeLayerSettings(buffer, helper, data.getAttributesLayerSettings());
    }

    protected UpdateAttributeLayerSettingsData readUpdateAttributeLayerSettingsData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final UpdateAttributeLayerSettingsData data = new UpdateAttributeLayerSettingsData();
        data.setAttributeLayerName(helper.readStringMaxLen(buffer, 128));
        data.setAttributeLayerDimension(DimensionType.from(VarInts.readInt(buffer)));
        data.setAttributesLayerSettings(this.readAttributeLayerSettings(buffer, helper));
        return data;
    }

    protected void writeUpdateEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, UpdateEnvironmentAttributesData data) {
        helper.writeString(buffer, data.getAttributeLayerName());
        VarInts.writeInt(buffer, data.getAttributeLayerDimension().getValue());
        helper.writeArray(
                buffer,
                data.getAttributes(),
                (buf, codecHelper, value) -> this.writeEnvironmentAttributeData(buf, codecHelper, packet, value)
        );
    }

    protected UpdateEnvironmentAttributesData readUpdateEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final UpdateEnvironmentAttributesData data = new UpdateEnvironmentAttributesData();
        data.setAttributeLayerName(helper.readStringMaxLen(buffer, 128));
        data.setAttributeLayerDimension(DimensionType.from(VarInts.readInt(buffer)));
        helper.readArray(
                buffer,
                data.getAttributes(),
                (buf, codecHelper) -> this.readEnvironmentAttributeData(buf, codecHelper, packet),
                1024
        );
        return data;
    }

    protected void writeRemoveEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, RemoveEnvironmentAttributesData data) {
        helper.writeString(buffer, data.getAttributeLayerName());
        VarInts.writeInt(buffer, data.getAttributeLayerDimension().getValue());
        helper.writeArray(buffer, data.getAttributes(), helper::writeString);
    }

    protected RemoveEnvironmentAttributesData readRemoveEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final RemoveEnvironmentAttributesData data = new RemoveEnvironmentAttributesData();
        data.setAttributeLayerName(helper.readStringMaxLen(buffer, 128));
        data.setAttributeLayerDimension(DimensionType.from(VarInts.readInt(buffer)));
        helper.readArray(buffer, data.getAttributes(), (buf, codecHelper) -> codecHelper.readStringMaxLen(buf, 128), 1024);
        return data;
    }

    protected void writeAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, AttributeLayerData data) {
        helper.writeString(buffer, data.getName());
        VarInts.writeInt(buffer, data.getDimension().getValue());
        this.writeAttributeLayerSettings(buffer, helper, data.getSettings());
        helper.writeArray(
                buffer,
                data.getAttributes(),
                (buf, codecHelper, value) -> this.writeEnvironmentAttributeData(buffer, codecHelper, packet, value)
        );
    }

    protected AttributeLayerData readAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final AttributeLayerData data = new AttributeLayerData();
        data.setName(helper.readStringMaxLen(buffer, NAME_LENGTH));
        data.setDimension(DimensionType.from(VarInts.readInt(buffer)));
        data.setSettings(this.readAttributeLayerSettings(buffer, helper));
        helper.readArray(
                buffer,
                data.getAttributes(),
                (buf, codecHelper) -> this.readEnvironmentAttributeData(buffer, codecHelper, packet),
                1024
        );
        return data;
    }

    protected void writeAttributeLayerSettings(ByteBuf buffer, BedrockCodecHelper helper, AttributeLayerSettings settings) {
        buffer.writeIntLE(settings.getPriority());
        this.writeWeight(buffer, helper, settings.getWeight());
        buffer.writeBoolean(settings.isEnabled());
        buffer.writeBoolean(settings.isTransitionsPaused());
    }

    protected AttributeLayerSettings readAttributeLayerSettings(ByteBuf buffer, BedrockCodecHelper helper) {
        final AttributeLayerSettings settings = new AttributeLayerSettings();
        settings.setPriority(buffer.readIntLE());
        settings.setWeight(this.readWeight(buffer, helper));
        settings.setEnabled(buffer.readBoolean());
        settings.setTransitionsPaused(buffer.readBoolean());
        return settings;
    }

    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, EnvironmentAttributeData data) {
        helper.writeString(buffer, data.getAttributeName());
        helper.writeOptionalNull(
                buffer,
                data.getFromAttribute(),
                (buf, codecHelper, attribute) -> this.writeAttributeData(buf, codecHelper, packet, attribute)
        );
        this.writeAttributeData(buffer, helper, packet, data.getAttribute());
        helper.writeOptionalNull(
                buffer,
                data.getToAttribute(),
                (buf, codecHelper, attribute) -> this.writeAttributeData(buf, codecHelper, packet, attribute)
        );
        buffer.writeIntLE(data.getCurrentTransitionTicks());
        buffer.writeIntLE(data.getTotalTransitionTicks());
        helper.writeString(buffer, data.getEasing().getSerializeName());
    }

    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final EnvironmentAttributeData data = new EnvironmentAttributeData();
        data.setAttributeName(helper.readString(buffer));
        data.setFromAttribute(
                helper.readOptional(
                        buffer,
                        null,
                        (buf, codecHelper) -> this.readAttributeData(buf, codecHelper, packet)
                )
        );
        data.setAttribute(this.readAttributeData(buffer, helper, packet));
        data.setToAttribute(
                helper.readOptional(
                        buffer,
                        null,
                        (buf, codecHelper) -> this.readAttributeData(buf, codecHelper, packet)
                )
        );
        data.setCurrentTransitionTicks(buffer.readIntLE());
        data.setTotalTransitionTicks(buffer.readIntLE());
        data.setEasing(EasingFunction.fromName(helper.readString(buffer)));
        return data;
    }

    protected void writeBoolAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, BoolAttributeData data) {
        buffer.writeBoolean(data.isValue());
        helper.writeOptionalNull(buffer, data.getOperation().getSerializeName(), helper::writeString);
    }

    protected BoolAttributeData readBoolAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final BoolAttributeData data = new BoolAttributeData();
        data.setValue(buffer.readBoolean());
        data.setOperation(helper.readOptional(buffer, null,
                (buf, h) -> BoolAttributeOperation.valueOf(h.readString(buf))));
        return data;
    }

    protected void writeFloatAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, FloatAttributeData data) {
        buffer.writeFloatLE(data.getValue());
        helper.writeOptionalNull(buffer, data.getOperation().getSerializeName(), helper::writeString);
        helper.writeOptionalNull(buffer, data.getConstraintMin(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getConstraintMax(), ByteBuf::writeFloatLE);
    }

    protected FloatAttributeData readFloatAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final FloatAttributeData data = new FloatAttributeData();
        data.setValue(buffer.readFloatLE());
        data.setOperation(helper.readOptional(buffer, null,
                (buf, h) -> FloatAttributeOperation.valueOf(h.readString(buffer))));
        data.setConstraintMin(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setConstraintMax(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        return data;
    }

    protected void writeColorAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, ColorAttributeData data) {
        this.writeColor255RGBA(buffer, helper, data.getColor());
        helper.writeOptionalNull(buffer, data.getOperation().getSerializeName(), helper::writeString);
    }

    protected ColorAttributeData readColorAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final ColorAttributeData data = new ColorAttributeData();
        data.setColor(this.readColor255RGBA(buffer, helper));
        data.setOperation(helper.readOptional(buffer, null,
                (buf, h) -> ColorAttributeOperation.valueOf(h.readString(buffer))));
        return data;
    }

    protected void writeColor255RGBA(ByteBuf buffer, BedrockCodecHelper helper, Color255RGBA color) {
        VarInts.writeUnsignedInt(buffer, color.getType());
        if (color.getType() == 0) {
            helper.writeString(buffer, color.getStringColor());
        } else {
            for (int i = 0; i < color.getArrayColor().length; i++) {
                buffer.writeIntLE(color.getArrayColor()[i]);
            }
        }
    }

    protected Color255RGBA readColor255RGBA(ByteBuf buffer, BedrockCodecHelper helper) {
        final Color255RGBA color = new Color255RGBA();
        color.setType(VarInts.readUnsignedInt(buffer));
        if (color.getType() == 0) {
            color.setStringColor(helper.readString(buffer));
        } else {
            for (int i = 0; i < color.getArrayColor().length; i++) {
                color.getArrayColor()[i] = buffer.readIntLE();
            }
        }
        return color;
    }

    protected void writeWeight(ByteBuf buffer, BedrockCodecHelper helper, AttributeLayerSettings.WeightData weight) {
        VarInts.writeUnsignedInt(buffer, weight.getType().ordinal());
        if (weight.getType().equals(AttributeLayerSettings.WeightData.Type.FLOAT)) {
            buffer.writeFloatLE(weight.getAsFloat());
        } else {
            helper.writeString(buffer, weight.getAsString());
        }
    }

    protected AttributeLayerSettings.WeightData readWeight(ByteBuf buffer, BedrockCodecHelper helper) {
        final AttributeLayerSettings.WeightData.Type type = AttributeLayerSettings.WeightData.Type.from(VarInts.readUnsignedInt(buffer));
        return new AttributeLayerSettings.WeightData(
                type,
                type.equals(AttributeLayerSettings.WeightData.Type.FLOAT) ? buffer.readFloatLE() : helper.readString(buffer)
        );
    }
}