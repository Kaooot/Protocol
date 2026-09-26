package org.cloudburstmc.protocol.bedrock.data.education;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduSharedUriResource {
    public static final EduSharedUriResource EMPTY = new EduSharedUriResource("", "");

    private String buttonName;
    private String linkUri;
}
