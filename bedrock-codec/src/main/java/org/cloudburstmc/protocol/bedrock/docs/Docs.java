package org.cloudburstmc.protocol.bedrock.docs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kaooot
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Docs {

    /**
     * The name of the field.
     */
    String name() default "";

    /**
     * Additional field notes. Empty by default
     */
    String notes() default "";

    /**
     * Only relevant if a field is added or removed
     */
    Version since() default Version.UNDEFINED;

    /**
     * Undefined means automatic type detection
     */
    DataType type() default DataType.UNDEFINED;

    /**
     * Whether a field is optional.
     */
    boolean isOptional() default false;

    /**
     * Whether to use the name of the field
     */
    boolean useCodeFieldName() default false;

    /**
     * The example element name. Requires the type {@link DataType#ARRAY}
     */
    String example() default "";

    /**
     * The array size type. Requires the type {@link DataType#ARRAY}
     */
    DataType sizeType() default DataType.UNSIGNED_VARINT;

    LinkType linkType() default LinkType.DEFAULT;

    Context[] context() default {};

    String typeName() default "";

    enum LinkType {
        DEFAULT,
        MEMBERS
    }

    @interface Context {
        Class<?> value();

        DataType type();
    }
}