package com.epam.training.wrappers;

import com.epam.training.helpers.XSSDefender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class XSSProtectionWrapper extends HttpServletRequestWrapper {

    private static final XSSDefender DEFENDER = new XSSDefender();

    /**
     *
     * @param request wrapped request
     */
    public XSSProtectionWrapper(final HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(final String name) {
        String parameterVal = super.getParameter(name);
        if (parameterVal == null) {
            return null;
        }
        return DEFENDER.getCleanString(parameterVal);
    }

    @Override
    public String[] getParameterValues(final String name) {
        String[] oldValues = super.getParameterValues(name);
        if (oldValues == null) {
            return null;
        }
        return Arrays.stream(oldValues)
                .map(DEFENDER::getCleanString)
                    .toArray(String[]::new);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Enumeration<String> parameterNames = getParameterNames();
        Map<String, String[]> parameterMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            parameterMap.put(parameterName,
                    getParameterValues(parameterName));
        }
        return parameterMap;
    }

    @Override
    public String getHeader(final String name) {
        String headerVal = super.getHeader(name);
        if (headerVal == null) {
            return null;
        }
        return DEFENDER.getCleanString(headerVal);
    }

    @Override
    public Enumeration<String> getHeaders(final String name) {
        Enumeration<String> oldHeaders = super.getHeaders(name);
        if (oldHeaders == null) {
            return null;
        }
        Set<String> newHeaders = new HashSet<>();
        while (oldHeaders.hasMoreElements()) {
            String header = oldHeaders.nextElement();
            newHeaders.add(DEFENDER.getCleanString(header));
        }
        return Collections.enumeration(newHeaders);
    }

}
