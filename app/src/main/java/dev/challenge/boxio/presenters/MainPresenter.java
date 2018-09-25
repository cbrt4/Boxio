package dev.challenge.boxio.presenters;

import javax.inject.Inject;

import dev.challenge.boxio.model.BoxEntity;
import dev.challenge.boxio.model.ColorEntity;
import dev.challenge.boxio.model.UserEntity;
import dev.challenge.boxio.model.room.dao.UserDao;
import dev.challenge.boxio.util.SharedPreferencesManager;
import dev.challenge.boxio.view.activities.MainActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends AbstractPresenter<MainActivity> {

    private UserDao userDao;
    private SharedPreferencesManager sharedPreferencesManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(UserDao userDao, SharedPreferencesManager sharedPreferencesManager) {
        this.userDao = userDao;
        this.sharedPreferencesManager = sharedPreferencesManager;
        compositeDisposable = new CompositeDisposable();
    }

    public void doSmth() {
        UserEntity userEntity =
                new UserEntity("Jumanji",
                        "jumanji@gmail.com",
                        new BoxEntity(BoxEntity.BoxSize.Medium.name(),
                                new ColorEntity("Black", "000000")));

        new CompositeDisposable().add(Observable.fromCallable(() -> {
            userDao.insertUser(userEntity);
            return userEntity;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> System.out.println("Inserted user: " + user)));

        new CompositeDisposable().add(userDao.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> System.out.println("Users from DB: " + users)));
    }

    @Override
    void cancel() {

    }

    @Override
    public void destroy() {
        super.destroy();

        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
