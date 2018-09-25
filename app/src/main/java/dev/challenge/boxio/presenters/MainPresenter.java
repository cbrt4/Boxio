package dev.challenge.boxio.presenters;

import javax.inject.Inject;

import dev.challenge.boxio.util.SharedPreferencesManager;
import dev.challenge.boxio.view.activities.MainActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter extends AbstractPresenter<MainActivity> {

    private SharedPreferencesManager sharedPreferencesManager;
    private CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(SharedPreferencesManager sharedPreferencesManager) {
        this.sharedPreferencesManager = sharedPreferencesManager;
        compositeDisposable = new CompositeDisposable();
    }

    public void doSmth() {
//        User userEntity =
//                new User("Jumanji",
//                        "jumanji@gmail.com",
//                        new Box(Box.BoxSize.Medium.name(),
//                                new Color("Black", "000000")));
//
//        new CompositeDisposable().add(Observable.fromCallable(() -> {
//            userDao.insertUser(userEntity);
//            return userEntity;
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(user -> System.out.println("Inserted user: " + user)));

//        compositeDisposable.add(userDao.getUsers().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(users -> System.out.println("\n\nUsers from DB: " + users)));
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
