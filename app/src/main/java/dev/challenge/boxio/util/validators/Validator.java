package dev.challenge.boxio.util.validators;

public abstract class Validator<T> {

    String validationMessage;

    public abstract boolean isValid(T value);

    public String getValidationMessage() {
        return validationMessage;
    }
}
