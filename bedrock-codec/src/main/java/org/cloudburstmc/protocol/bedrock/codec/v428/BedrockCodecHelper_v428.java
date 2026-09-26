package org.cloudburstmc.protocol.bedrock.codec.v428;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v422.BedrockCodecHelper_v422;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestMineBlockAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.skin.*;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v428 extends BedrockCodecHelper_v422 {

    public BedrockCodecHelper_v428(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames);
        this.itemStackRequestActionsVariant = this.itemStackRequestActionsVariant.toBuilder(this.stackRequestActionTypes::getId)
                .add(
                        ItemStackRequestActionType.SCREEN_HUD_MINE_BLOCK,
                        ItemStackRequestMineBlockAction.class,
                        (buffer, helper, type, value) -> this.writeItemStackRequestMineBlockAction(buffer, type, value),
                        (buffer, helper, type) -> this.readItemStackRequestMineBlockAction(buffer, type)
                )
                .build();
    }

    protected void writeItemStackRequestMineBlockAction(ByteBuf buffer, ItemStackRequestActionType type, ItemStackRequestMineBlockAction action) {
        VarInts.writeInt(buffer, action.getSlot());
        VarInts.writeInt(buffer, action.getPredictedDurability());
        VarInts.writeInt(buffer, action.getNetIdVariant());
    }

    protected ItemStackRequestMineBlockAction readItemStackRequestMineBlockAction(ByteBuf buffer, ItemStackRequestActionType type) {
        final ItemStackRequestMineBlockAction action = new ItemStackRequestMineBlockAction();
        action.setSlot(VarInts.readInt(buffer));
        action.setPredictedDurability(VarInts.readInt(buffer));
        action.setNetIdVariant(VarInts.readInt(buffer));
        return action;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public SerializedSkin readSkin(ByteBuf buffer) {
        String skinId = this.readString(buffer);
        String playFabId = this.readString(buffer); // new for v428
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
                .playFabID(playFabId)
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void writeSkin(ByteBuf buffer, SerializedSkin skin) {
        requireNonNull(skin, "Skin is null");

        this.writeString(buffer, skin.getID());
        this.writeString(buffer, skin.getPlayFabID()); // new for v428
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

    @Override
    protected ItemStackResponseSlotInfo readItemStackResponseSlotInfo(ByteBuf buffer) {
        return new ItemStackResponseSlotInfo(
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                buffer.readUnsignedByte(),
                new ItemStackNetId(VarInts.readInt(buffer)),
                new RedactableString(this.readString(buffer), ""),
                VarInts.readInt(buffer)
        );
    }

    @Override
    protected void writeItemStackResponseSlotInfo(ByteBuf buffer, ItemStackResponseSlotInfo info) {
        super.writeItemStackResponseSlotInfo(buffer, info);
        VarInts.writeInt(buffer, info.getDurabilityCorrection());
    }
}
