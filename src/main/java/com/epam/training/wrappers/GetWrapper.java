package com.epam.training.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GetWrapper extends HttpServletRequestWrapper {

    private static final String GET_METHOD = "GET";

    /**
     *
     * @param request wrapped request
     */
    public GetWrapper(final HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getMethod() {
        return GET_METHOD;
    }

}
