package edu.books.util;

public final class PaginationUtils {

    public static int getOffset(int page, int rowCount) {
        return (page - 1) * rowCount;
    }
}
