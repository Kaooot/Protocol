package org.cloudburstmc.protocol.bedrock.data.payload.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.Dimension;

/**
 * @author Kaooot
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DimensionType {

    int value;

    public static DimensionType from(int value) {
        return new DimensionType(value);
    }

    public static DimensionType from(Dimension dimension) {
        if (dimension.equals(Dimension.CUSTOM)) {
            throw new UnsupportedOperationException("Unable to create a DimensionType from a CUSTOM dimension enum value");
        }
        return from(dimension.ordinal());
    }

    public Dimension asEnum() {
        return Dimension.from(this.value);
    }
}