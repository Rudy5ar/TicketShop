package org.ticketshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manifestation_id", nullable = false)
    private Manifestation manifestation;

    @OneToMany(mappedBy = "user")
    private Set<Manifestation> manifestations = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

}