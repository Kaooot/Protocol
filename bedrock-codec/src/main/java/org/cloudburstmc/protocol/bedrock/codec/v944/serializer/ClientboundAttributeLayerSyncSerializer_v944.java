package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.*;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.*;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v944 implements BedrockPacketSerializer<ClientboundAttributeLayerSyncPacket> {
    public static final ClientboundAttributeLayerSyncSerializer_v944 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v944();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getData().getType().ordinal());
        switch (packet.getData().getType()) {
            case UPDATE_ATTRIBUTE_LAYERS:
                this.writeUpdateAttributeLayersData(buffer, helper, (UpdateAttributeLayersData) packet.getData());
                break;
            case UPDATE_ATTRIBUTE_LAYER_SETTINGS:
                this.writeUpdateAttributeLayerSettingsData(buffer, helper, (UpdateAttributeLayerSettingsData) packet.getData());
                break;
            case UPDATE_ENVIRONMENT_ATTRIBUTES:
                this.writeUpdateEnvironmentAttributesData(buffer, helper, (UpdateEnvironmentAttributesData) packet.getData());
                break;
            case REMOVE_ENVIRONMENT_ATTRIBUTES:
                this.writeRemoveEnvironmentAttributesData(buffer, helper, (RemoveEnvironmentAttributesData) packet.getData());
                break;
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        final AttributeLayerSyncPacketData.Type type = AttributeLayerSyncPacketData.Type.from(VarInts.readUnsignedInt(buffer));
        switch (type) {
            case UPDATE_ATTRIBUTE_LAYERS:
                packet.setData(this.readUpdateAttributeLayersData(buffer, helper));
                break;
            case UPDATE_ATTRIBUTE_LAYER_SETTINGS:
                packet.setData(this.readUpdateAttributeLayerSettingsData(buffer, helper));
                break;
            case UPDATE_ENVIRONMENT_ATTRIBUTES:
                packet.setData(this.readUpdateEnvironmentAttributesData(buffer, helper));
                break;
            case REMOVE_ENVIRONMENT_ATTRIBUTES:
                packet.setData(this.readRemoveEnvironmentAttributesData(buffer, helper));
                break;
            default:
                throw new IllegalStateException("Read unknown AttributeLayerSyncPacketData.Type");
        }
    }

    protected void writeUpdateAttributeLayersData(ByteBuf buffer, BedrockCodecHelper helper, UpdateAttributeLayersData data) {
        helper.writeArray(buffer, data.getAttributeLayers(), this::writeAttributeLayerData);
    }

    protected UpdateAttributeLayersData readUpdateAttributeLayersData(ByteBuf buffer, BedrockCodecHelper helper) {
        final UpdateAttributeLayersData data = new UpdateAttributeLayersData();
        helper.readArray(buffer, data.getAttributeLayers(), this::readAttributeLayerData);
        return data;
    }

    protected void writeUpdateAttributeLayerSettingsData(ByteBuf buffer, BedrockCodecHelper helper, UpdateAttributeLayerSettingsData data) {
        helper.writeString(buffer, data.getAttributeLayerName());
        VarInts.writeInt(buffer, data.getAttributeLayerDimension().ordinal());
        this.writeAttributeLayerSettings(buffer, helper, data.getAttributesLayerSettings());
    }

    protected UpdateAttributeLayerSettingsData readUpdateAttributeLayerSettingsData(ByteBuf buffer, BedrockCodecHelper helper) {
        final UpdateAttributeLayerSettingsData data = new UpdateAttributeLayerSettingsData();
        data.setAttributeLayerName(helper.readString(buffer));
        data.setAttributeLayerDimension(Dimension.from(VarInts.readInt(buffer)));
        data.setAttributesLayerSettings(this.readAttributeLayerSettings(buffer, helper));
        return data;
    }

    protected void writeUpdateEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper, UpdateEnvironmentAttributesData data) {
        helper.writeString(buffer, data.getAttributeLayerName());
        VarInts.writeInt(buffer, data.getAttributeLayerDimension().ordinal());
        helper.writeArray(buffer, data.getAttributes(), this::writeEnvironmentAttributeData);
    }

    protected UpdateEnvironmentAttributesData readUpdateEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper) {
        final UpdateEnvironmentAttributesData data = new UpdateEnvironmentAttributesData();
        data.setAttributeLayerName(helper.readString(buffer));
        data.setAttributeLayerDimension(Dimension.from(VarInts.readInt(buffer)));
        helper.readArray(buffer, data.getAttributes(), this::readEnvironmentAttributeData);
        return data;
    }

    protected void writeRemoveEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper, RemoveEnvironmentAttributesData data) {
        helper.writeString(buffer, data.getAttributeLayerName());
        VarInts.writeInt(buffer, data.getAttributeLayerDimension().ordinal());
        helper.writeArray(buffer, data.getAttributes(), helper::writeString);
    }

    protected RemoveEnvironmentAttributesData readRemoveEnvironmentAttributesData(ByteBuf buffer, BedrockCodecHelper helper) {
        final RemoveEnvironmentAttributesData data = new RemoveEnvironmentAttributesData();
        data.setAttributeLayerName(helper.readString(buffer));
        data.setAttributeLayerDimension(Dimension.from(VarInts.readInt(buffer)));
        helper.readArray(buffer, data.getAttributes(), helper::readString);
        return data;
    }

    protected void writeAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper, AttributeLayerData data) {
        helper.writeString(buffer, data.getName());
        VarInts.writeInt(buffer, data.getDimension().ordinal());
        this.writeAttributeLayerSettings(buffer, helper, data.getSettings());
        helper.writeArray(buffer, data.getAttributes(), this::writeEnvironmentAttributeData);
    }

    protected AttributeLayerData readAttributeLayerData(ByteBuf buffer, BedrockCodecHelper helper) {
        final AttributeLayerData data = new AttributeLayerData();
        data.setName(helper.readString(buffer));
        data.setDimension(Dimension.from(VarInts.readInt(buffer)));
        data.setSettings(this.readAttributeLayerSettings(buffer, helper));
        helper.readArray(buffer, data.getAttributes(), this::readEnvironmentAttributeData);
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

    protected void writeEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper, EnvironmentAttributeData data) {
        helper.writeString(buffer, data.getAttributeName());
        helper.writeOptionalNull(buffer, data.getFromAttribute(), this::writeAttributeData);
        this.writeAttributeData(buffer, helper, data.getAttribute());
        helper.writeOptionalNull(buffer, data.getToAttribute(), this::writeAttributeData);
        buffer.writeIntLE(data.getCurrentTransitionTicks());
        buffer.writeIntLE(data.getTotalTransitionTicks());
        helper.writeString(buffer, data.getEasing().getSerializeName());
    }

    protected EnvironmentAttributeData readEnvironmentAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final EnvironmentAttributeData data = new EnvironmentAttributeData();
        data.setAttributeName(helper.readString(buffer));
        data.setFromAttribute(helper.readOptional(buffer, null, this::readAttributeData));
        data.setAttribute(this.readAttributeData(buffer, helper));
        data.setToAttribute(helper.readOptional(buffer, null, this::readAttributeData));
        data.setCurrentTransitionTicks(buffer.readIntLE());
        data.setTotalTransitionTicks(buffer.readIntLE());
        data.setEasing(EasingType.fromName(helper.readString(buffer)));
        return data;
    }

    protected void writeAttributeData(ByteBuf buffer, BedrockCodecHelper helper, AttributeData data) {
        VarInts.writeUnsignedInt(buffer, data.getType().ordinal());
        switch (data.getType()) {
            case BOOL:
                this.writeBoolAttributeData(buffer, helper, (BoolAttributeData) data);
                break;
            case FLOAT:
                this.writeFloatAttributeData(buffer, helper, (FloatAttributeData) data);
                break;
            case COLOR:
                this.writeColorAttributeData(buffer, helper, (ColorAttributeData) data);
                break;
        }
    }

    protected AttributeData readAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final AttributeDataType type = AttributeDataType.from(VarInts.readUnsignedInt(buffer));
        switch (type) {
            case BOOL:
                return this.readBoolAttributeData(buffer, helper);
            case FLOAT:
                return this.readFloatAttributeData(buffer, helper);
            case COLOR:
                return this.readColorAttributeData(buffer, helper);
            default:
                throw new IllegalStateException("Read unknown AttributeDataType");
        }
    }

    protected void writeBoolAttributeData(ByteBuf buffer, BedrockCodecHelper helper, BoolAttributeData data) {
        buffer.writeBoolean(data.isValue());
        helper.writeOptionalNull(buffer, data.getOperation().name(), helper::writeString);
    }

    protected BoolAttributeData readBoolAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BoolAttributeData data = new BoolAttributeData();
        data.setValue(buffer.readBoolean());
        data.setOperation(helper.readOptional(buffer, null,
                (buf, h) -> BoolAttributeOperation.valueOf(h.readString(buffer))));
        return data;
    }

    protected void writeFloatAttributeData(ByteBuf buffer, BedrockCodecHelper helper, FloatAttributeData data) {
        buffer.writeFloatLE(data.getValue());
        helper.writeOptionalNull(buffer, data.getOperation().name(), helper::writeString);
        helper.writeOptionalNull(buffer, data.getConstraintMin(), ByteBuf::writeFloatLE);
        helper.writeOptionalNull(buffer, data.getConstraintMax(), ByteBuf::writeFloatLE);
    }

    protected FloatAttributeData readFloatAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final FloatAttributeData data = new FloatAttributeData();
        data.setValue(buffer.readFloatLE());
        data.setOperation(helper.readOptional(buffer, null,
                (buf, h) -> FloatAttributeOperation.valueOf(h.readString(buffer))));
        data.setConstraintMin(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        data.setConstraintMax(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
        return data;
    }

    protected void writeColorAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ColorAttributeData data) {
        buffer.writeIntLE(data.getColor());
        helper.writeOptionalNull(buffer, data.getOperation().name(), helper::writeString);
    }

    protected ColorAttributeData readColorAttributeData(ByteBuf buffer, BedrockCodecHelper helper) {
        final ColorAttributeData data = new ColorAttributeData();
        data.setColor(buffer.readIntLE());
        data.setOperation(helper.readOptional(buffer, null,
                (buf, h) -> ColorAttributeOperation.valueOf(h.readString(buffer))));
        return data;
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