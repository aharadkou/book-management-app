package com.epam.training.model.services;

import com.epam.training.exceptions.DAOException;
import com.epam.training.exceptions.ValidationException;
import com.epam.training.model.beans.Book;
import com.epam.training.model.dao.interfaces.IBookDAO;
import com.epam.training.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void init() {
        IBookDAO bookDAO = Mockito.mock(IBookDAO.class);
        bookService = new BookService(bookDAO);
    }

    @Test(expected = ValidationException.class)
    public void cantAddBookWithNullTitle()
            throws DAOException, ValidationException{
        var invalidBook = new Book(0, null, "fff",
                "fwaf", DateUtils.getCurrentDate());
        bookService.addBook(invalidBook);
    }

    @Test(expected = ValidationException.class)
    public void cantAddBookWithInvalidDate()
            throws DAOException, ValidationException{
        bookService.addBook("z", "g",
                "s", "invalid date");
    }

    @Test(expected = ValidationException.class)
    public void cantUpdateBookWithInvalidId()
            throws DAOException, ValidationException{
        bookService.updateBook("sss", "gww", "auth",
                "descr", DateUtils.getCurrentDate().toString());
    }

}
