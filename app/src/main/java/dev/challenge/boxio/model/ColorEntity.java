package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorEntity)) return false;
        ColorEntity that = (ColorEntity) o;
        return Objects.equals(colorName, that.colorName) &&
                Objects.equals(colorHex, that.colorHex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorName, colorHex);
    }

    @NonNull
    @Override
    public String toString() {
        return "ColorEntity { colorName: " + colorName + ", colorHex: " + colorHex + " }";
    }
}
