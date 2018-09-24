package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "userName")
    public String userName;

    @ColumnInfo(name = "userMail")
    public String userMail;
}
