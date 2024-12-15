package dev.mccue.jdbc;

import java.lang.reflect.RecordComponent;
import java.sql.ResultSet;
import java.sql.SQLException;

class DefaultRecordComponentGetter implements RecordComponentGetter<Object> {
    static final RecordComponentGetter<Object> INSTANCE
            = new DefaultRecordComponentGetter();

    public DefaultRecordComponentGetter() {}

    @Override
    public final Object getRecordComponent(ResultSet rs, RecordComponent component)
            throws SQLException {
        var column = component.getAnnotation(Column.class);

        int index = -1;
        if (column != null) {
            index = column.index();
        }
        if (index >= 0) {
            return getIndexedRecordComponent(rs, component, index);
        }
        else {
            String label;
            if (column != null && !"".equals(column.label())) {
                label = column.label();
            }
            else {
                label = component.getName();
            }

            return getLabeledRecordComponent(rs, component, label);
        }
    }

    protected Object getIndexedRecordComponent(ResultSet rs, RecordComponent recordComponent, int index) throws SQLException {
        var type = recordComponent.getType();
        if (!type.isPrimitive()) {
            if (type == Integer.class) {
                return ResultSets.getIntegerNullable(rs, index);
            }
            else if (type == Long.class) {
                return ResultSets.getLongNullable(rs, index);
            }
            else if (type == Boolean.class) {
                return ResultSets.getBooleanNullable(rs, index);
            }
            else if (type == Double.class) {
                return ResultSets.getDoubleNullable(rs, index);
            }
            else if (type == Float.class) {
                return ResultSets.getFloatNullable(rs, index);
            }
            else if (type == Byte.class) {
                return ResultSets.getByteNullable(rs, index);
            }
            else if (type == Short.class) {
                return ResultSets.getShortNullable(rs, index);
            }
            else {
                return rs.getObject(index, recordComponent.getType());
            }
        }
        else if (type == int.class) {
            return ResultSets.getIntegerNotNull(rs, index);
        }
        else if (type == long.class) {
            return ResultSets.getLongNotNull(rs, index);
        }
        else if (type == boolean.class) {
            return ResultSets.getBooleanNotNull(rs, index);
        }
        else if (type == double.class) {
            return ResultSets.getDoubleNotNull(rs, index);
        }
        else if (type == float.class) {
            return ResultSets.getFloatNotNull(rs, index);
        }
        else if (type == byte.class) {
            return ResultSets.getByteNotNull(rs, index);
        }
        else if (type == short.class) {
            return ResultSets.getShortNotNull(rs, index);
        }
        else { // char.class - unclear how best to handle.
            return rs.getObject(index, recordComponent.getType());
        }
    }

    protected Object getLabeledRecordComponent(
            ResultSet rs,
            RecordComponent recordComponent,
            String label
    ) throws SQLException {

        var type = recordComponent.getType();
        if (!type.isPrimitive()) {
            if (type == Integer.class) {
                return ResultSets.getIntegerNullable(rs, label);
            }
            else if (type == Long.class) {
                return ResultSets.getLongNullable(rs, label);
            }
            else if (type == Boolean.class) {
                return ResultSets.getBooleanNullable(rs, label);
            }
            else if (type == Double.class) {
                return ResultSets.getDoubleNullable(rs, label);
            }
            else if (type == Float.class) {
                return ResultSets.getFloatNullable(rs, label);
            }
            else if (type == Byte.class) {
                return ResultSets.getByteNullable(rs, label);
            }
            else if (type == Short.class) {
                return ResultSets.getShortNullable(rs, label);
            }
            else {
                return rs.getObject(label, recordComponent.getType());
            }
        }
        else if (type == int.class) {
            return ResultSets.getIntegerNotNull(rs, label);
        }
        else if (type == long.class) {
            return ResultSets.getLongNotNull(rs, label);
        }
        else if (type == boolean.class) {
            return ResultSets.getBooleanNotNull(rs, label);
        }
        else if (type == double.class) {
            return ResultSets.getDoubleNotNull(rs, label);
        }
        else if (type == float.class) {
            return ResultSets.getFloatNotNull(rs, label);
        }
        else if (type == byte.class) {
            return ResultSets.getByteNotNull(rs, label);
        }
        else if (type == short.class) {
            return ResultSets.getShortNotNull(rs, label);
        }
        else { // char.class - unclear how best to handle.
            return rs.getObject(label, recordComponent.getType());
        }
    }
}
