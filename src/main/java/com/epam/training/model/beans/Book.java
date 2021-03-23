package com.epam.training.model.beans;

import com.epam.training.utils.DateUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.ParseException;
import java.util.Objects;

public class Book {

    private int id;

    @NotBlank(message = "Title should't be empty!")
    private String title;

    @NotBlank(message = "Author should't be empty!")
    private String author;

    @NotBlank(message = "Description should't be empty!")
    private String description;

    @NotNull
    private Date releaseDate;

    /**
     * Public no fields constructor.
     */
    public Book() { }

    /**
     * Public all fields constructor.
     * @param id id
     * @param title title
     * @param author author
     * @param description description
     * @param releaseDate release date
     */
    public Book(final int id, final String title, final String author,
                final String description, final Date releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    /**
     * Getter for id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id.
     * @param id id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter for title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title.
     * @param title title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Getter for author.
     * @return author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for author.
     * @param author author
     */
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     * Getter for description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     * @param description description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Getter for release date.
     * @return release date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Setter for release date.
     * @param releaseDate release date
     */
    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Setter for release date.
     * @param stringReleaseDate string date
     * @throws ParseException if string date is invalid
     */
    public void setStringReleaseDate(final String stringReleaseDate)
            throws ParseException {
        releaseDate = DateUtils.parseDate(stringReleaseDate);
    }

    /**
     *
     * @return release date in string format
     */
    public String getStringReleaseDate() {
        return DateUtils.formatDate(releaseDate);
    }

    @Override
    public String toString() {
        return "Book{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", author='" + author + '\''
                + ", description='" + description + '\''
                + ", releaseDate=" + releaseDate + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id
                && title.equals(book.title)
                && author.equals(book.author)
                && description.equals(book.description)
                && releaseDate.toString()
                    .equals(book.releaseDate.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author,
                description, releaseDate.toString());
    }
}
