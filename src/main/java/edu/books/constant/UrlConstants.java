package edu.books.constant;

public class UrlConstants {

    public static final String URI_API = "/api";

    public static final String URI_ID = "/{id}";

    public static final String URL_AUTH_LOGIN = "/login";

    public static final String URL_AUTH_LOGOUT = "/logout";
    
    public static final String URL_BOOK = "/books";
    
    public static final String URL_MY_BOOK = "/books/mybooks";
    
    public static final String URL_CREATE_BOOK = "/books/create";
    
    public static final String URL_UPDATE_BOOK = "/books/update";
    
    public static final String URL_DETAIL_BOOK = "/books/detail/{id}";
    
    public static final String URL_DELETE_BOOK = "/books/delete/{id}";

    public static final String URL_USER = "/users";
    
    public static final String URI_ADMIN = "/admin";
    
    private UrlConstants() {
        throw new InstantiationError("Must not instantiate this class");
    }
}
