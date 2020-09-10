package edu.books.exception;

import java.io.Serializable;

public class PermissionException extends Exception implements Serializable {
    private static final long serialVersionUID = 1;


    public PermissionException() {
        super();
    }


    public PermissionException(String string) {
        super(string);
    }


    public PermissionException(String string, Throwable throwable) {
        super(string,
                throwable);
    }


    public PermissionException(Throwable throwable) {
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
        buffer.append("PermissionException:" + "\n");
        buffer.append(super.toString());
        return buffer.toString();
    }
}
