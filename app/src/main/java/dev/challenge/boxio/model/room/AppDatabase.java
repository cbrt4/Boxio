package dev.challenge.boxio.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dev.challenge.boxio.model.User;
import dev.challenge.boxio.model.room.dao.UserDao;

@Database(entities = {User.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
