package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v766.serializer.PlayerAuthInputSerializer_v766;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.LegacySetItemSlotData;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v944 extends PlayerAuthInputSerializer_v766 {
    public static final PlayerAuthInputSerializer_v944 INSTANCE = new PlayerAuthInputSerializer_v944();

    @Override
    protected void writeItemUseTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseTransaction transaction) {
        super.writeItemUseTransaction(buffer,helper,transaction);
        VarInts.writeUnsignedInt(buffer, transaction.getClientCooldownState());
    }

    @Override
    protected ItemUseTransaction readItemUseTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        ItemUseTransaction itemTransaction = new ItemUseTransaction();

        int legacyRequestId = VarInts.readInt(buffer);
        itemTransaction.setLegacyRequestId(legacyRequestId);

        if (legacyRequestId < -1 && (legacyRequestId & 1) == 0) {
            helper.readArray(buffer, itemTransaction.getLegacySlots(), (buf, packetHelper) -> {
                byte containerId = buf.readByte();
                byte[] slots = packetHelper.readByteArray(buf, 89);
                return new LegacySetItemSlotData(containerId, slots);
            });
        }

        boolean hasNetIds = helper.readInventoryActions(buffer, itemTransaction.getActions());
        itemTransaction.setActionType(VarInts.readUnsignedInt(buffer));
        itemTransaction.setTriggerType(ItemUseTransaction.TriggerType.values()[VarInts.readUnsignedInt(buffer)]);
        itemTransaction.setBlockPosition(helper.readBlockPosition(buffer));
        itemTransaction.setBlockFace(VarInts.readInt(buffer));
        itemTransaction.setHotbarSlot(VarInts.readInt(buffer));
        itemTransaction.setItemInHand(helper.readItem(buffer));
        itemTransaction.setPlayerPosition(helper.readVector3f(buffer));
        itemTransaction.setClickPosition(helper.readVector3f(buffer));
        itemTransaction.setBlockDefinition(helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        itemTransaction.setClientInteractPrediction(ItemUseTransaction.PredictedResult.values()[VarInts.readUnsignedInt(buffer)]);
        itemTransaction.setClientCooldownState(VarInts.readUnsignedInt(buffer));
        return itemTransaction;
    }
}