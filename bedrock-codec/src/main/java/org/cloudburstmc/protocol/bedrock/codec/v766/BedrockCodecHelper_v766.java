package org.cloudburstmc.protocol.bedrock.codec.v766;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v729.BedrockCodecHelper_v729;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackNetId;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.math.BigInteger;
import java.util.Set;

public class BedrockCodecHelper_v766 extends BedrockCodecHelper_v729 {

    public BedrockCodecHelper_v766(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public <T extends Enum<?>> void readLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz) {
        BigInteger flagsInt = VarInts.readUnsignedBigVarInt(buffer, clazz.getEnumConstants().length);
        for (T flag : clazz.getEnumConstants()) {
            if (flagsInt.testBit(flag.ordinal())) {
                flags.add(flag);
            }
        }
    }

    @Override
    public <T extends Enum<?>> void writeLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz) {
        BigInteger flagsInt = BigInteger.ZERO;
        for (T flag : flags) {
            flagsInt = flagsInt.setBit(flag.ordinal());
        }
        VarInts.writeUnsignedBigVarInt(buffer, flagsInt);
    }

    @Override
    public void writeRedactableString(ByteBuf buffer, RedactableString string) {
        this.writeString(buffer, string.getUnredacted());
        this.writeString(buffer, string.getRedacted());
    }

    @Override
    public RedactableString readRedactableString(ByteBuf buffer) {
        final RedactableString string = new RedactableString();
        string.setUnredacted(this.readString(buffer));
        string.setRedacted(this.readString(buffer));
        return string;
    }

    @Override
    protected ItemStackResponseSlotInfo readItemEntry(ByteBuf buffer) {
        int slot = buffer.readUnsignedByte();
        int requestedSlot = buffer.readUnsignedByte();
        int count = buffer.readUnsignedByte();
        ItemStackNetId stackNetworkId = new ItemStackNetId(VarInts.readInt(buffer));
        final RedactableString customName = this.readRedactableString(buffer);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(
                slot,
                requestedSlot,
                count,
                stackNetworkId,
                customName,
                durabilityCorrection
        );
    }

    @Override
    protected void writeItemEntry(ByteBuf buffer, ItemStackResponseSlotInfo itemEntry) {
        buffer.writeByte(itemEntry.getRequestedSlot());
        buffer.writeByte(itemEntry.getSlot());
        buffer.writeByte(itemEntry.getAmount());
        VarInts.writeInt(buffer, itemEntry.getItemStackNetId().getID());
        this.writeRedactableString(buffer, itemEntry.getCustomName());
        VarInts.writeInt(buffer, itemEntry.getDurabilityCorrection());
    }
}
