package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.attribute.*;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundAttributeLayerSyncPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundAttributeLayerSyncSerializer_v944 implements BedrockPacketSerializer<ClientboundAttributeLayerSyncPacket> {
    public static final ClientboundAttributeLayerSyncSerializer_v944 INSTANCE = new ClientboundAttributeLayerSyncSerializer_v944();

    protected static final int NAME_LENGTH = 128;

    protected final VariantCodec<ClientboundAttributeLayerSyncPacket> dataVariant = VariantCodec.<ClientboundAttributeLayerSyncDataType, ClientboundAttributeLayerSyncPacket>builder(ClientboundAttributeLayerSyncDataType::ordinal)
            .add(
                    ClientboundAttributeLayerSyncDataType.UPDATE_ATTRIBUTE_LAYERS,
                    UpdateAttributeLayersData.class,
                    this::writeUpdateAttributeLayersData,
                    this::readUpdateAttributeLayersData
            )
            .add(
                    ClientboundAttributeLayerSyncDataType.UPDATE_ATTRIBUTE_LAYER_SETTINGS,
                    UpdateAttributeLayerSettingsData.class,
                    this::writeUpdateAttributeLayerSettingsData,
                    this::readUpdateAttributeLayerSettingsData
            )
            .add(
                    ClientboundAttributeLayerSyncDataType.UPDATE_ENVIRONMENT_ATTRIBUTES,
                    UpdateEnvironmentAttributesData.class,
                    this::writeUpdateEnvironmentAttributesData,
                    this::readUpdateEnvironmentAttributesData
            )
            .add(
                    ClientboundAttributeLayerSyncDataType.REMOVE_ENVIRONMENT_ATTRIBUTES,
                    RemoveEnvironmentAttributesData.class,
                    this::writeRemoveEnvironmentAttributesData,
                    this::readRemoveEnvironmentAttributesData
            )
            .build();

    protected final VariantCodec<ClientboundAttributeLayerSyncPacket> attributeDataVariant = VariantCodec.<AttributeDataType, ClientboundAttributeLayerSyncPacket>builder(AttributeDataType::ordinal)
            .add(
                    AttributeDataType.BOOL,
                    BoolAttributeData.class,
                    this::writeBoolAttributeData,
                    this::readBoolAttributeData
            )
            .add(
                    AttributeDataType.FLOAT,
                    FloatAttributeData.class,
                    this::writeFloatAttributeData,
                    this::readFloatAttributeData
            )
            .add(
                    AttributeDataType.COLOR,
                    ColorAttributeData.class,
                    this::writeColorAttributeData,
                    this::readColorAttributeData
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        this.dataVariant.write(buffer, helper, packet, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet) {
        packet.setData(this.dataVariant.read(buffer, helper, packet));
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
                (buf, codecHelper, attribute) -> this.attributeDataVariant.write(buf, codecHelper, packet, attribute)
        );
        this.attributeDataVariant.write(buffer, helper, packet, data.getAttribute());
        helper.writeOptionalNull(
                buffer,
                data.getToAttribute(),
                (buf, codecHelper, attribute) -> this.attributeDataVariant.write(buf, codecHelper, packet, attribute)
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
                        (buf, codecHelper) -> this.attributeDataVariant.read(buf, codecHelper, packet)
                )
        );
        data.setAttribute(this.attributeDataVariant.read(buffer, helper, packet));
        data.setToAttribute(
                helper.readOptional(
                        buffer,
                        null,
                        (buf, codecHelper) -> this.attributeDataVariant.read(buf, codecHelper, packet)
                )
        );
        data.setCurrentTransitionTicks(buffer.readIntLE());
        data.setTotalTransitionTicks(buffer.readIntLE());
        data.setEasing(EasingFunction.fromName(helper.readString(buffer)));
        return data;
    }

    protected void writeBoolAttributeData(ByteBuf buffer, BedrockCodecHelper helper, ClientboundAttributeLayerSyncPacket packet, BoolAttributeData data) {
        buffer.writeBoolean(data.isValue());
        helper.writeOptionalNull(buffer, data.getOperation().name(), helper::writeString);
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
        helper.writeOptionalNull(buffer, data.getOperation().name(), helper::writeString);
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
        helper.writeOptionalNull(buffer, data.getOperation().name(), helper::writeString);
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