package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

/**
 * @since v419: initially added as ItemComponentPacket
 * @since v776: renamed and moved the item registry out of StartGamePacket and added the
 * item component registry to ItemRegistryPacket
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ItemRegistryPacket implements BedrockPacket {

    private final List<SimpleItemDefinition> itemData = new ObjectArrayList<>();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ITEM_REGISTRY;
    }

    @Override
    public ItemRegistryPacket clone() {
        try {
            return (ItemRegistryPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}