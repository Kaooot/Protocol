package org.cloudburstmc.protocol.bedrock.codec.v560.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ClientInputLockComponent;
import org.cloudburstmc.protocol.bedrock.packet.UpdateClientInputLocksPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Set;

public class UpdateClientInputLocksSerializer_v560 implements BedrockPacketSerializer<UpdateClientInputLocksPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
        this.serializeBitset(buffer, packet.getInputLockComponents());
        helper.writeVector3f(buffer, packet.getServerPos());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
        this.deserializeBitset(buffer, packet.getInputLockComponents());
        packet.setServerPos(helper.readVector3f(buffer));
    }

    protected void serializeBitset(ByteBuf buffer, Set<ClientInputLockComponent> set) {
        int data = 0;
        for (ClientInputLockComponent clientInputLockComponent : set) {
            data |= clientInputLockComponent.getId();
        }
        VarInts.writeUnsignedInt(buffer, data);
    }

    protected void deserializeBitset(ByteBuf buffer, Set<ClientInputLockComponent> set) {
        final int data = VarInts.readUnsignedInt(buffer);
        for (ClientInputLockComponent value : ClientInputLockComponent.VALUES) {
            if ((data & value.getId()) != 0) {
                set.add(value);
            }
        }
    }
}