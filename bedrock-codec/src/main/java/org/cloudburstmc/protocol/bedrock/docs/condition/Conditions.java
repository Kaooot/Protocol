package org.cloudburstmc.protocol.bedrock.docs.condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kaooot
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Conditions {

    String field();

    int insertIndex() default 0;

    Condition[] values() default {};

    EnumCondition[] enumValues() default {};
}