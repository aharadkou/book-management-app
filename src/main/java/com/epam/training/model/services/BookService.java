package com.epam.training.model.services;

import com.epam.training.exceptions.DAOException;
import com.epam.training.exceptions.ValidationException;
import com.epam.training.model.beans.Book;
import com.epam.training.model.dao.interfaces.IBookDAO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.util.List;

public class BookService {

    private final IBookDAO bookDAO;

    private final Validator validator;

    /**
     *
     * @param bookDAO implementation of IBookDAO
     */
    public BookService(IBookDAO bookDAO) {
        ValidatorFactory factory
                = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        this.bookDAO = bookDAO;
    }

    /**
     * Returns all books from the data source.
     * @return list of the books.
     * @throws DAOException if some problem with DAO occured
     */
    public List<Book> getBooks() throws DAOException {
        return bookDAO.getBooks();
    }

    /**
     * Creates book instance and adds it to the data source.
     * @param title book title
     * @param author book author
     * @param description book description
     * @param date book release date
     * @throws DAOException if some problem with DAO occured
     * @throws ValidationException if book invalid
     */
    public void addBook(final String title, final String author,
                        final String description, final String date)
            throws DAOException, ValidationException {
        var book = createBook(title, author, description, date);
        addBook(book);
    }

    /**
     * Creates book instance and updates it in the data source.
     * @param id book id
     * @param title book title
     * @param author book author
     * @param description book description
     * @param date book release date
     * @throws DAOException if some problem with DAO occured
     * @throws ValidationException if book invalid
     */
    public void updateBook(final String id, final String title,
                           final String author, final String description,
                           final String date)
            throws DAOException, ValidationException {
        try {
            var book = createBook(title, author, description, date);
            book.setId(Integer.parseInt(id));
            updateBook(book);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Invalid id!");
        }
    }

    /**
     * Add book to the data source.
     * @param book book
     * @throws DAOException if some problem with DAO occured
     * @throws ValidationException if book invalid
     */
    public void addBook(final Book book)
            throws DAOException, ValidationException {
        validateBook(book);
        bookDAO.addBook(book);
    }

    /**
     * Updates book in the data source.
     * @param book book
     * @throws DAOException if some problem with DAO occured
     * @throws ValidationException if book invalid
     */
    public void updateBook(final Book book)
            throws DAOException, ValidationException {
        validateBook(book);
        bookDAO.updateBook(book);
    }

    private Book createBook(final String title, final String author,
                            final String description, final String date)
                throws ValidationException {
        try {
            var book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setDescription(description);
            book.setStringReleaseDate(date);
            return book;
        } catch (ParseException ex) {
            throw new ValidationException("Invalid date!");
        }
    }

    private void validateBook(final Book book)
            throws ValidationException {
        var constraintViolations = validator.validate(book);
        if (!constraintViolations.isEmpty()) {
            String[] errorMessages
                    = constraintViolations
                        .stream()
                            .map(ConstraintViolation::getMessage)
                                .toArray(String[]::new);
            throw new ValidationException(errorMessages);
        }
    }
}
