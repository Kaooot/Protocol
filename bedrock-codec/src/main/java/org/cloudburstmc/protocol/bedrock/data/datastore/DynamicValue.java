package org.cloudburstmc.protocol.bedrock.data.datastore;

import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Deprecated
public class DynamicValue {

    DynamicValueType type;
    Object value;

    public boolean asBoolean() {
        return (boolean) this.value;
    }

    public long asInteger() {
        return (long) this.value;
    }

    public double asNumber() {
        return (double) this.value;
    }

    public String asString() {
        return this.value.toString();
    }

    public List<DynamicValue> asArray() {
        return (List<DynamicValue>) this.value;
    }

    public Map<String, DynamicValue> asObject() {
        return (Map<String, DynamicValue>) this.value;
    }
}