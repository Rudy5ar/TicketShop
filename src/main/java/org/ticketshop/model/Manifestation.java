package org.ticketshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "manifestation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manifestation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "num_of_seats")
    private Integer numOfSeats;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "price_regular")
    private BigDecimal priceRegular;

    @Column(name = "status")
    @Builder.Default
    private String status = "innactive";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_seller")
    private User userSeller;

    @OneToMany(mappedBy = "manifestation")
    private List<Ticket> tickets;

    @Column(name = "num_of_regular_tickets")
    private Integer numOfRegularTickets;

    @Column(name = "num_of_fanpit_tickets")
    private Integer numOfFanpitTickets;

    @Column(name = "num_of_vip_tickets")
    private Integer numOfVipTickets;

}