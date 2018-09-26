package dev.challenge.boxio.presenters;

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

    public void doSmth(User user) {
        System.out.println(jsonConverter.createUserJson(user));
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
