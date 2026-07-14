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
        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper, slot) -> {
                codecHelper.writeContainerEnumName(buf, slot.getContainerEnum());
                codecHelper.writeByteArray(buf, slot.getSlots());
            });
        }
        helper.writeArray(buffer, transaction.getActions(), this::writeInventoryAction);
        this.writeItemUseInventoryTransaction(buffer, helper, transaction.getTransaction());
    }

    @Override
    protected PackedLegacyItemUseInventoryTransaction readPackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedLegacyItemUseInventoryTransaction transaction = new PackedLegacyItemUseInventoryTransaction();
        transaction.setLegacyRequestID(new ItemStackLegacyRequestId(VarInts.readInt(buffer)));
        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.readArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper) -> {
                final LegacySetSlot slot = new LegacySetSlot();
                slot.setContainerEnum(codecHelper.readContainerEnumName(buf));
                slot.setSlots(codecHelper.readByteArray(buf));
                return slot;
            });
        }
        helper.readArray(buffer, transaction.getActions(), this::readInventoryAction, helper.getEncodingSettings().maxInventoryActionsOrRequests());
        transaction.setTransaction(this.readItemUseInventoryTransaction(buffer, helper));
        return transaction;
    }
}