package com.epam.training.controllers.book;

import com.epam.training.constants.ConstantsJSP;
import com.epam.training.exceptions.DAOException;
import com.epam.training.exceptions.ValidationException;
import com.epam.training.wrappers.GetWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookUpdateController", urlPatterns = {"/bookUpdate"})
public final class BookUpdateController extends BaseBookController {

    private static final Logger LOGGER =
            LogManager.getLogger(BookUpdateController.class);

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            getBookService().updateBook(
                    request.getParameter(ConstantsJSP.PARAM_ID),
                    request.getParameter(ConstantsJSP.PARAM_TITLE),
                    request.getParameter(ConstantsJSP.PARAM_AUTHOR),
                    request.getParameter(ConstantsJSP
                            .PARAM_DESCRIPTION),
                    request.getParameter(ConstantsJSP.PARAM_DATE)
            );
            response.sendRedirect(getServletContext().getContextPath()
                    + ConstantsJSP.PATH_BOOK);
        } catch (DAOException | ValidationException ex) {
            LOGGER.error(ex);
            forwardError(ex, ConstantsJSP.PATH_BOOK,
                    new GetWrapper(request), response);
        }
    }

}
