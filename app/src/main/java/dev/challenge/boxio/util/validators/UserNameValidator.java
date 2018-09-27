package dev.challenge.boxio.util.validators;

import javax.inject.Inject;

public class UserNameValidator extends Validator<String> {

    @Inject
    public UserNameValidator() {
    }

    @Override
    public boolean isValid(String value) {
        if (value == null || value.length() < 2) {
            validationMessage = "User name is undefined";
            return false;
        }

        if (value.matches("[A-Z, a-z]+")) {
            validationMessage = "Wrong name format";
            return false;
        }
        return true;
    }
}
