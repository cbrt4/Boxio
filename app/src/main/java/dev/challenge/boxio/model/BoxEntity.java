package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "box")
public class BoxEntity {

    public enum BoxSize {Small, Medium, Large}

    public BoxEntity(String boxSize, ColorEntity boxColor) {
        this.boxSize = boxSize;
        this.boxColor = boxColor;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "box_size")
    public String boxSize;

    @Embedded(prefix = "color_")
    public ColorEntity boxColor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoxEntity)) return false;
        BoxEntity boxEntity = (BoxEntity) o;
        return Objects.equals(boxSize, boxEntity.boxSize) &&
                Objects.equals(boxColor, boxEntity.boxColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxSize, boxColor);
    }

    @NonNull
    @Override
    public String toString() {
        return "BoxEntity { size: " + boxSize + ", color:" + boxColor.colorName + ", colorHex: " + boxColor.colorHex + " }";
    }
}
