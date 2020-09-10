package edu.books.constant;

import java.util.List;

import static java.util.Arrays.asList;

public class CommonConstants {

	public static final String DIR_UPLOAD = "WEB-INF/uploads";

	public static final String ACTIVE = "1";

	public static final String DEACTIVE = "0";

	public static final String EMAIL_REGEX = "^[_\\w\\d-]+(\\.[_\\w\\d-]+)*@[\\w\\d-]+(\\.[\\w\\d-]+)*(\\.[\\w]{2,})$";

	public static final String PHONE_REGEX = "^[\\d]*$";

	public static final String DEVICE_CODE_REGEX = "([a-zA-Z0-9]){3}-([0-9]){4}-([0-9]){4}";

	public static final String BIRTHDAY_REGEX = "\\d{4}-\\d{2}-\\d{2}";

	public static final int DEFAULT_PAGING_SIZE = 10;

	public static final int DEFAULT_PAGING_PAGE = 0;

	public static final List<String> FIBRES = asList("4", "12", "24", "28", "72", "96", "120", "144", "192", "288",
			"432", "576", "customised");

	public static final String HOTLINE_REQUEST_PRODUCT_GROUP = "DUCT HOTL";

	public static final String DATE_FORMAT = "DD/MM/YYYY";

	public static final String FEASIBILITY_REQUEST_PRODUCT_GROUP = "DUCT";

	public static final String RESOURCE_SERVER = "/opt/tomcat/apache-tomcat-8.0.32/webapps/images";

	public static final String RESOURCE_BOOK = "/books";

	public static final String RESOURCE_ORIGIN_ADS = "/ads";

	public static final String RESOURCE_ORIGIN_PRODUCT_LANDING = "/landing";

	public static final String RESOURCE_CATEGORY = "/category";

	public static final String RESOURCE_IMAGES = "/images";

	public static final Integer MAX_NUMBER_OF_RESULT = 9999;

	public static final String NO_PROJECT_FOUND = "28";

	public static final String NO_REQUEST_FOUND = "12";

	public static final String MEMBER_TYPE_ADMIN = "ADMIN";

	public static final String MEMBER_TYPE_STORE_OWNER = "STORE_OWNER";

	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final String ROLE_STORE = "ROLE_STORE";

	public static final String TIMEZONE = "GMT+9";

	private CommonConstants() {
		throw new InstantiationError("Must not instantiate this class");
	}

	public static void main(String[] args) {
		System.out.println(BIRTHDAY_REGEX.matches("2019-01-11"));
	}
}
