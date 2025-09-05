package dev.mccue.jdbc;

import org.intellij.lang.annotations.MagicConstant;

import java.sql.Connection;

public final class TransactionOptions {
    public static final TransactionOptions DEFAULT
            = TransactionOptions.builder().build();

    @MagicConstant(intValues = {
            Connection.TRANSACTION_NONE,
            Connection.TRANSACTION_READ_COMMITTED,
            Connection.TRANSACTION_READ_UNCOMMITTED,
            Connection.TRANSACTION_REPEATABLE_READ,
            Connection.TRANSACTION_SERIALIZABLE
    }) final Integer isolation;
    final Boolean readOnly;
    final Boolean rollbackOnly;

    private TransactionOptions(Builder builder) {
        isolation = builder.isolation;
        readOnly = builder.readOnly;
        rollbackOnly = builder.rollbackOnly;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Builder() {}

        @MagicConstant(intValues = {
                Connection.TRANSACTION_NONE,
                Connection.TRANSACTION_READ_COMMITTED,
                Connection.TRANSACTION_READ_UNCOMMITTED,
                Connection.TRANSACTION_REPEATABLE_READ,
                Connection.TRANSACTION_SERIALIZABLE
        }) Integer isolation = null;
        Boolean readOnly = null;
        Boolean rollbackOnly = null;


        public Builder isolation(
                @MagicConstant(intValues = {
                        Connection.TRANSACTION_NONE,
                        Connection.TRANSACTION_READ_COMMITTED,
                        Connection.TRANSACTION_READ_UNCOMMITTED,
                        Connection.TRANSACTION_REPEATABLE_READ,
                        Connection.TRANSACTION_SERIALIZABLE
                }) int isolation) {
            this.isolation = isolation;
            return this;
        }

        public Builder readOnly(boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        public Builder rollbackOnly(boolean rollbackOnly) {
            this.rollbackOnly = rollbackOnly;
            return this;
        }

        public TransactionOptions build() {
            return new TransactionOptions(this);
        }
    }
}
