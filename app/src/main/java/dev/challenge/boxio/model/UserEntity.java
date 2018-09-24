package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class UserEntity {

    public UserEntity(String userName, String userMail, BoxEntity userBox) {
        this.userName = userName;
        this.userMail = userMail;
        this.userBox = userBox;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "user_mail")
    public String userMail;

    @Embedded(prefix = "box_")
    public BoxEntity userBox;

    @NonNull
    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userBox=" + userBox +
                '}';
    }
}
