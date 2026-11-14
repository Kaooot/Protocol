package org.cloudburstmc.protocol.bedrock.data.connection;

import lombok.Data;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

@Data
public class TransferPlayerUIOptions {

    private String titleText;
    private String bodyText;
    private OptionalBoolean titleVisible = OptionalBoolean.empty();
    private OptionalBoolean bodyVisible = OptionalBoolean.empty();
    private String cancelButtonText;
    private OptionalBoolean cancelButtonVisible = OptionalBoolean.empty();
    private Float cancelButtonDelaySeconds;
}