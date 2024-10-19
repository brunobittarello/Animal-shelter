package com.sosnazario.dto;

import lombok.Data;

@Data
public class AnimalSearchDto {
    private Integer ageOfBirth;
    private Character type;
    private Character gender;
    private Character size;
}