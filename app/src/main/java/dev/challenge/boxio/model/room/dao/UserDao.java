package dev.challenge.boxio.model.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dev.challenge.boxio.model.User;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE user_mail LIKE :userMail")
    Flowable<User> getUserByMail(String userMail);

    @Query("DELETE FROM user WHERE user_mail LIKE :userMail")
    void deleteUserByMail(String userMail);
}