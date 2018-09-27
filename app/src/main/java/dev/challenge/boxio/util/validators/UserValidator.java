package dev.challenge.boxio.util.validators;

import android.util.Patterns;

import javax.inject.Inject;

import dev.challenge.boxio.model.User;

public class UserValidator extends Validator<User> {

    //private String validationMessage;

    @Inject
    public UserValidator() {
    }

    @Override
    public boolean isValid(User user) {

        if (user == null) {
            validationMessage = "User must is undefined";
            return false;
        }

        if (user.getUserMail() == null || user.getUserMail().isEmpty()) {
            validationMessage = "User e-mail is undefined";
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user.getUserMail()).matches()) {
            validationMessage = "Wrong e-mail format";
            return false;
        }

        if (user.getUserBox() == null) {
            validationMessage = "User box is undefined";
            return false;
        }

        if (user.getUserBox().getBoxSize() == null) {
            validationMessage = "Box size is undefined";
            return false;
        }

        if (user.getUserBox().getBoxColor() == null) {
            validationMessage = "Box color is undefined";
            return false;
        }

        return true;
    }
}
