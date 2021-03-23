package com.epam.training.constants;

import com.epam.training.helpers.PropertyReader;

public final class ConstantsJSP {

    static {
        PropertyReader propertyReader = new PropertyReader("jsp");
        JSP_BOOK = propertyReader.getProperty("jspBook");
        JSP_HEADER = propertyReader.getProperty("jspHeader");
        PATH_BOOK  = propertyReader.getProperty("booksGet");
        PATH_BOOK_ADD = propertyReader.getProperty("bookAdd");
        PATH_BOOK_UPDATE = propertyReader.getProperty("bookUpdate");
    }

    private ConstantsJSP() { }

    /**
     *  Path to jsp with books.
     */
    public static final String JSP_BOOK;

    /**
     *  Path to header.
     */
    public static final String JSP_HEADER;

    /**
     *  Path to BookController.
     */
    public static final String PATH_BOOK;

    /**
     *  Path to BookAddController.
     */
    public static final String PATH_BOOK_ADD;

    /**
     *  Path to BookUpdateController.
     */
    public static final String PATH_BOOK_UPDATE;


    /**
     *  Name of request attribute with book list.
     */
    public static final String ATTR_LIST_BOOKS = "listBooks";

    /**
     *  Name of request attribute with error.
     */
    public static final String ATTR_ERROR = "errorList";


    /**
     *  Name of request attribute with book id.
     */
    public static final String PARAM_ID = "id";

    /**
     *  Name of request attribute with book title.
     */
    public static final String PARAM_TITLE = "title";

    /**
     *  Name of request attribute with book description.
     */
    public static final String PARAM_DESCRIPTION = "description";

    /**
     *  Name of request attribute with book author.
     */
    public static final String PARAM_AUTHOR = "author";

    /**
     *  Name of request attribute with book release date.
     */
    public static final String PARAM_DATE = "date";

}
