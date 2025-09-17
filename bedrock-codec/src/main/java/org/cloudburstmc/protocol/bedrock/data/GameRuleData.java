package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.cloudburstmc.protocol.bedrock.docs.DataType;
import org.cloudburstmc.protocol.bedrock.docs.Docs;
import org.cloudburstmc.protocol.bedrock.docs.condition.Conditions;
import org.cloudburstmc.protocol.bedrock.docs.condition.EnumCondition;

@Docs
@Value
@AllArgsConstructor
public class GameRuleData<T> {
    @Docs(name = "Rule Name")
    String name;
    @Docs(name = "Rule Can Be Modified")
    boolean editable;
    @Docs(name = "Rule Type", type = DataType.UNSIGNED_VARINT)
    Type type;
    @Docs(name = "Rule Value")
    @Conditions(field = "type", enumValues = {
            @EnumCondition(clazz = Type.class, values = {
                    @EnumCondition.Serialization(value = "BOOL", type = DataType.BOOL),
                    @EnumCondition.Serialization(value = "INT", type = DataType.INT),
                    @EnumCondition.Serialization(value = "INT", type = DataType.UNSIGNED_VARINT, context = LevelSettings.class),
                    @EnumCondition.Serialization(value = "FLOAT", type = DataType.FLOAT)
            }),
    })
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

    @Docs(name = "GameRule::Type")
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
