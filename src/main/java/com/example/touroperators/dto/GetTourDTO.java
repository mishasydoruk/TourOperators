package com.example.touroperators.dto;

import com.example.touroperators.dto.Abstract.BaseDTO;
import com.example.touroperators.enums.TourType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetTourDTO implements BaseDTO {

    private Long id;

    String name;

    TourType tourType;
}
