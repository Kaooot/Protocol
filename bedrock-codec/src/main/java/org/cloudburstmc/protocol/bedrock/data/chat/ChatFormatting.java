package org.cloudburstmc.protocol.bedrock.data.chat;

import java.lang.String;

import lombok.Data;

@Data
public class ChatFormatting {

    private String namePrefix;
    private String namePostfix;
    private String messagePrefix;
    private String messagePostfix;
}