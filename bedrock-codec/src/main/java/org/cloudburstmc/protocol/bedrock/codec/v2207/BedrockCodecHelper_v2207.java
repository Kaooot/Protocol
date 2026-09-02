package org.cloudburstmc.protocol.bedrock.codec.v2207;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v2192.BedrockCodecHelper_v2192;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.ArmSizeType;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.PieceType;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.TrustedSkinFlag;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v2207 extends BedrockCodecHelper_v2192 {

    public BedrockCodecHelper_v2207(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
        this.personaPieceTypeMap = this.personaPieceTypeMap.toBuilder()
                .shift(28, 1)
                .insert(28, PieceType.CO_CO)
                .build();
    }

    @Override
    public void writeSerializedSkin(ByteBuf buffer, SerializedSkin serializedSkin) {
        this.writeString(buffer, serializedSkin.getID());
        this.writeString(buffer, serializedSkin.getResourcePatch());
        this.writeSkinImage(buffer, serializedSkin.getImageData());
        this.writeArray(buffer, serializedSkin.getAnimatedImageData(), this::writeAnimatedImageData);
        this.writeSkinImage(buffer, serializedSkin.getCapeImageData());
        this.writeString(buffer, serializedSkin.getGeometryData());
        this.writeString(buffer, serializedSkin.getGeometryDataMinEngineVersion());
        this.writeString(buffer, serializedSkin.getAnimationData());
        this.writeString(buffer, serializedSkin.getCapeID());
        this.writeString(buffer, serializedSkin.getFullID());
        buffer.writeByte(serializedSkin.getArmSize().ordinal());
        buffer.writeIntLE(serializedSkin.getSkinColor());
        this.writeArray(buffer, serializedSkin.getPersonaPieces(), this::writeSerializedPersonaPieceHandle);
        this.writePieceTintColors(buffer, serializedSkin.getPieceTintColors());
        buffer.writeBoolean(serializedSkin.isPremium());
        buffer.writeBoolean(serializedSkin.isPersona());
        buffer.writeBoolean(serializedSkin.isPersonaCapeOnClassicSkin());
        buffer.writeBoolean(serializedSkin.isPrimaryUser());
        buffer.writeBoolean(serializedSkin.isOverridesPlayerAppearance());
        this.writeString(buffer, serializedSkin.getTrustedSkinFlag().getId());
        this.writeString(buffer, serializedSkin.getProfileHash());
    }

    @Override
    public SerializedSkin readSerializedSkin(ByteBuf buffer) {
        final SerializedSkin serializedSkin = new SerializedSkin();
        serializedSkin.setID(this.readString(buffer));
        serializedSkin.setResourcePatch(this.readString(buffer));
        serializedSkin.setImageData(this.readSkinImage(buffer));
        this.readArray(buffer, serializedSkin.getAnimatedImageData(), this::readAnimatedImageData);
        serializedSkin.setCapeImageData(this.readSkinImage(buffer));
        serializedSkin.setGeometryData(this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize()));
        serializedSkin.setGeometryDataMinEngineVersion(this.readString(buffer));
        serializedSkin.setAnimationData(this.readString(buffer));
        serializedSkin.setCapeID(this.readString(buffer));
        serializedSkin.setFullID(this.readString(buffer));
        serializedSkin.setArmSize(ArmSizeType.from(buffer.readUnsignedByte()));
        serializedSkin.setSkinColor(buffer.readIntLE());
        this.readArray(buffer, serializedSkin.getPersonaPieces(), this::readSerializedPersonaPieceHandle);
        this.readPieceTintColors(buffer, serializedSkin.getPieceTintColors());
        serializedSkin.setPremium(buffer.readBoolean());
        serializedSkin.setPersona(buffer.readBoolean());
        serializedSkin.setPersonaCapeOnClassicSkin(buffer.readBoolean());
        serializedSkin.setPrimaryUser(buffer.readBoolean());
        serializedSkin.setOverridesPlayerAppearance(buffer.readBoolean());
        serializedSkin.setTrustedSkinFlag(TrustedSkinFlag.from(this.readString(buffer)));
        serializedSkin.setProfileHash(this.readString(buffer));
        return serializedSkin;
    }
}