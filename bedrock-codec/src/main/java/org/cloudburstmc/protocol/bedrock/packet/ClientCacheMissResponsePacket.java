package org.cloudburstmc.protocol.bedrock.packet;

import io.netty.util.AbstractReferenceCounted;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.chunk.MissingBlobData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
@ToString(doNotUseGetters = true)
public class ClientCacheMissResponsePacket extends AbstractReferenceCounted implements BedrockPacket {

    private final List<MissingBlobData> missingBlobs = new ObjectArrayList<>();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_MISS_RESPONSE;
    }

    @Override
    protected void deallocate() {
        this.missingBlobs.forEach(missingBlobData -> missingBlobData.getBlobData().release());
    }

    @Override
    public ClientCacheMissResponsePacket touch(Object hint) {
        this.missingBlobs.forEach(missingBlobData -> missingBlobData.getBlobData().touch(hint));
        return this;
    }

    @Override
    public ClientCacheMissResponsePacket clone() {
        try {
            return (ClientCacheMissResponsePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}