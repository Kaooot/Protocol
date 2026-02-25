package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

/**
 * @author Kaooot
 */
public interface AttributeLayerSyncPacketData {

    Type getType();

    enum Type {
        UPDATE_ATTRIBUTE_LAYERS,
        UPDATE_ATTRIBUTE_LAYER_SETTINGS,
        UPDATE_ENVIRONMENT_ATTRIBUTES,
        REMOVE_ENVIRONMENT_ATTRIBUTES;

        private static final Type[] VALUES = values();

        public static Type from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown AttributeLayerSyncPacketData.Type ID: " + ordinal);
        }
    }
}