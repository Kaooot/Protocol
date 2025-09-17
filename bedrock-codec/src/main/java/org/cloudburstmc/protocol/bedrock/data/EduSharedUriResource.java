package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Docs
@Value
public class EduSharedUriResource {
    public static final EduSharedUriResource EMPTY = new EduSharedUriResource("", "");

    String buttonName;
    String linkUri;
}
