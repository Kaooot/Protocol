package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.Experiment;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ResourcePackStackPacket implements BedrockPacket {
    private boolean texturePackRequired;
    private final List<Entry> addonList = new ObjectArrayList<>();
    private final List<Entry> texturePackList = new ObjectArrayList<>();
    private String baseGameVersion;
    private final List<Experiment> experiments = new ObjectArrayList<>();
    private boolean wereAnyExperimentsEverToggled;
    /**
     * @since v671
     */
    private boolean includeEditorPacks;


    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_STACK;
    }

    @Value
    public static class Entry {
        private final String id;
        private final String version;
        private final String subPackName;
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

