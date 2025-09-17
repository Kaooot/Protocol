package org.cloudburstmc.protocol.bedrock.docs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kaooot
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangelogEntry {

    Version version();

    Type type();

    String value();

    enum Type {
        ADD,
        UPDATE,
        REMOVE
    }
}