package com.example.touroperators.models;

import com.example.touroperators.enums.TourType;
import com.example.touroperators.models.Abstract.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tours")
@Getter
@Setter
public class Tour extends NamedEntity {

    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
            name = "tours_users",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private TourOperator tourOperator;

   @PreRemove
    private void removeFromOtherTables(){

        users.forEach(user -> user.getTours().remove(this));
        tourOperator.getTours().remove(this);
    }
}
