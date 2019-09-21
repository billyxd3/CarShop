package com.bren.carshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String shortName;

    @OneToMany(mappedBy = "country")
    private List<Make> makes = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();

//    @OneToOne
//    private User user;

//    @OneToOne(mappedBy = "country")
//    private City city;
}
