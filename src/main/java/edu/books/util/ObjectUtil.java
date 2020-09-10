package edu.books.util;

import java.lang.reflect.Field;

public class ObjectUtil {

    public static void removeEmptyField(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        setNullForEmptyFields(object, fields);
        if(object.getClass().getSuperclass() != null){
            Field[] supperClassFields = object.getClass().getSuperclass().getDeclaredFields();
            setNullForEmptyFields(object, supperClassFields);
        }
    }

    private static void setNullForEmptyFields(Object object, Field[] fields) {
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if ("".equals(field.get(object))) {
                    field.set(object, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
