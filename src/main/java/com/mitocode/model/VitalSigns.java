package com.mitocode.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class VitalSigns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idVitalSigns;
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 5, nullable = false)
    private String temperature;
    @Column(length = 5, nullable = false)
    private String pulse;
    @Column(length = 5, nullable = false)
    private String respiratoryRate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_Patient", nullable = false, foreignKey = @ForeignKey(name = "FK_VITALSIGNS_PATIENT"))
    private Patient patient;

}
