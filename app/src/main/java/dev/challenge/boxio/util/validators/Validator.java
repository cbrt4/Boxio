package dev.challenge.boxio.util.validators;

public interface Validator<T> {

    boolean isValid(T value);

    String getValidationMessage();
}
