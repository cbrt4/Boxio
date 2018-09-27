package dev.challenge.boxio.view;

import io.reactivex.annotations.Nullable;

public interface AbstractView {

    void showLoading();

    void hideLoading();

    void showSuccessMessage(@Nullable String message);

    void showErrorMessage(@Nullable String error);
}
