package org.ticketshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "manifestacija")
public class Manifestacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idManifestacija", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "naziv", length = 45)
    private String naziv;

    @Column(name = "tip")
    private Integer tip;

    @Size(max = 45)
    @Column(name = "brojMesta", length = 45)
    private String brojMesta;

    @Column(name = "datumVreme")
    private Date datumVreme;

    @Column(name = "cenaRegular")
    private Integer cenaRegular;

    @Column(name = "status")
    private Integer status;

    @Size(max = 45)
    @Column(name = "lokacija", length = 45)
    private String lokacija;

}