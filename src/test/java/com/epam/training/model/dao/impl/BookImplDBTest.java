package com.epam.training.model.dao.impl;

import com.epam.training.exceptions.DAOException;
import com.epam.training.helpers.db.ConnectionManager;
import com.epam.training.model.beans.Book;
import com.epam.training.model.dao.interfaces.IBookDAO;
import com.epam.training.utils.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;


public class BookImplDBTest {

    private static final IBookDAO BOOK_DAO = new BookImplDB();

    private static final String CREATE_BOOKS =
            "CREATE TABLE IF NOT EXISTS `books` (" +
            " `id`INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " `title` VARCHAR ( 120 ) NOT NULL UNIQUE," +
            " `author` VARCHAR ( 120 ) NOT NULL," +
            " `description` VARCHAR ( 300 ) NOT NULL," +
            " `releaseDate` DATE NOT NULL " +
            ")";

    private static final String DELETE_FROM_BOOKS =
            "DELETE FROM books";

    private static final String INSERT_BOOK_ONE
            = "INSERT INTO `books` VALUES (1,'one','one','one','2011-01-01');";

    private static final String INSERT_BOOK_TWO
            = "INSERT INTO `books` VALUES (2,'two','two','two','2011-02-02');";

    @BeforeClass
    public static void createDB() throws SQLException {
        ConnectionManager.initDatabaseUrl("file::memory:?cache=shared");
        Connection conn = ConnectionManager.getConnection();
        try (var psCreateBooks = conn.prepareStatement(CREATE_BOOKS)) {
            psCreateBooks.executeUpdate();
        }

    }

    @Before
    public void insertRecords() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (var psDeleteBooks = conn.prepareStatement(DELETE_FROM_BOOKS);
             var psInsertBookOne = conn.prepareStatement(INSERT_BOOK_ONE);
             var psInsertBookTwo = conn.prepareStatement(INSERT_BOOK_TWO)) {
            psDeleteBooks.executeUpdate();
            psInsertBookOne.executeUpdate();
            psInsertBookTwo.executeUpdate();
        }
    }

    @Test
    public void getBooksTest() throws DAOException, ParseException{
        var bookList = BOOK_DAO.getBooks();
        Assert.assertEquals(bookList, Arrays.asList(
                new Book(1, "one", "one",
                        "one", DateUtils.parseDate("2011-01-01")),
                new Book(2, "two", "two",
                        "two", DateUtils.parseDate("2011-02-02"))
        ));
    }

    @Test(expected = DAOException.class)
    public void cantAddBookWithExistingTitle() throws DAOException {
        var book = new Book(0, "one", "one", "one",
                DateUtils.getCurrentDate());
        BOOK_DAO.addBook(book);
    }

    @Test
    public void addBookWithNonexistentTitle() throws DAOException {
        var bookList = BOOK_DAO.getBooks();
        Assert.assertEquals(bookList.size(), 2);
        var book = new Book(3, "titleThree", "authorThree",
                "descrThree", DateUtils.getCurrentDate());
        BOOK_DAO.addBook(book);
        bookList = BOOK_DAO.getBooks();
        Assert.assertEquals(bookList.size(), 3);
        Assert.assertTrue(bookList.contains(book));
    }

    @Test
    public void updateExistingBook() throws DAOException {
        var book = new Book(1, "four", "four",
                "four", DateUtils.getCurrentDate());
        BOOK_DAO.updateBook(book);
        var bookList = BOOK_DAO.getBooks();
        Assert.assertEquals(bookList.get(0), book);

    }

    @Test
    public void updateNonexistentBook() throws DAOException {
        var expectedList = BOOK_DAO.getBooks();
        var book = new Book(10, "four", "four",
                "four", DateUtils.getCurrentDate());
        BOOK_DAO.updateBook(book);
        var actualList = BOOK_DAO.getBooks();
        Assert.assertEquals(expectedList, actualList);

    }

}
