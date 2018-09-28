package dev.challenge.boxio.presenters;

import javax.inject.Inject;

import dev.challenge.boxio.model.User;
import dev.challenge.boxio.model.room.dao.UserDao;
import dev.challenge.boxio.util.JSONConverter;
import dev.challenge.boxio.util.ScreenScope;
import dev.challenge.boxio.util.validators.BoxValidator;
import dev.challenge.boxio.util.validators.UserValidator;
import dev.challenge.boxio.view.activities.MainActivityView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@ScreenScope
public class MainPresenter extends AbstractPresenter<MainActivityView> {

    private CompositeDisposable compositeDisposable;
    private UserValidator userValidator;
    private BoxValidator boxValidator;
    private JSONConverter jsonConverter;
    private UserDao userDao;

    @Inject
    public MainPresenter(UserValidator userValidator,
                         BoxValidator boxValidator,
                         JSONConverter jsonConverter,
                         UserDao userDao) {
        this.userValidator = userValidator;
        this.boxValidator = boxValidator;
        this.jsonConverter = jsonConverter;
        this.userDao = userDao;
        compositeDisposable = new CompositeDisposable();
    }

    public void submit(User user) {
        if (getView() != null) {
            getView().showLoading();

            compositeDisposable.add(Observable.fromCallable(() -> {
                if (userValidator.isValid(user) && boxValidator.isValid(user.getUserBox())) {
                    userDao.insertUser(user);
                }
                return user;
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            theUser -> {
                                if (getView() != null) {
                                    if (userValidator.isValid(theUser) && boxValidator.isValid(theUser.getUserBox())) {
                                        getView().hideLoading();
                                        getView().showSuccessMessage("User profile submitted");
                                        getView().onUserProfileSubmitted(jsonConverter.createUserJson(theUser));
                                        compositeDisposable.add(userDao.getAllUsers().subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(System.out::println));
                                    } else {
                                        if (!userValidator.isValid(user)) {
                                            getView().hideLoading();
                                            getView().showErrorMessage(userValidator.getValidationMessage());
                                        } else {
                                            getView().hideLoading();
                                            getView().showErrorMessage(boxValidator.getValidationMessage());
                                        }
                                    }
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
