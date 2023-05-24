package com.yanovych.logiskserver.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double weight;
    private Double height;
    private Double width;
    private BigDecimal deliveryPrice;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Timestamp createdTime;
    private Timestamp deliverDueTime;
    private Timestamp acceptTime;
    private Timestamp finishTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_from_id", nullable = false)
    public Location locationFrom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_to_id", nullable = false)
    private Location locationTo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
