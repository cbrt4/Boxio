package dev.challenge.boxio.util.validators;

import java.util.Objects;

import javax.inject.Inject;

import dev.challenge.boxio.model.Box;

public class BoxValidator extends Validator<Box> {

    @Inject
    public BoxValidator() {
    }

    @Override
    public boolean isValid(Box box) {

        if (box == null) {
            validationMessage = "Box is undefined";
            return false;
        }

        if (box.getBoxSize() == null) {
            validationMessage = "Box size is undefined";
            return false;
        }

        if (box.getBoxColor() == null) {
            validationMessage = "Box color is undefined";
            return false;
        }

        if (box.getBoxSize() == Box.BoxSize.Small) {
            validationMessage = "Wrong box configuration";
            return Objects.equals(box.getBoxColor().getColorName(), "Red") || Objects.equals(box.getBoxColor().getColorName(), "Blue") || Objects.equals(box.getBoxColor().getColorName(), "Yellow");
        }

        if (box.getBoxSize() == Box.BoxSize.Medium) {
            validationMessage = "Wrong box configuration";
            return Objects.equals(box.getBoxColor().getColorName(), "Red") || Objects.equals(box.getBoxColor().getColorName(), "Yellow") || Objects.equals(box.getBoxColor().getColorName(), "Purple") || Objects.equals(box.getBoxColor().getColorName(), "Green");
        }

        if (box.getBoxSize() == Box.BoxSize.Small) {
            validationMessage = "Wrong box configuration";
            return Objects.equals(box.getBoxColor().getColorName(), "Green") || Objects.equals(box.getBoxColor().getColorName(), "Orange") || Objects.equals(box.getBoxColor().getColorName(), "Blue");
        }

        return true;
    }
}
