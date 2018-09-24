package dev.challenge.boxio.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "BoxEntity{" +
                "boxSize='" + boxSize + '\'' +
                ", boxColor=" + boxColor +
                '}';
    }
}
