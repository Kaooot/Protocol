package org.cloudburstmc.protocol.bedrock.codec.v924.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.ClientboundDataStoreSerializer_v898;

/**
 * The v924 wire difference (pathUpdateCount, {@code @since v924}) is handled by the
 * version-chained {@code writeDataStoreUpdate}/{@code readDataStoreUpdate} in
 * {@code BedrockCodecHelper_v924}, so the serializer inherits v898's VariantCodec logic unchanged.
 */
public class ClientboundDataStoreSerializer_v924 extends ClientboundDataStoreSerializer_v898 {

    public static final ClientboundDataStoreSerializer_v924 INSTANCE = new ClientboundDataStoreSerializer_v924();
}
