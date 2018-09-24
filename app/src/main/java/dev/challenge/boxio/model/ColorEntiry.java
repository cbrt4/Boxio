package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "color")
public class ColorEntiry {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "colorName")
    public String colorName;

    @ColumnInfo(name = "colorHex")
    public String colorHex;
}
