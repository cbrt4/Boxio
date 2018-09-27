package dev.challenge.boxio.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class User {

    private String userName;
    private String userMail;
    private Box userBox;
    private String userInfo;
    private String updatedAt;

    public User(String userName, String userMail, Box userBox, String userInfo, String updatedAt) {
        this.userName = userName;
        this.userMail = userMail;
        this.userBox = userBox;
        this.userInfo = userInfo;
        this.updatedAt = updatedAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(userMail, that.userMail) &&
                Objects.equals(userBox, that.userBox) &&
                Objects.equals(userInfo, that.userInfo) &&
                Objects.equals(updatedAt, that.updatedAt);
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
