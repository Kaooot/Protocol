package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.PlayerAuthInputSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.inventory.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.PackedItemUseLegacyInventoryTransaction;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v1001 extends PlayerAuthInputSerializer_v944 {
    public static final PlayerAuthInputSerializer_v1001 INSTANCE = new PlayerAuthInputSerializer_v1001();

    @Override
    protected void writePackedItemUseLegacyInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedItemUseLegacyInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getLegacyRequestID().getID());
        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper, slot) -> {
                codecHelper.writeContainerEnumName(buf, slot.getContainerEnum());
                codecHelper.writeByteArray(buf, slot.getSlots());
            });
        }
        helper.writeArray(buffer, transaction.getActions(), helper::writeInventoryAction);
        this.writeItemUseInventoryTransaction(buffer, helper, transaction.getTransaction());
    }

    @Override
    protected PackedItemUseLegacyInventoryTransaction readPackedItemUseLegacyInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedItemUseLegacyInventoryTransaction transaction = new PackedItemUseLegacyInventoryTransaction();
        transaction.setLegacyRequestID(new ItemStackLegacyRequestId(VarInts.readInt(buffer)));
        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.readArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper) -> {
                final LegacySetSlot slot = new LegacySetSlot();
                slot.setContainerEnum(codecHelper.readContainerEnumName(buf));
                slot.setSlots(codecHelper.readByteArray(buf, 89));
                return slot;
            });
        }
        helper.readArray(buffer, transaction.getActions(), helper::readInventoryAction, helper.getEncodingSettings().maxInventoryActionsOrRequests());
        transaction.setTransaction(this.readItemUseInventoryTransaction(buffer, helper));
        return transaction;
    }
}