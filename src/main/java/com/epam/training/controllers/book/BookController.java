package com.epam.training.controllers.book;

import com.epam.training.constants.ConstantsJSP;
import com.epam.training.exceptions.DAOException;
import com.epam.training.model.beans.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "BookController",
            initParams = {
                @WebInitParam(name = BookController.PARAM_TIME, value = "60"),
                @WebInitParam(name = BookController.PARAM_FLAG, value = "true")
            }, urlPatterns = {"/book"})
public final class BookController extends BaseBookController {

    static final String PARAM_TIME = "unavailableTime";

    static final String PARAM_FLAG = "unavailabilitySupport";

    private boolean unavailabilityFlag;

    private int unavailableTime;

    private static final Logger LOGGER =
            LogManager.getLogger(BaseBookController.class);

    /**Calls init() of superclass,
     * initializing time and flag of unavailability.
     * @throws ServletException if some problem occured
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ServletConfig servletConfig = getServletConfig();
        unavailabilityFlag = Boolean.parseBoolean(
                servletConfig.getInitParameter(PARAM_FLAG));
        unavailableTime = Integer.parseInt(
                servletConfig.getInitParameter(PARAM_TIME));
    }

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Book> books = getBookService().getBooks();
            request.setAttribute(ConstantsJSP.ATTR_LIST_BOOKS, books);
            forward(ConstantsJSP.JSP_BOOK, request, response);
        } catch (DAOException ex) {
            LOGGER.error(ex);
            if (unavailabilityFlag) {
                throw new UnavailableException(ex.getMessage(),
                                                    unavailableTime);
            } else {
                forwardError(ex, ConstantsJSP.JSP_BOOK, request, response);
            }
        }
    }

}
