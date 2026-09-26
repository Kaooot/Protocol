package org.cloudburstmc.protocol.bedrock.codec.v465;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v448.BedrockCodecHelper_v448;
import org.cloudburstmc.protocol.bedrock.data.education.EduSharedUriResource;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v465 extends BedrockCodecHelper_v448 {

    public BedrockCodecHelper_v465(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SerializedSkin readSkin(ByteBuf buffer) {
        String skinId = this.readString(buffer);
        String playFabId = this.readString(buffer);
        String skinResourcePatch = this.readString(buffer);
        SkinImage skinData = this.readImage(buffer, SkinImage.SKIN_PERSONA_SIZE);

        List<AnimatedImageData> animations = new ObjectArrayList<>();
        this.readArray(buffer, animations, ByteBuf::readIntLE, (b, h) -> this.readAnimationData(b));

        SkinImage capeData = this.readImage(buffer, SkinImage.SINGLE_SKIN_SIZE);
        String geometryData = this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize());
        String geometryDataEngineVersion = this.readString(buffer);
        String animationData = this.readString(buffer);
        String capeId = this.readString(buffer);
        String fullSkinId = this.readString(buffer);
        ArmSizeType armSize = ArmSizeType.from(buffer.readUnsignedByte());
        int skinColor = buffer.readIntLE();

        List<SerializedPersonaPieceHandle> personaPieces = new ObjectArrayList<>();
        this.readArray(buffer, personaPieces, ByteBuf::readIntLE, (buf, h) -> this.readPersonaPiece(buf));

        Map<PieceType, TintMapColor> tintColors = this.readPieceTintColors(buffer);

        boolean premium = buffer.readBoolean();
        boolean persona = buffer.readBoolean();
        boolean capeOnClassic = buffer.readBoolean();
        boolean primaryUser = buffer.readBoolean();

        return SerializedSkin.builder()
                .ID(skinId)
                .playFabID(playFabId)
                .resourcePatch(skinResourcePatch)
                .imageData(skinData)
                .animatedImageData(animations)
                .capeImageData(capeData)
                .geometryData(geometryData)
                .geometryDataMinEngineVersion(geometryDataEngineVersion)
                .animationData(animationData)
                .capeID(capeId)
                .fullID(fullSkinId)
                .armSize(armSize)
                .skinColor(skinColor)
                .personaPieces(personaPieces)
                .pieceTintColors(tintColors)
                .isPremium(premium)
                .isPersona(persona)
                .isPersonaCapeOnClassicSkin(capeOnClassic)
                .isPrimaryUser(primaryUser)
                .build();
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        requireNonNull(skin, "Skin is null");

        this.writeString(buffer, skin.getID());
        this.writeString(buffer, skin.getPlayFabID());
        this.writeString(buffer, skin.getResourcePatch());
        this.writeImage(buffer, skin.getImageData());

        List<AnimatedImageData> animations = skin.getAnimatedImageData();
        buffer.writeIntLE(animations.size());
        for (AnimatedImageData animation : animations) {
            this.writeAnimationData(buffer, animation);
        }

        this.writeImage(buffer, skin.getCapeImageData());
        this.writeString(buffer, skin.getGeometryData());
        this.writeString(buffer, skin.getGeometryDataMinEngineVersion());
        this.writeString(buffer, skin.getAnimationData());
        this.writeString(buffer, skin.getCapeID());
        this.writeString(buffer, skin.getFullID());
        buffer.writeByte(skin.getArmSize().ordinal());
        buffer.writeIntLE(skin.getSkinColor());

        List<SerializedPersonaPieceHandle> pieces = skin.getPersonaPieces();
        buffer.writeIntLE(pieces.size());
        for (SerializedPersonaPieceHandle piece : pieces) {
            this.writePersonaPiece(buffer, piece);
        }

        this.writePieceTintColors(buffer, skin.getPieceTintColors());

        buffer.writeBoolean(skin.isPremium());
        buffer.writeBoolean(skin.isPersona());
        buffer.writeBoolean(skin.isPersonaCapeOnClassicSkin());
        buffer.writeBoolean(skin.isPrimaryUser());
    }

    @Override
    public void writeEduSharedUriResource(ByteBuf buffer, EduSharedUriResource eduSharedUriResource) {
        this.writeString(buffer, eduSharedUriResource.getButtonName());
        this.writeString(buffer, eduSharedUriResource.getLinkUri());
    }

    @Override
    public EduSharedUriResource readEduSharedUriResource(ByteBuf buffer) {
        final EduSharedUriResource eduSharedUriResource = new EduSharedUriResource();
        eduSharedUriResource.setButtonName(this.readString(buffer));
        eduSharedUriResource.setLinkUri(this.readString(buffer));
        return eduSharedUriResource;
    }
}