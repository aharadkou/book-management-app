package com.epam.training.model.dao.interfaces;

import com.epam.training.exceptions.DAOException;
import com.epam.training.model.beans.Book;

import java.util.List;

public interface IBookDAO {

    /**
     * Gets list of books from data source.
     * @return list of the books
     * @throws DAOException if problem with getting book list occured
     */
    List<Book> getBooks() throws DAOException;

    /**
     * Adds a book to the data source.
     * @param book added book
     * @throws DAOException if problem with adding book occured
     */
    void addBook(Book book) throws DAOException;

    /**
     * Update book in data source.
     * @param book updated book
     * @throws DAOException if problem with updating book occured
     */
    void updateBook(Book book) throws DAOException;

}
