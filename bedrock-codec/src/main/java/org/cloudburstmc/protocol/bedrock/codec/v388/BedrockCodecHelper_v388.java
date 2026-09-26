package org.cloudburstmc.protocol.bedrock.codec.v388;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v361.BedrockCodecHelper_v361;
import org.cloudburstmc.protocol.bedrock.data.skin.PersonaAnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimatedImageData;
import org.cloudburstmc.protocol.bedrock.data.skin.SkinImage;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.data.structure.AnimationMode;
import org.cloudburstmc.protocol.bedrock.data.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureSettings;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v388 extends BedrockCodecHelper_v361 {

    protected static final PersonaAnimatedTextureType[] TEXTURE_TYPES = PersonaAnimatedTextureType.values();

    public BedrockCodecHelper_v388(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
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
    }

    @Override
    public AnimatedImageData readAnimationData(ByteBuf buffer) {
        SkinImage image = this.readImage(buffer, SkinImage.ANIMATION_SIZE);
        PersonaAnimatedTextureType type = TEXTURE_TYPES[buffer.readIntLE()];
        float frames = buffer.readFloatLE();
        return new AnimatedImageData(image, type, frames);
    }

    @Override
    public void writeAnimationData(ByteBuf buffer, AnimatedImageData animation) {
        this.writeImage(buffer, animation.getSkinImage());
        buffer.writeIntLE(animation.getAnimatedTextureType().ordinal());
        buffer.writeFloatLE(animation.getFrames());
    }

    @Override
    public SkinImage readImage(ByteBuf buffer, int maxSize) {
        int width = buffer.readIntLE();
        int height = buffer.readIntLE();
        byte[] image = readByteArray(buffer, maxSize);
        return SkinImage.of(width, height, image);
    }

    @Override
    public void writeImage(ByteBuf buffer, SkinImage image) {
        requireNonNull(image, "image is null");

        buffer.writeIntLE(image.getWidth());
        buffer.writeIntLE(image.getHeight());
        writeByteArray(buffer, image.getImage());
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
        Vector3f pivot = this.readVector3f(buffer);

        return new StructureSettings(paletteName, ignoringEntities, ignoringBlocks, true, size, offset, lastEditedByEntityId,
                rotation, mirror, AnimationMode.NONE, 0f, integrityValue, integritySeed, pivot);
    }

    @Override
    public void writeStructureSettings(ByteBuf buffer, StructureSettings settings) {
        super.writeStructureSettings(buffer, settings);
        this.writeVector3f(buffer, settings.getRotationPivot());
    }
}
