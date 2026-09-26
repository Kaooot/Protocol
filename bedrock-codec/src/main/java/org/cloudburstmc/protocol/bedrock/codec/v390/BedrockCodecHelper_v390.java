package org.cloudburstmc.protocol.bedrock.codec.v390;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v388.BedrockCodecHelper_v388;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v390 extends BedrockCodecHelper_v388 {

    protected TypeMap<PieceType> personaPieceTypeMap = TypeMap.builder(PieceType.class)
            .insert(0, PieceType.UNKNOWN)
            .insert(1, PieceType.SKELETON)
            .insert(2, PieceType.BODY)
            .insert(3, PieceType.SKIN)
            .insert(4, PieceType.BOTTOM)
            .insert(5, PieceType.FEET)
            .insert(6, PieceType.DRESS)
            .insert(7, PieceType.TOP)
            .insert(8, PieceType.HIGH_PANTS)
            .insert(9, PieceType.HANDS)
            .insert(10, PieceType.OUTERWEAR)
            .insert(11, PieceType.FACIAL_HAIR)
            .insert(12, PieceType.MOUTH)
            .insert(13, PieceType.EYES)
            .insert(14, PieceType.HAIR)
            .insert(15, PieceType.HOOD)
            .insert(16, PieceType.BACK)
            .insert(17, PieceType.FACE_ACCESSORY)
            .insert(18, PieceType.HEAD)
            .insert(19, PieceType.LEGS)
            .insert(20, PieceType.LEFT_LEG)
            .insert(21, PieceType.RIGHT_LEG)
            .insert(22, PieceType.ARMS)
            .insert(23, PieceType.LEFT_ARM)
            .insert(24, PieceType.RIGHT_ARM)
            .insert(25, PieceType.CAPES)
            .insert(26, PieceType.CLASSIC_SKIN)
            .insert(27, PieceType.EMOTE)
            .insert(28, PieceType.UNSUPPORTED)
            .build();

    public BedrockCodecHelper_v390(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
        super(entityData, gameRulesTypes);
    }

    @Override
    public SerializedSkin readSkin(ByteBuf buffer) {
        String skinId = this.readString(buffer);
        String skinResourcePatch = this.readString(buffer);
        SkinImage skinData = this.readImage(buffer, SkinImage.SKIN_PERSONA_SIZE);

        List<AnimatedImageData> animations = new ObjectArrayList<>();
        this.readArray(buffer, animations, ByteBuf::readIntLE, (b, h) -> this.readAnimationData(b));

        SkinImage capeData = this.readImage(buffer, SkinImage.SINGLE_SKIN_SIZE);
        String geometryData = this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize());
        String animationData = this.readString(buffer);
        boolean premium = buffer.readBoolean();
        boolean persona = buffer.readBoolean();
        boolean capeOnClassic = buffer.readBoolean();
        String capeId = this.readString(buffer);
        String fullSkinId = this.readString(buffer);
        ArmSizeType armSize = ArmSizeType.from(buffer.readUnsignedByte());
        int skinColor = buffer.readIntLE();

        List<SerializedPersonaPieceHandle> personaPieces = new ObjectArrayList<>();
        this.readArray(buffer, personaPieces, ByteBuf::readIntLE, (buf, h) -> this.readPersonaPiece(buf));

        Map<PieceType, TintMapColor> tintColors = this.readPieceTintColors(buffer);

        return SerializedSkin.builder()
                .ID(skinId)
                .resourcePatch(skinResourcePatch)
                .imageData(skinData)
                .animatedImageData(animations)
                .capeImageData(capeData)
                .geometryData(geometryData)
                .animationData(animationData)
                .isPremium(premium)
                .isPersona(persona)
                .isPersonaCapeOnClassicSkin(capeOnClassic)
                .capeID(capeId)
                .fullID(fullSkinId)
                .armSize(armSize)
                .skinColor(skinColor)
                .personaPieces(personaPieces)
                .pieceTintColors(tintColors)
                .build();
    }

    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        requireNonNull(skin, "Skin is null");

        this.writeString(buffer, skin.getID());
        this.writeString(buffer, skin.getResourcePatch());
        this.writeImage(buffer, skin.getImageData());

        List<AnimatedImageData> animations = skin.getAnimatedImageData();
        buffer.writeIntLE(animations.size());
        for (AnimatedImageData animation : animations) {
            this.writeAnimationData(buffer, animation);
        }

        this.writeImage(buffer, skin.getCapeImageData());
        this.writeString(buffer, skin.getGeometryData());
        this.writeString(buffer, skin.getAnimationData());
        buffer.writeBoolean(skin.isPremium());
        buffer.writeBoolean(skin.isPersona());
        buffer.writeBoolean(skin.isPersonaCapeOnClassicSkin());
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
    }

    protected SerializedPersonaPieceHandle readPersonaPiece(ByteBuf buffer) {
        SerializedPersonaPieceHandle piece = new SerializedPersonaPieceHandle();
        piece.setPieceId(this.readString(buffer));
        piece.setPieceType(this.personaPieceTypeMap.getType(buffer.readIntLE()));
        piece.setPackId(this.readUuid(buffer));
        piece.setDefaultPiece(buffer.readBoolean());
        piece.setProductId(this.readString(buffer));
        return piece;
    }

    protected void writePersonaPiece(ByteBuf buffer, SerializedPersonaPieceHandle piece) {
        this.writeString(buffer, piece.getPieceId());
        buffer.writeIntLE(this.personaPieceTypeMap.getId(piece.getPieceType()));
        this.writeUuid(buffer, piece.getPackId());
        buffer.writeBoolean(piece.isDefaultPiece());
        this.writeString(buffer, piece.getProductId());
    }

    protected Map<PieceType, TintMapColor> readPieceTintColors(ByteBuf buffer) {
        Map<PieceType, TintMapColor> tintColors = new HashMap<>();
        int length = buffer.readIntLE();
        for (int i = 0; i < length; i++) {
            PieceType pieceType = PieceType.from(this.readString(buffer));
            TintMapColor tintMapColor = new TintMapColor();
            int colorsLength = buffer.readIntLE();
            for (int j = 0; j < colorsLength; j++) {
                tintMapColor.getColors().add(buffer.readIntLE());
            }
            tintColors.put(pieceType, tintMapColor);
        }
        return tintColors;
    }

    protected void writePieceTintColors(ByteBuf buffer, Map<PieceType, TintMapColor> tintColors) {
        buffer.writeIntLE(tintColors.size());
        for (Map.Entry<PieceType, TintMapColor> entry : tintColors.entrySet()) {
            this.writeString(buffer, entry.getKey().getId());
            List<Integer> colors = entry.getValue().getColors();
            buffer.writeIntLE(colors.size());
            for (int color : colors) {
                buffer.writeIntLE(color);
            }
        }
    }
}