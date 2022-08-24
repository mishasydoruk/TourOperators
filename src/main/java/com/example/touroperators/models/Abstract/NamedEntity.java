package com.example.touroperators.models.Abstract;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
public abstract class NamedEntity extends BaseEntity{

    @Column(name="name", nullable = false)
    @NotNull @NotBlank
    protected String name;

}
