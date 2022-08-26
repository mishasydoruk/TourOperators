package com.example.touroperators.controllers.Abstract;

import com.example.touroperators.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    protected ModelMapper modelMapper;

}
