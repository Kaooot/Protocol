package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.PlayerAuthInputSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.PackedLegacyItemUseInventoryTransaction;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v1001 extends PlayerAuthInputSerializer_v944 {
    public static final PlayerAuthInputSerializer_v1001 INSTANCE = new PlayerAuthInputSerializer_v1001();

    @Override
    protected void writePackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedLegacyItemUseInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getLegacyRequestID().getID());
        final boolean legacySetItemSlotsHasValue = transaction.getLegacySetItemSlots().isEmpty();
        buffer.writeBoolean(legacySetItemSlotsHasValue);
        if (legacySetItemSlotsHasValue) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper, slot) -> {
                codecHelper.writeContainerEnumName(buf, slot.getContainerEnum());
                codecHelper.writeByteArray(buf, slot.getSlots());
            });
        }
        helper.writeItemUseInventoryTransaction(buffer, transaction.getTransaction());
    }

    @Override
    protected PackedLegacyItemUseInventoryTransaction readPackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedLegacyItemUseInventoryTransaction transaction = new PackedLegacyItemUseInventoryTransaction();
        transaction.setLegacyRequestID(new ItemStackLegacyRequestId(VarInts.readInt(buffer)));
        if (buffer.readBoolean()) {
            helper.readArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper) -> {
                final LegacySetSlot slot = new LegacySetSlot();
                slot.setContainerEnum(codecHelper.readContainerEnumName(buf));
                slot.setSlots(codecHelper.readByteArray(buf));
                return slot;
            });
        }
        transaction.setTransaction(helper.readItemUseInventoryTransaction(buffer));
        return transaction;
    }
}