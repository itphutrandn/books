package edu.books.convert;


import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

public abstract class AbstractThrowableConverter<FROM, TO, EXCEPTION extends Exception>
        implements IConverter<FROM, TO, EXCEPTION> {
    @Override
    public abstract TO convert(FROM from) throws EXCEPTION;

    @Override
    public List<TO> convert(List<FROM> fromList) throws EXCEPTION {
        List<TO> toList = null;
        if (ofNullable(fromList).isPresent()) {
            toList = new ArrayList<TO>();
            for (FROM from : fromList) {
                toList.add(convert(from));
            }
        }

        return toList;
    }
}
