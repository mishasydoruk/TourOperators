package com.example.touroperators.repositories.Abstract;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public abstract class BaseRepository {

   protected final ModelMapper modelMapper;
}
