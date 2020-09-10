package edu.books.validator;

import static java.util.regex.Pattern.compile;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.StringUtils.hasText;

import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

import edu.books.constant.CommonConstants;

@Component("phoneValidator")
public class PhoneValidator extends AbstractThrowableValidator<String, IllegalArgumentException> {

    @Override
    public void validate(String input) throws IllegalArgumentException {
        if (hasText(input)) {
            Matcher matcher = compile(CommonConstants.PHONE_REGEX).matcher(input);
            isTrue(matcher.find(), "invalid phone number@" + input);
        }
    }
}
