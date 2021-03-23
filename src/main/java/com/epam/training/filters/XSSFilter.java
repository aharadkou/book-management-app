package com.epam.training.filters;

import com.epam.training.wrappers.XSSProtectionWrapper;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "XSSFilter", urlPatterns = {"/*"})
public final class XSSFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse resp,
                         final FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(new XSSProtectionWrapper(httpReq), resp);
    }

}
