package dev.mccue.jdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a record component as having a value in the
 * {@link java.sql.ResultSet} which isn't found by its
 * name or which is retrieved from the {@link java.sql.ResultSet}
 * in a special way.
 */
@Target(ElementType.RECORD_COMPONENT)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    /**
     * When set to a number &gte; 0, will be used as the key
     * to look up a value in the {@link java.sql.ResultSet}.
     *
     * <p>
     *     If both this and {@link Column#label()}
     *     are set, this wins.
     * </p>
     * @return The {@code columnIndex} to use.
     */
    int index() default -1;

    /**
     * When set to a non-empty value, will be used as the key
     * to look up a value in the {@link java.sql.ResultSet}.
     * @return The {@code columnLabel} to use.
     *
     * <p>
     *     If both this and {@link Column#index()}
     *     are set, {@link Column#index()} wins..
     * </p>
     */
    String label() default "";

    /**
     * The {@link RecordComponentGetter} to use for getting the
     * column's value out from a {@link java.sql.ResultSet}.
     * @return A {@link RecordComponentGetter}
     */
    Class<? extends RecordComponentGetter<?>> recordComponentGetter()
            default DefaultRecordComponentGetter.class;
}
