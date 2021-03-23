package com.epam.training.controllers;

import com.epam.training.constants.ConstantsContextAttributes;
import com.epam.training.constants.ConstantsJSP;
import com.epam.training.exceptions.ValidationException;
import com.epam.training.helpers.collections.HeterogeneousMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "BaseController")
public class BaseController extends HttpServlet {

    private HeterogeneousMap mapDAO;

    /**
     * Initializing DAO map from servlet context.
     * @throws ServletException if some proble occured
     */
    @Override
    public void init() throws ServletException {
       ServletContext servletContext = getServletContext();
       mapDAO = (HeterogeneousMap)
               servletContext.getAttribute(ConstantsContextAttributes.DAO_MAP);
       if (mapDAO == null) {
           throw new ServletException("No such attribute in ServletContext -> "
                   + ConstantsContextAttributes.DAO_MAP);
       }
    }

    /**
     * @param type class instance of DAO implementation
     * @param <T> type of DAO implementation
     * @return instance of DAO implementation
     */
    protected  <T> T getDaoImpl(final Class<T> type) {
        T implDAO = mapDAO.getItem(type);
        if (implDAO == null) {
            throw new IllegalArgumentException("No DAO impl for class -> "
                    + type.getName());
        }
        return implDAO;
    }

    /**
     * Forwarding error to another recourse.
     * @param ex exception instance
     * @param url path of recourse
     * @param request request
     * @param response response
     * @throws ServletException if some problem occured
     * @throws IOException if some problem occured
     */
    protected void forwardError(final Throwable ex, final String url,
                                final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors;
        if (ex instanceof ValidationException) {
            errors = ((ValidationException) ex).getErrors();
        } else {
            errors = Collections.singletonList(ex.getMessage());
        }
        request.setAttribute(ConstantsJSP.ATTR_ERROR, errors);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    /**
     * Forwarding request and response.
     * @param url path to recourse
     * @param request request
     * @param response response
     * @throws ServletException if some problem occured
     * @throws IOException if some problem occured
     */
    protected void forward(final String url, final HttpServletRequest request,
                           final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
