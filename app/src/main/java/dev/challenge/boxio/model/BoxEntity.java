package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "box")
public class BoxEntity {

    enum BoxSize {Small, Medium, Large}

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "boxSize")
    public String boxSize;
}
