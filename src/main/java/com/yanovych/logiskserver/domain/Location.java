package com.yanovych.logiskserver.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LocationType type;
    private String address;
    private Double latitude;
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "locationFrom", fetch = FetchType.LAZY)
    private List<Order> ordersFrom = new ArrayList<>();

    @OneToMany(mappedBy = "locationTo", fetch = FetchType.LAZY)
    private List<Order> ordersTo = new ArrayList<>();
}
