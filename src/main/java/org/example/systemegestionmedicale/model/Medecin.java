package org.example.systemegestionmedicale.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "medecin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Medecin extends User {

    @Column(nullable = false, length = 100)
    private String specialite;

    @Column(length = 20)
    private String telephone;
}
