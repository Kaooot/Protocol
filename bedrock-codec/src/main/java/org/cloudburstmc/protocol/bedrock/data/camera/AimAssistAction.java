package org.cloudburstmc.protocol.bedrock.data.camera;

import org.cloudburstmc.protocol.bedrock.docs.Docs;

@Docs(name = "CameraAimAssistPacketPayload::Action")
public enum AimAssistAction {
    SET,
    CLEAR
}
