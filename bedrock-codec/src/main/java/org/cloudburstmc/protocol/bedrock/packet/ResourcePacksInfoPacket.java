package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.*;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ResourcePacksInfoPacket implements BedrockPacket {
    /**
     * @deprecated since v729
     */
    private final List<Entry> behaviorPacks = new ObjectArrayList<>();
    private final List<Entry> resourcePacks = new ObjectArrayList<>();
    private boolean resourcePackRequired;
    /**
     * @since v662
     */
    private boolean hasAddonPacks;
    private boolean hasScripts;
    /**
     * @since v448
     * @deprecated since v729
     */
    private boolean forceServerPacksEnabled;
    /**
     * @since v766
     */
    private UUID worldTemplateUUID;
    /**
     * @since v766
     */
    private String worldTemplateVersion;
    /**
     * Force the client to disable vibrant visuals, even if the client supports it.
     *
     * @since v818
     */
    private boolean forceDisableVibrantVisuals;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACKS_INFO;
    }

    @Data
    @AllArgsConstructor
    public static class Entry {
        private UUID packId;
        private String packVersion;
        private long packSize;
        private String contentKey;
        private String subPackName;
        private String contentIdentity;
        private boolean hasScripts;
        private boolean isRayTracingCapable;
        /**
         * @since v712
         */
        private boolean isAddonPack;
        /**
         * @since v748
         */
        private String cdnUrl;
    }

    @Override
    public ResourcePacksInfoPacket clone() {
        try {
            return (ResourcePacksInfoPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

