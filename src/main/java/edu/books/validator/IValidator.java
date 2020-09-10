package edu.books.validator;

import java.util.List;

public interface IValidator<INPUT, EXCEPTION extends Exception> {
    void validate(INPUT input) throws EXCEPTION;

    void validate(List<INPUT> inputList) throws EXCEPTION;
}
