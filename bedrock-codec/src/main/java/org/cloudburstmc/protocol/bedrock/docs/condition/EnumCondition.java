package org.cloudburstmc.protocol.bedrock.docs.condition;

import org.cloudburstmc.protocol.bedrock.docs.DataType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kaooot
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumCondition {

    Class<?> clazz();

    String value() default "";

    Serialization[] values() default {};

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Serialization {
        String value();

        DataType type();

        Class<?> context() default Object.class;
    }
}