package org.cloudburstmc.protocol.bedrock.codec.v766;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v729.BedrockCodecHelper_v729;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.math.BigInteger;
import java.util.Set;

public class BedrockCodecHelper_v766 extends BedrockCodecHelper_v729 {

    public BedrockCodecHelper_v766(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                   TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
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
    protected ItemStackResponseSlotInfo readItemStackResponseSlotInfo(ByteBuf buffer) {
        int requestedSlot = buffer.readUnsignedByte();
        int slot = buffer.readUnsignedByte();
        int amount = buffer.readUnsignedByte();
        ItemStackNetId stackNetworkId = new ItemStackNetId(VarInts.readInt(buffer));
        String customName = this.readString(buffer);
        String filteredCustomName = this.readString(buffer);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(requestedSlot, slot, amount, stackNetworkId,
                new RedactableString(customName, filteredCustomName), durabilityCorrection);

    }

    @Override
    protected void writeItemStackResponseSlotInfo(ByteBuf buffer, ItemStackResponseSlotInfo info) {
        buffer.writeByte(info.getRequestedSlot());
        buffer.writeByte(info.getSlot());
        buffer.writeByte(info.getAmount());
        VarInts.writeInt(buffer, info.getItemStackNetId().getID());
        this.writeString(buffer, info.getCustomName().getUnredacted());
        this.writeString(buffer, info.getCustomName().getRedacted());
        VarInts.writeInt(buffer, info.getDurabilityCorrection());
    }
}
