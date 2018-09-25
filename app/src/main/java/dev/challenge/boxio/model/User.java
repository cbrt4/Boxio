package dev.challenge.boxio.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class User {

    public User(String userName, String userMail, Box userBox) {
        this.userName = userName;
        this.userMail = userMail;
        this.userBox = userBox;
    }

    public String userName;
    public String userMail;
    public Box userBox;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(userMail, that.userMail) &&
                Objects.equals(userBox, that.userBox);
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
