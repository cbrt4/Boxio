package dev.challenge.boxio.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Box {

    public enum BoxSize {Small, Medium, Large}

    public Box(String boxSize, Color boxColor) {
        this.boxSize = boxSize;
        this.boxColor = boxColor;
    }

    public String boxSize;
    public Color boxColor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return Objects.equals(boxSize, box.boxSize) &&
                Objects.equals(boxColor, box.boxColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxSize, boxColor);
    }

    @NonNull
    @Override
    public String toString() {
        return "Box { size: " + boxSize + ", color:" + boxColor + " }";
    }
}
