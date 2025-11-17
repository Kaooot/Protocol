package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class GameRuleData<T> {
    String name;
    boolean editable;
    Type type;
    T value;

    public GameRuleData(String name, T value) {
        this.name = name;
        this.value = value;
        this.editable = false;
        if (value instanceof Boolean) {
            this.type = Type.BOOL;
        } else if (value instanceof Integer) {
            this.type = Type.INT;
        } else if (value instanceof Float) {
            this.type = Type.FLOAT;
        } else {
            this.type = Type.INVALID;
            throw new IllegalStateException("Game rule type is invalid for " + this.toString());
        }
    }

    @Override
    public String toString() {
        return this.name + '=' + this.value;
    }

    public enum Type {
        INVALID,
        BOOL,
        INT,
        FLOAT;

        private static final Type[] VALUES = values();

        public static Type from(int ordinal) {
            return VALUES[ordinal];
        }
    }
}
