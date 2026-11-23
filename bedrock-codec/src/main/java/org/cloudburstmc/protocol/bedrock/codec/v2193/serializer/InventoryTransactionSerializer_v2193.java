package org.cloudburstmc.protocol.bedrock.codec.v2193.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.InventoryTransactionSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryTransactionSerializer_v2193 extends InventoryTransactionSerializer_v1001 {
    public static final InventoryTransactionSerializer_v2193 INSTANCE = new InventoryTransactionSerializer_v2193();

    @Override
    protected void writeTransactionIndex(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet, InventoryTransactionType type) {
        VarInts.writeUnsignedInt(buffer, type.ordinal());
    }

    @Override
    protected InventoryTransactionType readTransactionIndex(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
        return InventoryTransactionType.values()[VarInts.readUnsignedInt(buffer)];
    }
}