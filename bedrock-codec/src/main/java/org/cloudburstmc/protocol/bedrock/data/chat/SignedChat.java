package org.cloudburstmc.protocol.bedrock.data.chat;

import lombok.Data;

@Data
public class SignedChat {

    private String keyId;
    private String senderId;
    private String messageId;
    private String scanText;
    private String redactedText;
    private String authorName;
    private String signature;
}