package org.ticketshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Size(max = 45)
    @NotNull
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "reward_points")
    private Integer rewardPoints;

    @Size(max = 45)
    @NotNull
    @Column(name = "user_type", nullable = false, length = 45)
    private String userType;

    @OneToMany(mappedBy = "userIdSeller")
    private List<Manifestation> manifestations;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

}