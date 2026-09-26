package org.cloudburstmc.protocol.bedrock.data.player.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientInputLockComponent {

    RESET(0),
    CAMERA(2),
    MOVEMENT(4),
    LATERAL_MOVEMENT(16),
    SNEAK(32),
    JUMP(64),
    MOUNT(128),
    DISMOUNT(256),
    MOVE_FORWARD(512),
    MOVE_BACKWARD(1024),
    MOVE_LEFT(2048),
    MOVE_RIGHT(4096);

    private final int id;

    public static final ClientInputLockComponent[] VALUES = values();

    public static ClientInputLockComponent from(int ordinal) {
        if (ordinal >= 0 && ordinal < VALUES.length) {
            return VALUES[ordinal];
        }
        throw new UnsupportedOperationException("Detected unknown ClientInputLockComponent ID: " + ordinal);
    }
}