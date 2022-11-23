package com.edrone.filegenerator;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TaskSettings {

    public String charsInput;

    Integer requestedQuantityOfWords;

    @NotNull(message = "is required")
    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to ten")
    Integer lengthOfTheWords;
}
