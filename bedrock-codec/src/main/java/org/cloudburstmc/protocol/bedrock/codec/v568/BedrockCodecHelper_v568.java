package org.cloudburstmc.protocol.bedrock.codec.v568;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v557.BedrockCodecHelper_v557;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.common.util.TypeMap;

import java.util.List;
import java.util.Map;

public class BedrockCodecHelper_v568 extends BedrockCodecHelper_v557 {

    public BedrockCodecHelper_v568(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

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
        boolean overridingPlayerAppearance = buffer.readBoolean();

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
                .overridesPlayerAppearance(overridingPlayerAppearance)
                .build();
    }

    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        super.writeSkin(buffer, skin);
        buffer.writeBoolean(skin.isOverridesPlayerAppearance());
    }
}
