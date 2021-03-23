package com.epam.training.exceptions;

public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param mes exception message
     */
    public DAOException(final String mes) {
        super(mes);
    }

    /**
     * @param mes exception message
     * @param cause exception cause
     */
    public DAOException(final String mes, final Throwable cause) {
        super(mes, cause);
    }

}
