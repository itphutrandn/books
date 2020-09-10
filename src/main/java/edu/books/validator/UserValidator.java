package edu.books.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.books.model.UserRequest;

@Component("userValidator")
public class UserValidator extends AbstractThrowableValidator<UserRequest, IllegalArgumentException> {

    @Autowired
    @Qualifier("existingValidator")
    private ExistingValidator existingValidator;

    @Autowired
    @Qualifier("phoneValidator")
    private PhoneValidator phoneValidator;

    @Override
    public void validate(UserRequest userRequest) throws IllegalArgumentException {
        if (userRequest.getPhoneNumber() != null) {
            phoneValidator.validate(userRequest.getPhoneNumber());
        }
    }
}
