package dev.challenge.boxio.presenters;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

import dev.challenge.boxio.model.User;
import dev.challenge.boxio.util.JSONConverter;
import dev.challenge.boxio.util.SharedPreferencesManager;
import dev.challenge.boxio.util.validators.BoxValidator;
import dev.challenge.boxio.util.validators.UserValidator;
import dev.challenge.boxio.view.activities.MainActivityView;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends AbstractPresenter<MainActivityView> {

    private CompositeDisposable compositeDisposable;
    private SharedPreferencesManager sharedPreferencesManager;
    private UserValidator userValidator;
    private BoxValidator boxValidator;
    private JSONConverter jsonConverter;

    @Inject
    public MainPresenter(SharedPreferencesManager sharedPreferencesManager,
                         UserValidator userValidator,
                         BoxValidator boxValidator,
                         JSONConverter jsonConverter) {
        this.sharedPreferencesManager = sharedPreferencesManager;
        this.userValidator = userValidator;
        this.boxValidator = boxValidator;
        this.jsonConverter = jsonConverter;
        compositeDisposable = new CompositeDisposable();
    }

    public void submit(User user) {
        if (getView() != null) {
            getView().showLoading();

            compositeDisposable.add(Observable.create((ObservableOnSubscribe<User>) emitter ->
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        if (userValidator.isValid(user) && boxValidator.isValid(user.getUserBox())) {
                            emitter.onNext(user);
                        } else {
                            if (!userValidator.isValid(user)) {
                                emitter.onError(new Throwable(userValidator.getValidationMessage()));
                            } else {
                                emitter.onError(new Throwable(boxValidator.getValidationMessage()));
                            }
                        }
                    }, 2000)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            validUser -> {
                                if (getView() != null) {
                                    getView().hideLoading();
                                    getView().showSuccessMessage("User profile submitted");
                                    getView().onUserProfileSubmitted(jsonConverter.createUserJson(validUser));
                                }
                            },
                            throwable -> {
                                if (getView() != null) {
                                    getView().hideLoading();
                                    getView().showErrorMessage(throwable.getLocalizedMessage());
                                }
                            }
                    ));
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
