package dev.challenge.boxio.presenters;

import javax.inject.Inject;

import dev.challenge.boxio.model.room.dao.BoxDao;
import dev.challenge.boxio.model.room.dao.ColorDao;
import dev.challenge.boxio.model.room.dao.UserDao;
import dev.challenge.boxio.util.SharedPreferencesManager;
import dev.challenge.boxio.view.activities.MainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends AbstractPresenter<MainActivity> {

    private UserDao userDao;
    private BoxDao boxDao;
    private ColorDao colorDao;
    private SharedPreferencesManager sharedPreferencesManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(UserDao userDao, BoxDao boxDao, ColorDao colorDao, SharedPreferencesManager sharedPreferencesManager) {
        this.userDao = userDao;
        this.boxDao = boxDao;
        this.colorDao = colorDao;
        this.sharedPreferencesManager = sharedPreferencesManager;
        compositeDisposable = new CompositeDisposable();
    }

    public void doSmth() {
//        UserEntity userEntity =
//                new UserEntity("Jumanji",
//                        "jumanji@gmail.com",
//                        new BoxEntity(BoxEntity.BoxSize.Medium.name(),
//                                new ColorEntity("Black", "000000")));
//
//        new CompositeDisposable().add(Observable.fromCallable(() -> {
//            userDao.insertUser(userEntity);
//            return userEntity;
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(user -> System.out.println("Inserted user: " + user)));

        compositeDisposable.add(userDao.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> System.out.println("\n\nUsers from DB: " + users)));

        compositeDisposable.add(boxDao.getBoxes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> System.out.println("\n\nBoxes from DB: " + users)));

        compositeDisposable.add(colorDao.getColors().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> System.out.println("\n\nColors from DB: " + users)));
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
