package edu.books.convert;


import java.util.List;

public interface IConverter<FROM, TO, EXCEPTION extends Exception> {
    TO convert(FROM from) throws EXCEPTION;

    List<TO> convert(List<FROM> from) throws EXCEPTION;
}
