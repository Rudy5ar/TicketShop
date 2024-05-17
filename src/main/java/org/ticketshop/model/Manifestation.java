package org.ticketshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "manifestation")
public class Manifestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idManifestation", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "num_of_seats")
    private Integer numOfSeats;

    @Column(name = "date")
    private Instant date;

    @Column(name = "price_regular")
    private Integer priceRegular;

    @Column(name = "status")
    private Integer status;

    @Size(max = 45)
    @Column(name = "location", length = 45)
    private String location;

}