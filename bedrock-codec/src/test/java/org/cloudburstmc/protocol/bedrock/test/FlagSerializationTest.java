package org.cloudburstmc.protocol.bedrock.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.Bedrock_v776;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.bedrock.transformer.FlagTransformer;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.junit.jupiter.api.AssertionFailureBuilder;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.EnumMap;


public class FlagSerializationTest {
    private static final BedrockCodecHelper CODEC_HELPER = Bedrock_v776.CODEC.createHelper();

    private static final TypeMap<ActorFlags> ENTITY_FLAGS;

    static {
        try {
            Field field = Bedrock_v776.class.getDeclaredField("ENTITY_FLAGS");
            field.setAccessible(true);
            ENTITY_FLAGS = (TypeMap<ActorFlags>) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static final FlagTransformer FLAGS_TRANSFORMER = new FlagTransformer(ENTITY_FLAGS, 0);
    private static final FlagTransformer FLAGS_2_TRANSFORMER = new FlagTransformer(ENTITY_FLAGS, 1);

    @Test
    public void testFlagSerialization() {
        ActorDataMap dataMap = new ActorDataMap();
        dataMap.setFlag(ActorFlags.BLOCKING, true); // FLAGS_2

        testSerializationRoundTrip(dataMap);
        verifyTransformerOutput(dataMap, false, true);

        dataMap = new ActorDataMap();
        dataMap.setFlag(ActorFlags.HAS_GRAVITY, true); // FLAGS

        testSerializationRoundTrip(dataMap);
        verifyTransformerOutput(dataMap, true, false);

        dataMap = new ActorDataMap();
        dataMap.setFlag(ActorFlags.HAS_GRAVITY, true); // FLAGS
        dataMap.setFlag(ActorFlags.BLOCKING, true); // FLAGS_2

        testSerializationRoundTrip(dataMap);
        verifyTransformerOutput(dataMap, true, true);

        dataMap = new ActorDataMap();
        dataMap.setFlag(ActorFlags.HAS_GRAVITY, false); // FLAGS
        dataMap.setFlag(ActorFlags.BLOCKING, true); // FLAGS_2

        testSerializationRoundTrip(dataMap);
        verifyTransformerOutput(dataMap, true, true);
    }

    private void verifyTransformerOutput(ActorDataMap dataMap, boolean shouldHaveFlags, boolean shouldHaveFlags2) {
        Long serializedFlags = FLAGS_TRANSFORMER.serialize(CODEC_HELPER, dataMap, dataMap.getFlags());
        if (shouldHaveFlags && serializedFlags == null) {
            AssertionFailureBuilder.assertionFailure()
                    .message("FLAGS")
                    .expected("non-null value")
                    .actual(null)
                    .buildAndThrow();
        } else if (!shouldHaveFlags && serializedFlags != null) {
            AssertionFailureBuilder.assertionFailure()
                    .message("FLAGS")
                    .expected(null)
                    .actual(serializedFlags)
                    .buildAndThrow();
        }

        Long serializedFlags2 = FLAGS_2_TRANSFORMER.serialize(CODEC_HELPER, dataMap, dataMap.getFlags());
        if (shouldHaveFlags2 && serializedFlags2 == null) {
            AssertionFailureBuilder.assertionFailure()
                    .message("FLAGS_2")
                    .expected("non-null value")
                    .actual(null)
                    .buildAndThrow();
        } else if (!shouldHaveFlags2 && serializedFlags2 != null) {
            AssertionFailureBuilder.assertionFailure()
                    .message("FLAGS_2")
                    .expected(null)
                    .actual(serializedFlags2)
                    .buildAndThrow();
        }
    }

    private void testSerializationRoundTrip(ActorDataMap originalDataMap) {
        ByteBuf buffer = Unpooled.buffer();
        CODEC_HELPER.writeEntityData(buffer, originalDataMap);

        ActorDataMap deserializedDataMap = new ActorDataMap();
        CODEC_HELPER.readEntityData(buffer, deserializedDataMap);

        assertFlagsMatch(originalDataMap, deserializedDataMap);
    }

    private void assertFlagsMatch(ActorDataMap expected, ActorDataMap actual) {
        EnumMap<ActorFlags, Boolean> expectedFlags = expected.getFlags();
        EnumMap<ActorFlags, Boolean> actualFlags = actual.getFlags();
        for (ActorFlags flag : ActorFlags.values()) {
            Boolean actualValue = actualFlags.get(flag);
            Boolean expectedValue = expectedFlags.get(flag);
            if (expectedValue == null && Boolean.TRUE.equals(actualValue)) {
                AssertionFailureBuilder.assertionFailure()
                        .message("Flag " + flag + " should not be set")
                        .expected(null)
                        .actual(true)
                        .buildAndThrow();
            }
        }
    }
}