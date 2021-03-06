package dev.challenge.boxio.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.challenge.boxio.model.room.AppDatabase;
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
    @Singleton
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "app_db").build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }
}
