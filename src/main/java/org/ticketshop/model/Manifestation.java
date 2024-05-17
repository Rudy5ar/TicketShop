package org.ticketshop.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "manifestation")
public class Manifestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    @NotNull
    private String name;

    @NotNull
    @Column(name = "type")
    private Integer type;

    @NotNull
    @Column(name = "num_of_seats")
    private Integer num_of_seats;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @Column(name = "price_regular")
    private Integer price_regular;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @NotNull
    @Size(max = 45)
    @Column(name = "location", length = 45)
    private String location;

}