package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "color")
public class ColorEntity {

    public ColorEntity(String colorName, String colorHex) {
        this.colorName = colorName;
        this.colorHex = colorHex;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "color_name")
    public String colorName;

    @ColumnInfo(name = "color_hex")
    public String colorHex;

    @NonNull
    @Override
    public String toString() {
        return "ColorEntity{" +
                "colorName='" + colorName + '\'' +
                ", colorHex='" + colorHex + '\'' +
                '}';
    }
}
