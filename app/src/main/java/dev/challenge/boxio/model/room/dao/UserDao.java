package dev.challenge.boxio.model.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dev.challenge.boxio.model.UserEntity;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity userEntity);

    @Query("SELECT * FROM user")
    Flowable<List<UserEntity>> getUsers();

    @Query("DELETE FROM user")
    void clearUsers();
}
