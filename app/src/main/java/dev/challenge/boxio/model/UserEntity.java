package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
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
        return "UserEntity { userName: " + userName + ", userMail: " + userMail + ", userBoxSize: " + userBox.boxSize + ", userBoxColorName: " + userBox.boxColor.colorName + ", userBoxColorHex: " + userBox.boxColor.colorHex + " }";
    }
}
