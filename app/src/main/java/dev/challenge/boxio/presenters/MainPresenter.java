package dev.challenge.boxio.presenters;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

import dev.challenge.boxio.model.User;
import dev.challenge.boxio.util.JSONConverter;
import dev.challenge.boxio.util.SharedPreferencesManager;
import dev.challenge.boxio.view.activities.MainActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter extends AbstractPresenter<MainActivity> {

    private JSONConverter jsonConverter;
    private SharedPreferencesManager sharedPreferencesManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(JSONConverter jsonConverter, SharedPreferencesManager sharedPreferencesManager) {
        this.jsonConverter = jsonConverter;
        this.sharedPreferencesManager = sharedPreferencesManager;
        compositeDisposable = new CompositeDisposable();
    }

    public void submit(User user) {
        if (getView() != null) {
            getView().showLoading();
            System.out.println(jsonConverter.createUserJson(user));
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                getView().hideLoading();
            }, 3000);
        }
    }

    @Override
    void cancel() {
        compositeDisposable.clear();
    }

    @Override
    public void destroy() {
        super.destroy();

        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
