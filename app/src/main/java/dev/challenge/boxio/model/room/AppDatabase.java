package dev.challenge.boxio.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dev.challenge.boxio.model.BoxEntity;
import dev.challenge.boxio.model.ColorEntity;
import dev.challenge.boxio.model.UserEntity;
import dev.challenge.boxio.model.room.dao.BoxDao;
import dev.challenge.boxio.model.room.dao.ColorDao;
import dev.challenge.boxio.model.room.dao.UserDao;

@Database(entities = {UserEntity.class, BoxEntity.class, ColorEntity.class}, version = 3, exportSchema = false)
abstract public class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract BoxDao boxDao();

    public abstract ColorDao colorDao();
}
