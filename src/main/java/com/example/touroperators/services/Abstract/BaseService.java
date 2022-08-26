package com.example.touroperators.services.Abstract;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public abstract class BaseService {

    protected ModelMapper modelMapper;


}
