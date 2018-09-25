package dev.challenge.boxio.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.challenge.boxio.model.room.AppDatabase;
import dev.challenge.boxio.model.room.dao.BoxDao;
import dev.challenge.boxio.model.room.dao.ColorDao;
import dev.challenge.boxio.model.room.dao.UserDao;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationComponent() {
        return context;
    }

    @Provides
    public AppDatabase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "box-db").build();
    }


    @Provides
    public UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    public BoxDao provideBoxDao(AppDatabase appDatabase) {
        return appDatabase.boxDao();
    }

    @Provides
    public ColorDao provideColorDao(AppDatabase appDatabase) {
        return appDatabase.colorDao();
    }
}
