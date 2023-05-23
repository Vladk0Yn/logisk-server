package com.yanovych.logiskserver.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TransportType type;
    private Double loadCapacity;
    private Double loadHeight;
    private Double loadWidth;

    @OneToOne(mappedBy = "transport", cascade = CascadeType.ALL)
    private Driver driver;
}
