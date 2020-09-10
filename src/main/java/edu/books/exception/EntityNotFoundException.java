package edu.books.exception;

import java.io.Serializable;

public class EntityNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;


    public EntityNotFoundException() {
        super();
    }


    public EntityNotFoundException(String string) {
        super(string);
    }


    public EntityNotFoundException(String string, Throwable throwable) {
        super(string, throwable);
    }


    public EntityNotFoundException(Throwable throwable) {
        super(throwable);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }

        if (oth == null) {
            return false;
        }

        if (oth.getClass() != getClass()) {
            return false;
        }

        if (!super.equals(oth)) {
            return false;
        }

        return true;
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("EntityNotFoundException:" + "\n");
        buffer.append(super.toString());
        return buffer.toString();
    }
}
