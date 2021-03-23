package com.epam.training.model.dao.impl;

import com.epam.training.exceptions.DAOException;
import com.epam.training.helpers.db.ConnectionManager;
import com.epam.training.model.beans.Book;
import com.epam.training.model.dao.interfaces.IBookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public final class BookImplDB implements IBookDAO {

    private static final Logger LOGGER = LogManager.getLogger(BookImplDB.class);

    private static final String QUERY_GET_BOOKS = "SELECT * FROM books";

    private static final String QUERY_ADD_BOOK = "INSERT INTO books(title, "
            + "author, description, releaseDate) VALUES(?, ?, ?, ?)";

    private static final String QUERY_UPDATE_BOOK = "UPDATE books SET title "
            + "= ?, author = ?, description = ?, releaseDate = ? WHERE id = ?";

    @Override
    public List<Book> getBooks() throws DAOException {
        final int indId = 1;
        final int indTitle = 2;
        final int indAuthor = 3;
        final int indDescription = 4;
        final int indReleaseDate = 5;
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement psGetBooks =
                    conn.prepareStatement(QUERY_GET_BOOKS);
             ResultSet rs = psGetBooks.executeQuery()
            ) {
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt(indId));
                book.setTitle(rs.getString(indTitle));
                book.setAuthor(rs.getString(indAuthor));
                book.setDescription(rs.getString(indDescription));
                book.setStringReleaseDate(rs.getString(indReleaseDate));
                books.add(book);
            }
            return books;
        } catch (SQLException | ParseException ex) {
            LOGGER.error(ex);
            throw new DAOException("Problem with getting book list!", ex);
        }
    }

    @Override
    public void addBook(final Book book) throws DAOException {
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement psAddBook =
                    conn.prepareStatement(QUERY_ADD_BOOK)) {
            initPSWithBook(psAddBook, book);
            psAddBook.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new DAOException("Problem adding a book!", ex);
        }
    }


    @Override
    public void updateBook(final Book book) throws DAOException {
        final int indId = 5;
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement psUpdateBook =
                    conn.prepareStatement(QUERY_UPDATE_BOOK)) {
            initPSWithBook(psUpdateBook, book);
            psUpdateBook.setInt(indId, book.getId());
            psUpdateBook.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ex);
            throw new DAOException("Problem with updating a book!", ex);
        }
    }

    private void initPSWithBook(final PreparedStatement psBook,
                                final Book book)
            throws SQLException {
        final int indTitle = 1;
        final int indAuthor = 2;
        final int indDescription = 3;
        final int indReleaseDate = 4;
        psBook.setString(indTitle, book.getTitle());
        psBook.setString(indAuthor, book.getAuthor());
        psBook.setString(indDescription, book.getDescription());
        psBook.setString(indReleaseDate, book.getStringReleaseDate());
    }

}
