package dev.challenge.boxio.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.challenge.boxio.util.Layout;
import dev.challenge.boxio.view.AbstractView;

public abstract class AbstractActivity extends AppCompatActivity implements AbstractView {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getClass().getAnnotation(Layout.class) != null) {
            setContentView(getClass().getAnnotation(Layout.class).id());
        }

        unbinder = ButterKnife.bind(this);

        inject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    abstract void inject();

    public void showToast(@Nullable String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showSnackBar(@NonNull String message) {
        Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_SHORT);
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        InputMethodManager inputMethodManager;
        if (view != null && (inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
