package com.sales.sales_system.response;

import com.sales.sales_system.entity.Category;
import java.util.List;

/*
* +--------------------+
* | MAKE RESPONSE BODY |
* +--------------------+
*
* This is a generic class to
* handle all kind of request,
* at least until I learn a better way.
*
* Using Generic type to set
* the according body.
* */
public class Response <T>
{
    private T body;
    private String response;

    public Response() {
    }

    public Response(String response)
    {
        this.response = response;
    }

    public Response(T body, String response) {
        this.body = body;
        this.response = response;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
