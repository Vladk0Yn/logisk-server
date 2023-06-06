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
    private String code;
    private Double loadCapacity;
    private Double loadHeight;
    private Double loadWidth;
    private Double loadLength;

    @OneToOne(mappedBy = "transport", fetch = FetchType.LAZY)
    private Driver driver;
}
