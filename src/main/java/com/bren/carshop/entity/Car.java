package com.bren.carshop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    private Double volume;

    private Long price;

    private Double rating;

    private String photo;

    @Column(columnDefinition = "text")
    private String description;

    private Boolean carConditionNew;

    private Integer power;

    private Boolean abs;

    private Boolean leatherSeats;

    @ManyToOne
    private BodyType bodyType;

    @ManyToOne
    private DriverType driverType;

    @ManyToOne
    private Fuel fuel;

    @ManyToOne
    private Gearbox gearbox;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Model model;

    @ManyToOne
    private City city;

    @ManyToMany(mappedBy = "favoriteCars")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    private List<Comment> comments = new ArrayList<>();
}
