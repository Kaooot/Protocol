package org.cloudburstmc.protocol.bedrock.codec.v2225;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v2193.BedrockCodecHelper_v2193;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.actor.passenger.PassengerOfBlockArguments;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestCraftDiscPressAction;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.bedrock.data.sound.audio.SignedAudioContent;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v2225 extends BedrockCodecHelper_v2193 {

    private static final TypeMap<ItemStackRequestActionType> ACTION_TYPE_VALUES = TypeMap.builder(ItemStackRequestActionType.class)
            .insert(0, ItemStackRequestActionType.TAKE)
            .insert(1, ItemStackRequestActionType.PLACE)
            .insert(2, ItemStackRequestActionType.SWAP)
            .insert(3, ItemStackRequestActionType.DROP)
            .insert(4, ItemStackRequestActionType.DESTROY)
            .insert(5, ItemStackRequestActionType.CONSUME)
            .insert(6, ItemStackRequestActionType.CREATE)
            .insert(7, ItemStackRequestActionType.PLACE_IN_ITEM_CONTAINER)
            .insert(8, ItemStackRequestActionType.TAKE_FROM_ITEM_CONTAINER)
            .insert(9, ItemStackRequestActionType.SCREEN_LAB_TABLE_COMBINE)
            .insert(10, ItemStackRequestActionType.SCREEN_BEACON_PAYMENT)
            .insert(11, ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK)
            .insert(12, ItemStackRequestActionType.CRAFT_RECIPE)
            .insert(13, ItemStackRequestActionType.CRAFT_RECIPE_AUTO)
            .insert(14, ItemStackRequestActionType.CRAFT_CREATIVE)
            .insert(15, ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL)
            .insert(16, ItemStackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT)
            .insert(17, ItemStackRequestActionType.CRAFT_LOOM)
            .insert(18, ItemStackRequestActionType.CRAFT_DISC_PRESS)
            .insert(19, ItemStackRequestActionType.CRAFT_NON_IMPLEMENTED)
            .insert(20, ItemStackRequestActionType.CRAFT_RESULTS)
            .build();

    public BedrockCodecHelper_v2225(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
        this.personaPieceTypeMap = this.personaPieceTypeMap.toBuilder()
                .shift(28, 1)
                .insert(28, PieceType.CO_CO)
                .build();
    }

    @Override
    protected ItemStackRequestActionType itemStackRequestActionType(Object action) {
        if (action instanceof ItemStackRequestCraftDiscPressAction) {
            return ItemStackRequestActionType.CRAFT_DISC_PRESS;
        }
        return super.itemStackRequestActionType(action);
    }

    @Override
    protected void writeItemStackRequestActionIndex(ByteBuf buffer, ItemStackRequestActionType type) {
        VarInts.writeUnsignedInt(buffer, this.stackRequestActionTypes.getId(type));
        buffer.writeByte(ACTION_TYPE_VALUES.getId(type));
    }

    @Override
    protected ItemStackRequestActionType readItemStackRequestActionIndex(ByteBuf buffer) {
        final ItemStackRequestActionType type = this.stackRequestActionTypes.getType(VarInts.readUnsignedInt(buffer));
        buffer.readUnsignedByte();
        return type;
    }

    @Override
    protected void writeItemStackRequestActionBody(ByteBuf buffer, ItemStackRequestActionType type, Object action) {
        if (type == ItemStackRequestActionType.CRAFT_DISC_PRESS) {
            return;
        }
        super.writeItemStackRequestActionBody(buffer, type, action);
    }

    @Override
    protected Object readItemStackRequestActionBody(ByteBuf buffer, ItemStackRequestActionType type) {
        if (type == ItemStackRequestActionType.CRAFT_DISC_PRESS) {
            return new ItemStackRequestCraftDiscPressAction();
        }
        return super.readItemStackRequestActionBody(buffer, type);
    }

    @Override
    public void writeSignedAudioContent(ByteBuf buffer, SignedAudioContent content) {
        this.writeString(buffer, content.getCompactJwt());
    }

    @Override
    public SignedAudioContent readSignedAudioContent(ByteBuf buffer) {
        final SignedAudioContent content = new SignedAudioContent();
        content.setCompactJwt(this.readStringMaxLen(buffer, 32768));
        return content;
    }

    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        requireNonNull(skin, "Skin is null");

        this.writeString(buffer, skin.getID());
        this.writeString(buffer, skin.getResourcePatch());
        this.writeImage(buffer, skin.getImageData());

        List<AnimatedImageData> animations = skin.getAnimatedImageData();
        VarInts.writeUnsignedInt(buffer, animations.size());
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
        VarInts.writeUnsignedInt(buffer, pieces.size());
        for (SerializedPersonaPieceHandle piece : pieces) {
            this.writePersonaPiece(buffer, piece);
        }

        Map<PieceType, TintMapColor> tints = skin.getPieceTintColors();
        VarInts.writeUnsignedInt(buffer, tints.size());
        for (Map.Entry<PieceType, TintMapColor> entry : tints.entrySet()) {
            this.writeString(buffer, entry.getKey().getId());
            List<Integer> colors = entry.getValue().getColors();
            if (colors.size() != 4) {
                throw new IllegalArgumentException("Expected 4 colors in TintMapColor");
            }
            for (int color : colors) {
                buffer.writeIntLE(color);
            }
        }

        buffer.writeBoolean(skin.isPremium());
        buffer.writeBoolean(skin.isPersona());
        buffer.writeBoolean(skin.isPersonaCapeOnClassicSkin());
        buffer.writeBoolean(skin.isPrimaryUser());

        buffer.writeBoolean(skin.isOverridesPlayerAppearance());

        this.writeString(buffer, skin.getTrustedSkinFlag().getId());
        this.writeString(buffer, skin.getProfileHash());
    }

    @Override
    public SerializedSkin readSkin(ByteBuf buffer) {
        String skinId = this.readString(buffer);
        String skinResourcePatch = this.readString(buffer);
        SkinImage skinData = this.readImage(buffer, SkinImage.SKIN_PERSONA_SIZE);

        List<AnimatedImageData> animations = new ObjectArrayList<>();
        this.readArray(buffer, animations, (b, h) -> this.readAnimationData(b));

        SkinImage capeData = this.readImage(buffer, SkinImage.SINGLE_SKIN_SIZE);
        String geometryData = this.readStringMaxLen(buffer, this.encodingSettings.maxGeometryDataSize());
        String geometryDataEngineVersion = this.readString(buffer);
        String animationData = this.readString(buffer);
        String capeId = this.readString(buffer);
        String fullSkinId = this.readString(buffer);

        ArmSizeType armSize = ArmSizeType.from(buffer.readUnsignedByte());
        int skinColor = buffer.readIntLE();

        List<SerializedPersonaPieceHandle> personaPieces = new ObjectArrayList<>();
        this.readArray(buffer, personaPieces, (buf, h) -> this.readPersonaPiece(buf));

        Map<PieceType, TintMapColor> tintColors = new HashMap<>();
        int tintLength = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < tintLength; i++) {
            PieceType pieceType = PieceType.from(this.readString(buffer));
            TintMapColor tintMapColor = new TintMapColor();
            for (int j = 0; j < 4; j++) {
                tintMapColor.getColors().add(buffer.readIntLE());
            }
            tintColors.put(pieceType, tintMapColor);
        }

        boolean premium = buffer.readBoolean();
        boolean persona = buffer.readBoolean();
        boolean capeOnClassic = buffer.readBoolean();
        boolean primaryUser = buffer.readBoolean();
        boolean overridingPlayerAppearance = buffer.readBoolean();

        TrustedSkinFlag trustedSkinFlag = TrustedSkinFlag.from(this.readString(buffer));
        String profileHash = this.readString(buffer);

        return SerializedSkin.builder()
                .ID(skinId)
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
                .trustedSkinFlag(trustedSkinFlag)
                .profileHash(profileHash)
                .build();
    }

    @Override
    public void writePassengerOfBlockArguments(ByteBuf buffer, PassengerOfBlockArguments arguments) {
        this.writeBlockPosition(buffer, arguments.getBlockPos());
        this.writeVector3f(buffer, arguments.getOffset());
        buffer.writeFloatLE(arguments.getRotation());
        buffer.writeFloatLE(arguments.getRotationLimit());
        buffer.writeByte(arguments.getEmoteType().ordinal());
    }

    @Override
    public PassengerOfBlockArguments readPassengerOfBlockArguments(ByteBuf buffer) {
        final PassengerOfBlockArguments arguments = new PassengerOfBlockArguments();
        arguments.setBlockPos(this.readBlockPosition(buffer));
        arguments.setOffset(this.readVector3f(buffer));
        arguments.setRotation(buffer.readFloatLE());
        arguments.setRotationLimit(buffer.readFloatLE());
        arguments.setEmoteType(PassengerOfBlockArguments.EmoteType.from(buffer.readUnsignedByte()));
        return arguments;
    }
}