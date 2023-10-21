package com.sales.sales_system.error_handling.db_errors;

public class DatabaseErrorsResponse
{
    private int status;
    private String message;

    public DatabaseErrorsResponse()
    {

    }

    public DatabaseErrorsResponse(int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
