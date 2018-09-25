package dev.challenge.boxio.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dev.challenge.boxio.di.modules.ApplicationModule;
import dev.challenge.boxio.model.room.dao.BoxDao;
import dev.challenge.boxio.model.room.dao.ColorDao;
import dev.challenge.boxio.model.room.dao.UserDao;
import dev.challenge.boxio.util.SharedPreferencesManager;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context context();

    SharedPreferencesManager sharedPreferences();

    UserDao userDao();

    BoxDao boxDao();

    ColorDao colorDao();
}
