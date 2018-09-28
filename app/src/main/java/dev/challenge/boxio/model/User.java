package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "user_mail")
    private String userMail;

    @Embedded
    private Box userBox;

    @ColumnInfo(name = "user_info")
    private String userInfo;

    @ColumnInfo(name = "updated_at")
    private String updatedAt;

    @ColumnInfo(name = "sign_box")
    private boolean signBox;

    public User(String userName, String userMail, Box userBox, String userInfo, String updatedAt, boolean signBox) {
        this.userName = userName;
        this.userMail = userMail;
        this.userBox = userBox;
        this.userInfo = userInfo;
        this.updatedAt = updatedAt;
        this.signBox = signBox;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public Box getUserBox() {
        return userBox;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public boolean isSignBox() {
        return signBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(userMail, that.userMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userMail, userBox);
    }

    @NonNull
    @Override
    public String toString() {
        return "User { userName: " + userName + ", userMail: " + userMail + ", userBox: " + userBox + " }";
    }
}
