package com.mitocode.dto;

import java.time.LocalDate;

import com.mitocode.model.Patient;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSignsDTO {

    @EqualsAndHashCode.Include
    private Integer idVitalSigns;
    @NotNull
    private LocalDate date;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 5, message = "The temperature must have a maximum of 5 characters")
    private String temperature;
    @Column(length = 5, nullable = false)
    @Size(min = 1, max = 5, message = "The pulse must have a maximum of 5 characters")
    private String pulse;
    @Column(length = 5, nullable = false)
    @Size(min = 1, max = 5, message = "The respiratory must have a maximum of 5 characters")
    private String respiratoryRate;
    private PatientDTO patient;

}
