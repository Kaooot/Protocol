package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Docs
@Value
public class NetworkPermissions {
    public static final NetworkPermissions DEFAULT = new NetworkPermissions(false);

    boolean serverAuthSoundEnabled;
}
