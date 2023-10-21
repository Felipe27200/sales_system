package com.sales.sales_system.error_handling.db_errors;

public class DuplicateConstraintException extends RuntimeException
{
    public DuplicateConstraintException(String message) {
        super(message);
    }

    public DuplicateConstraintException(Throwable cause) {
        super(cause);
    }

    public DuplicateConstraintException(String message, Throwable cause) {
        super(message, cause);
    }
}
