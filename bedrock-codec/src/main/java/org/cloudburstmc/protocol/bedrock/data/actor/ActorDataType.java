package org.cloudburstmc.protocol.bedrock.data.actor;

public class ActorDataType<T> {

    private final String name;
    private final Class<?> type;

    public ActorDataType(Class<? super T> type, String name) {
        this.name = name;
        this.type = type;
    }

    public boolean isInstance(Object value) {
        return type.isInstance(value);
    }

    public String getTypeName() {
        return this.type.getTypeName();
    }

    @Override
    public String toString() {
        return name;
    }
}
