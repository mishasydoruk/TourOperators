package com.example.touroperators.dto;

import antlr.ASdebug.IASDebugStream;
import com.example.touroperators.dto.Abstract.BaseDTO;
import com.example.touroperators.models.Tour;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetToursDTO implements BaseDTO {

    private List<GetTourDTO> tours;
}
