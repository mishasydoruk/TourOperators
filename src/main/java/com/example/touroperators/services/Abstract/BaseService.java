package com.example.touroperators.services.Abstract;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    protected ModelMapper modelMapper;
}
