package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.resourcepack.PackInstanceId;
import org.cloudburstmc.protocol.bedrock.data.world.Experiments;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ResourcePackStackPacket implements BedrockPacket {

    private boolean texturePackRequired;
    private final List<PackInstanceId> addonList = new ObjectArrayList<>();
    private final List<PackInstanceId> texturePackList = new ObjectArrayList<>();
    private String baseGameVersion;
    private Experiments experiments;
    private boolean includeEditorPacks;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_STACK;
    }

    @Override
    public ResourcePackStackPacket clone() {
        try {
            return (ResourcePackStackPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}