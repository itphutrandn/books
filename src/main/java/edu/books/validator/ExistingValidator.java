package edu.books.validator;

import static org.springframework.util.Assert.isTrue;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.books.util.FileUploadUtil;

@Component("existingValidator")
public class ExistingValidator  {

    public void validate(Object object, String objectName) throws IllegalArgumentException {
        isTrue(object == null, "이미 존재하는 " + objectName);
    }

    public void validateImageUpload(Object object) throws IllegalArgumentException {
        isTrue(FileUploadUtil.imageFormats.indexOf(object) != -1 , "image type png, gif, jpg ");
    }

    public void validateNotExist(Object object, String objectName) throws IllegalArgumentException {
        isTrue(object != null, objectName + " 는 존재하지 않습니다");
    }
    public void validateList(Object object, String objectName) throws IllegalArgumentException {
        isTrue((object == null || (object != null && ((List)object).size() == 0)), "이미 존재하는 " + objectName);
    }

    public void validateArrays(Object object, String objectName) throws IllegalArgumentException {
        isTrue((object == null || ((List)object).size() != 0), "이미 존재하는 " + objectName);
    }


    public void validateNullOrEmpty(Object object, String objectName) throws IllegalArgumentException {
        isTrue((object != null), objectName + " not empty");
        if(object != null) {
            isTrue((!"".equals(object.toString()) ), objectName + " not empty");
        }
    }
}
