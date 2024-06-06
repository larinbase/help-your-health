package ru.itis.healthserviceimpl.model.additionalinfo;

import lombok.Getter;

@Getter
public enum ActivityCoefficient {
    SEDENTARY(1.2),
    LIGHT(1.35),
    MEDIUM(1.5),
    HARD(1.72),
    VERY_HARD(1.9);

    private final double numVal;

    ActivityCoefficient(double numVal) {
        this.numVal = numVal;
    }
}
