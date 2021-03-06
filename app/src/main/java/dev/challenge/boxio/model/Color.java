package dev.challenge.boxio.model;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Color {

    private String colorName;
    private String colorHex;

    public Color(String colorName, String colorHex) {
        this.colorName = colorName;
        this.colorHex = colorHex;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorHex() {
        return colorHex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color)) return false;
        Color that = (Color) o;
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
        return "Color { colorName: " + colorName + ", colorHex: " + colorHex + " }";
    }
}
