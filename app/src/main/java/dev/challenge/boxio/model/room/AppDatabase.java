package dev.challenge.boxio.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dev.challenge.boxio.model.BoxEntity;
import dev.challenge.boxio.model.ColorEntity;
import dev.challenge.boxio.model.UserEntity;
import dev.challenge.boxio.model.room.dao.BoxDao;

@Database(entities = {UserEntity.class, BoxEntity.class, ColorEntity.class}, version = 3, exportSchema = false)
abstract public class AppDatabase extends RoomDatabase {

    public abstract BoxDao boxDao();
}
