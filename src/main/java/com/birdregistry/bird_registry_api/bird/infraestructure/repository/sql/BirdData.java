package com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "birds")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BirdData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String type;
    @NotEmpty
    private String color;
    @NotEmpty
    private LocalDate dateCreate;
    @NotEmpty
    private String description;
}
