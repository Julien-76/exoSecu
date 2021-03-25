package be.technifutur.exoSecu.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(nullable = false)
    String lieu;

    @Column(nullable = false)
    LocalDate dateDebut;

    @Column(nullable = false)
    LocalDate dateFin;

    @Column(nullable = false)
    Integer prix;

    @Embedded
    Hebergement hebergement;

    @Column(nullable = false)
    Integer nbrePersonnes;

    @ManyToMany(mappedBy = "voyages")
    List<User> users;
}
