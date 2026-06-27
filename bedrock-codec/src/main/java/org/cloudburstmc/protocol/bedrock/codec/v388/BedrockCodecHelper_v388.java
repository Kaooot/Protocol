package org.cloudburstmc.protocol.bedrock.codec.v388;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v361.BedrockCodecHelper_v361;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Mirror;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.Rotation;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureSettings;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.data.skin.AnimationData;
import org.cloudburstmc.protocol.bedrock.data.skin.ImageData;
import org.cloudburstmc.protocol.bedrock.data.skin.Skin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v388 extends BedrockCodecHelper_v361 {

    protected static final AnimatedTextureType[] TEXTURE_TYPES = AnimatedTextureType.values();

    public BedrockCodecHelper_v388(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
        super(entityData, gameRulesTypes);
    }

    @Override
    public Skin readSkin(ByteBuf buffer) {
        String skinId = this.readString(buffer);
        String skinResourcePatch = this.readString(buffer);
        ImageData skinData = this.readImage(buffer);

        List<AnimationData> animations = new ObjectArrayList<>();
        this.readArray(buffer, animations, ByteBuf::readIntLE, (b, h) -> this.readAnimationData(b));

        ImageData capeData = this.readImage(buffer);
        String geometryData = this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize());
        String animationData = this.readString(buffer);
        boolean premium = buffer.readBoolean();
        boolean persona = buffer.readBoolean();
        boolean capeOnClassic = buffer.readBoolean();
        String capeId = this.readString(buffer);
        String fullSkinId = this.readString(buffer);

        return Skin.of(skinId, "", skinResourcePatch, skinData, animations, capeData, geometryData, animationData,
                premium, persona, capeOnClassic, capeId, fullSkinId);
    }

    @Override
    public void writeSkin(ByteBuf buffer, Skin skin) {
        requireNonNull(skin, "Skin is null");

        this.writeString(buffer, skin.getSkinId());
        this.writeString(buffer, skin.getSkinResourcePatch());
        this.writeImage(buffer, skin.getSkinData());

        List<AnimationData> animations = skin.getAnimations();
        buffer.writeIntLE(animations.size());
        for (AnimationData animation : animations) {
            this.writeAnimationData(buffer, animation);
        }

        this.writeImage(buffer, skin.getCapeData());
        this.writeString(buffer, skin.getGeometryData());
        this.writeString(buffer, skin.getAnimationData());
        buffer.writeBoolean(skin.isPremium());
        buffer.writeBoolean(skin.isPersona());
        buffer.writeBoolean(skin.isCapeOnClassic());
        this.writeString(buffer, skin.getCapeId());
        this.writeString(buffer, skin.getFullSkinId());
    }

    @Override
    public AnimationData readAnimationData(ByteBuf buffer) {
        ImageData image = this.readImage(buffer);
        AnimatedTextureType type = TEXTURE_TYPES[buffer.readIntLE()];
        float frames = buffer.readFloatLE();
        return new AnimationData(image, type, frames);
    }

    @Override
    public void writeAnimationData(ByteBuf buffer, AnimationData animation) {
        this.writeImage(buffer, animation.getImage());
        buffer.writeIntLE(animation.getTextureType().ordinal());
        buffer.writeFloatLE(animation.getFrames());
    }

    @Override
    public ImageData readImage(ByteBuf buffer) {
        int width = buffer.readIntLE();
        int height = buffer.readIntLE();
        byte[] image = readByteArray(buffer);
        return ImageData.of(width, height, image);
    }

    @Override
    public void writeImage(ByteBuf buffer, ImageData image) {
        requireNonNull(image, "image is null");

        buffer.writeIntLE(image.getWidth());
        buffer.writeIntLE(image.getHeight());
        writeByteArray(buffer, image.getImage());
    }

    @Override
    public StructureSettings readStructureSettings(ByteBuf buffer) {
        final StructureSettings structureSettings = new StructureSettings();
        structureSettings.setStructurePaletteName(this.readString(buffer));
        structureSettings.setShouldIgnoreEntities(buffer.readBoolean());
        structureSettings.setShouldIgnoreBlocks(buffer.readBoolean());
        structureSettings.setStructureSize(this.readBlockPosition(buffer));
        structureSettings.setStructureOffset(this.readBlockPosition(buffer));
        structureSettings.setLastEditPlayer(VarInts.readLong(buffer));
        structureSettings.setRotation(Rotation.from(buffer.readUnsignedByte()));
        structureSettings.setMirror(Mirror.from(buffer.readUnsignedByte()));
        structureSettings.setIntegrityValue(buffer.readFloatLE());
        structureSettings.setIntegritySeed(buffer.readIntLE());
        structureSettings.setRotationPivot(this.readVector3f(buffer));
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
        this.writeVector3f(buffer, settings.getRotationPivot());
    }
}
