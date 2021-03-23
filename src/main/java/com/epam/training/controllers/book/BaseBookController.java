package com.epam.training.controllers.book;

import com.epam.training.controllers.BaseController;
import com.epam.training.model.dao.impl.BookImplDB;
import com.epam.training.model.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "BaseBookController")
public class BaseBookController extends BaseController {

    private BookService bookService;

    /**
     * Calls init() of superclass, initializing DAO-field with
     * implementation from DAO map.
     * @throws ServletException if no such DAO class key in the map
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            var bookDAO = getDaoImpl(BookImplDB.class);
            bookService = new BookService(bookDAO);
        } catch (IllegalArgumentException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     *
     * @return implementation of IBookDAO
     */
    protected BookService getBookService() {
        return bookService;
    }


}
